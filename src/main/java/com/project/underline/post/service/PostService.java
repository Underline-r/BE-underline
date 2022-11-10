package com.project.underline.post.service;

import com.project.underline.post.entity.repository.HashtagRepository;
import com.project.underline.post.entity.repository.PostRepository;
import com.project.underline.post.web.dto.PostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final HashtagRepository hashtagRepository;

    @Transactional
    public void registerPost(PostRequest postRequest) {

    }
}
