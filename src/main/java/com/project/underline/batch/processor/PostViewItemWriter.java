package com.project.underline.batch.processor;

import com.project.underline.post.entity.PostView;
import com.project.underline.post.entity.repository.PostViewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PostViewItemWriter implements ItemWriter<PostView> {

    private final PostViewRepository postViewRepository;

    @Override
    @Transactional
    public void write(List<? extends PostView> items) {
        log.info("* -- PostView save all -- *");
        postViewRepository.saveAll(items);
    }
}