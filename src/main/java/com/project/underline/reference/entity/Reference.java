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
    @Column(name = "ISBN13")
    private Long isbn13;

    @Column(name="TITLE")
    private String title;

    @Column(name="AUTHOR")
    private String author;

    // TODO.  ref기준으로 post가 많을텐데 그때마다 통으로 조회하는 쿼리가 나가면 안될듯함 -> 해당 속성을 쓰는 서비스단에서 잘 처리해야함
    @OneToMany(mappedBy = "post")
    private List<Post> posts = new ArrayList<Post>();

    @Builder
    public Reference(Long isbn13, String title,String author){
        this.isbn13 = isbn13;
        this.title = title;
        this.author = author;
    }

}
