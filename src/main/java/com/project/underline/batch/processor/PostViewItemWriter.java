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
import org.springframework.retry.annotation.Retryable;
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
    @Retryable
    @Transactional
    public void write(List<? extends PostTemp> items) {
        try{
//            List<Post> temp = postRepository.findAllById(items.stream().map(PostTemp::getPostId)
//                    .collect(Collectors.toList()));
//
//            postRepository.saveAll(temp);

            // Redis에 있는 조회수 데이터를 들고와서 Post데이터로 매핑
            Map<Long, Post> postMap = postRepository.findAllById(items.stream().map(PostTemp::getPostId)
                            .collect(Collectors.toList()))
                    .stream()
                    .collect(Collectors.toMap(Post::getPostId, Function.identity()));

            List<PostView> saveTarget = new ArrayList<>();
            List<PostTemp> deleteTarget = new ArrayList<>();

            for (PostTemp postTemp : items) {
                if (postMap.get(postTemp.getPostId()) != null) {
                    // Redis에서 들고온 postTemp가 Sql post에 존재한다면(=정합성이 맞는다면)
                    saveTarget.add(new PostView(postMap.get(postTemp.getPostId()), postTemp));
                } else {
                    // Redis에 존재하는 데이터가 sql에 존재하지 않는다면
                    deleteTarget.add(postTemp);
                }
            }

            postViewRepository.saveAll(saveTarget);
            postRedisRepository.deleteAll(deleteTarget);
        }catch (RuntimeException e){
            throw e;
        }
    }

}