package com.project.underline.common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.underline.post.entity.PostTemp;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class PostTempSerializer implements RedisSerializer<PostTemp> {

    private static final Charset CHARSET = StandardCharsets.UTF_8;
    private static final String HASH_KEY_POST_ID = "postId";
    private static final String HASH_KEY_POST_VIEW = "postView";

    @Override
    public byte[] serialize(PostTemp postTemp) throws SerializationException {
        try {
            Map<String, Object> hash = new HashMap<>();
            hash.put(HASH_KEY_POST_ID, postTemp.getPostId());
            hash.put(HASH_KEY_POST_VIEW, postTemp.getPostView());
            return new ObjectMapper().writeValueAsString(hash).getBytes(CHARSET);
        } catch (IOException e) {
            throw new SerializationException("Error serializing object", e);
        }
    }

    @Override
    public PostTemp deserialize(byte[] bytes) throws SerializationException {
        try {
            Map<String, Object> hash = new ObjectMapper().readValue(new String(bytes, CHARSET), new TypeReference<Map<String, Object>>() {});
            Long postId = Long.parseLong(hash.get(HASH_KEY_POST_ID).toString());
            Long postView = Long.parseLong(hash.get(HASH_KEY_POST_VIEW).toString());
            return new PostTemp(postId, postView);
        } catch (IOException e) {
            throw new SerializationException("Error deserializing object", e);
        }
    }
}
