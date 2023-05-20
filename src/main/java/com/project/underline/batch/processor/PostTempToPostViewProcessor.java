package com.project.underline.batch.processor;

import com.project.underline.post.entity.PostTemp;
import com.project.underline.post.entity.PostView;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostTempToPostViewProcessor implements ItemProcessor<PostTemp, PostTemp> {

    @Override
    public PostTemp process(PostTemp postTemp) throws Exception {
//        PostView postViewObj = new PostView(postTemp);
//        return postViewObj;
        return postTemp;
    }

}
