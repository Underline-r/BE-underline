package com.project.underline.source.entity;

import com.project.underline.common.util.BaseTimeEntity;
import com.project.underline.post.entity.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "SOURCE")
public class Source extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "SOURCE_ID")
    private Long sourceId;

    @Column(name="TITLE")
    @NotNull
    private String title;

    @OneToMany(mappedBy = "source",
            cascade = CascadeType.ALL)
    private List<Post> postList = new ArrayList<Post>();

    @Builder
    public Source(Long sourceId, String title){
        this.sourceId = sourceId;
        this.title = title;
    }

    public Source(String title){
        this.title = title;
    }

}
