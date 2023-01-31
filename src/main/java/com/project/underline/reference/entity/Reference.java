package com.project.underline.reference.entity;

import com.project.underline.post.entity.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "REFERENCE")
public class Reference {
    @Id
    @Column(name = "REFERENCE_ID")
    private Long referenceId;

    @Column(name="TITLE")
    private String title;

    @Column(name="AUTHOR")
    private String author;

    @Builder
    public Reference(Long referenceId, String title,String author){
        this.referenceId = referenceId;
        this.title = title;
        this.author = author;
    }

}
