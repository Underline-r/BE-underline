package com.project.underline.batch.processor;

import com.project.underline.post.entity.PostTemp;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostTempToPostViewProcessor implements ItemProcessor<PostTemp, PostTemp> {

    @Override
    @Retryable
    public PostTemp process(PostTemp postTemp) throws Exception {
//        PostView postViewObj = new PostView(postTemp);
//        return postViewObj;
        return postTemp;
    }

}
