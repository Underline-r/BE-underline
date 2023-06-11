package com.project.underline.common.util;

import com.project.underline.feed.web.dto.FeedPost;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.*;

public class FeedPostSerializer implements RedisSerializer<FeedPost> {

    @Override
    public byte[] serialize(FeedPost feedPost) throws SerializationException {
        try {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
            objectStream.writeObject(feedPost);
            objectStream.flush();
            return byteStream.toByteArray();
        } catch (IOException e) {
            throw new SerializationException("Error serializing object", e);
        }
    }

    @Override
    public FeedPost deserialize(byte[] bytes) throws SerializationException {
        try {
            ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectStream = new ObjectInputStream(byteStream);
            return (FeedPost) objectStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new SerializationException("Error deserializing object", e);
        }
    }
}
