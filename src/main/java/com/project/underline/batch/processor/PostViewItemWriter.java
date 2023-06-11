package com.project.underline.batch.processor;

import com.project.underline.post.entity.Post;
import com.project.underline.post.entity.PostTemp;
import com.project.underline.post.entity.PostView;
import com.project.underline.post.entity.repository.PostRedisRepository;
import com.project.underline.post.entity.repository.PostRepository;
import com.project.underline.post.entity.repository.PostViewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class PostViewItemWriter implements ItemWriter<PostTemp> {


    private final PostViewRepository postViewRepository;
    private final PostRepository postRepository;
    private final PostRedisRepository postRedisRepository;

    /*
     * 1. PostTemp에서 관리중인 Post가 실제로 존재하는지 검색
     * 2. 해당 Post리스트들을 PostView에 매핑해줌
     * 3. 그다음에 saveAll*/
    @Override
    @Transactional
    public void write(List<? extends PostTemp> items) {
        try{
            List<Long> postIds = items.stream().map(PostTemp::getPostId).collect(Collectors.toList());
            List<Post> posts = postRepository.findAllById(postIds);

            // Map 생성
            Map<Long, Post> postMap = posts.stream()
                    .collect(Collectors.toMap(Post::getPostId, Function.identity()));

            List<PostView> postViews = new ArrayList<>();
            List<PostTemp> deletedPostList = new ArrayList<>();
            for (PostTemp postTemp : items) {
                Post post = postMap.get(postTemp.getPostId());
                if (post != null) {
                    postViews.add(new PostView(post, postTemp));
                } else {
                    // 현재 이동하려는 postTemp
                    deletedPostList.add(postTemp);
                    // postTemp.getPostId()에 해당하는 Post가 데이터베이스에 없음
                    // 이에 대한 처리를 여기에 추가하세요
                }
            }

            // 존재하는 postId는 저장하고
            postViewRepository.saveAll(postViews);
            // 삭제된 postId는 Redis에서도 삭제

            log.info("* -- 조회수 배치 완료 -- *");

        }catch (RuntimeException e) {
            log.info("* -- 조회수 저장 오류 -- *");
            throw e;
        }
    }

}