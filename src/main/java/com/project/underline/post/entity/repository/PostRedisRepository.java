package com.project.underline.post.entity.repository;

import com.project.underline.post.entity.PostTemp;
import org.springframework.data.repository.CrudRepository;

public interface PostRedisRepository extends CrudRepository<PostTemp, Long> {

}
