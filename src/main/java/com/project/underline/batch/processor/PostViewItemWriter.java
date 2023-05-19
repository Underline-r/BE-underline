package com.project.underline.batch.processor;

import com.project.underline.post.entity.Post;
import com.project.underline.post.entity.PostTemp;
import com.project.underline.post.entity.PostView;
import com.project.underline.post.entity.repository.PostRepository;
import com.project.underline.post.entity.repository.PostViewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class PostViewItemWriter implements ItemWriter<PostTemp> {


    private final PostViewRepository postViewRepository;
    private final PostRepository postRepository;

    /*
     * 1. PostTemp에서 관리중인 Post가 실제로 존재하는지 검색
     * 2. 해당 Post리스트들을 PostView에 매핑해줌
     * 3. 그다음에 saveAll*/
    @Override
    @Transactional
    public void write(List<? extends PostTemp> items) {
        List<Long> postIds = items.stream().map(PostTemp::getPostId).collect(Collectors.toList());
        List<Post> posts = postRepository.findAllById(postIds);

        List<PostView> postViews = new ArrayList<>();
        for (PostTemp postTemp : items) {
            for (Post post : posts) {
                if (postTemp.getPostId().equals(post.getPostId())) {
                    postViews.add(new PostView(post));
                    break;
                }
            }
        }

        postViewRepository.saveAll(postViews);

        log.info("* -- 조회수 배치 완료 -- *");
    }

}