package com.project.underline.reference.entity;

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
@Table(name = "REFERENCE")
public class Reference {

    @Id
    @GeneratedValue
    @Column(name = "REFERENCE_ID")
    private Long referenceId;

    @Column(name="TITLE")
    @NotNull
    private String title;

    @OneToMany(mappedBy = "reference",
            cascade = CascadeType.ALL)
    private List<Post> postList = new ArrayList<Post>();

    @Builder
    public Reference(Long referenceId, String title){
        this.referenceId = referenceId;
        this.title = title;
    }

    public Reference(String title){
        this.title = title;
    }

}
