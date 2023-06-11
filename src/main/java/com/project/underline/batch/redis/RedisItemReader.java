package com.project.underline.batch.redis;

import com.project.underline.post.entity.PostTemp;
import com.project.underline.post.entity.repository.PostRedisRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RedisItemReader<T> implements ItemReader<T> {

    private final PostRedisRepository postTempRepository;
    private final List<T> items = new ArrayList<>();
    private int currentIndex = 0;

    @Autowired
    public RedisItemReader(PostRedisRepository postTempRepository) {
        this.postTempRepository = postTempRepository;
    }

    @Override
    public T read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (currentIndex < items.size()) {
            return items.get(currentIndex++);
        } else {
            loadItems();
            currentIndex = 0;
            if (items.isEmpty()) {
                return null;
            } else {
                return items.get(currentIndex++);
            }
        }
    }

    private void loadItems() {
        items.clear();
        Iterable<PostTemp> resultSet = postTempRepository.findAll();
        resultSet.forEach(item -> items.add((T) item));
    }

}
