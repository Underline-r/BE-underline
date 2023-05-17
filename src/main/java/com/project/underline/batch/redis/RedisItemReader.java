package com.project.underline.batch.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class RedisItemReader<T> implements ItemReader<T> {

    private final RedisTemplate<String, T> redisTemplate;
    private final String key;
    private final List<T> items = new ArrayList<>();
    private int currentIndex = 0;

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
        Set<T> resultSet = redisTemplate.opsForSet().members(key);
        items.addAll(resultSet);
    }
}
