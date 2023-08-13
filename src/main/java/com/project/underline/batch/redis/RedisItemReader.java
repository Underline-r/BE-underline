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

import static com.project.underline.batch.config.BatchConfiguration.chunkSize;

@Component
public class RedisItemReader<T> implements ItemReader<T> {

    private final PostRedisRepository postTempRepository;
    private final List<T> items = new ArrayList<>();
    private int currentIndex = 0;
    private boolean loaded = false; // 최초 호출 여부 체크

    @Autowired
    public RedisItemReader(PostRedisRepository postTempRepository) {
        this.postTempRepository = postTempRepository;
    }

    @Override
    public T read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (currentIndex < items.size()) {
            return items.get(currentIndex++);
        } else {
            if (!loaded) {
                loadItems(); // 최초 호출 시에만 데이터 로드
                loaded = true;
            }
            currentIndex = 0;
            if (items.isEmpty()) {
                return null;
            } else {
                // 마지막 청크 처리를 위해 chunkSize 조정
                int lastChunkSize = items.size() % chunkSize;
                int adjustedChunkSize = (lastChunkSize > 0) ? lastChunkSize : chunkSize;
                List<T> chunk = new ArrayList<>(items.subList(0, adjustedChunkSize));
                items.subList(0, adjustedChunkSize).clear();
                return chunk.get(0); // 첫 번째 항목 반환
            }
        }
    }

    private void loadItems() {
        items.clear();
        Iterable<PostTemp> resultSet = postTempRepository.findAll();
        resultSet.forEach(item -> items.add((T) item));
    }

    public void setLoaded(){
        loaded = false;
    }

}
