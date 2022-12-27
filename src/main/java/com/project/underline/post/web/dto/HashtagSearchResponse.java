package com.project.underline.post.web.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class HashtagSearchResponse {
    private String userNickname;
    private String tagName;
    private TargetContent targetContent;
    private OtherContent otherContent;

    public HashtagSearchResponse setBasicInfo(String keyword, String userNickname){
        this.tagName = keyword;
        this.userNickname = userNickname;
        return this;
    }

    public HashtagSearchResponse setTargetContent(List<TargetContent.TargetHashtag> targetHashtagList){
        this.targetContent = new TargetContent(targetHashtagList);
        return this;
    }

    public HashtagSearchResponse setOtherContent(List<OtherContent.OtherHashtag> otherHashtagList){
        this.otherContent = new OtherContent(otherHashtagList);
        return this;
    }

    public HashtagSearchResponse(){
    }

    /* 데이터 요청사항 -> 각 데이터가 null 이어도 그대로 내려줘야함 JsonInclude.Include.NON_NULL 사용X */
    @Getter
    public static class TargetContent{
        // 원래 검색하려했던 유저의 해시태그에 의한 게시글 데이터
        private int totalCount;
        private List<TargetHashtag> hashtagList;

        @QueryProjection
        public TargetContent(List<TargetHashtag> targetHashtagLists){
            this.totalCount = 0;
            this.hashtagList = new ArrayList<TargetHashtag>();

            for (TargetHashtag targetHashtag: targetHashtagLists) {
                if(totalCount < 3){
                    hashtagList.add(targetHashtag);
                }
                totalCount+= 1;
            }
        }

        @Getter
        public static class TargetHashtag{
            private Long postId;
            private String content;
            private String source;
            private Long pickCount;
            private Long commentCount;
            private int textCount;
            private String title;

            @QueryProjection
            public TargetHashtag(Long postId,String content,String source,String title,Long pickCount,Long commentCount){
                this.postId = postId;
                this.content = content;
                this.source = source;
                this.title = title;
                this.commentCount = commentCount;
                this.textCount = content.length();
            }

            @QueryProjection
            public TargetHashtag(Long postId,String content,String title,Long pickCount,Long commentCount){ // TO-DO. source 생성되면 해당 생성자 삭제
                this.postId = postId;
                this.pickCount = pickCount;
                this.content = content;
                this.title = title;
                this.commentCount = commentCount;
                this.textCount = content.length();
            }
        }
    }

    @Getter
    public static class OtherContent{
        // 그 외 다른사람들이 사용한 해시태그
        private int totalCount;
        private List<OtherHashtag> hashtagList;

        @QueryProjection
        public OtherContent(List<OtherHashtag> otherHashtagList){
            this.totalCount = 0;
            this.hashtagList = new ArrayList<OtherHashtag>();

            for (OtherHashtag otherHashtag: otherHashtagList) {
                if(totalCount < 3){
                    hashtagList.add(otherHashtag);
                }
                totalCount+= 1;
            }
        }

        @Getter
        public static class OtherHashtag{
            private Long postId;
            private String content;
            private String source;
            private Long pickCount;
            private Long commentCount;
            private int textCount;
            private String title;
            private String userNickname;
            private String userProfileImage;

            @QueryProjection
            public OtherHashtag(Long postId,String content,String title,String userNickname,Long pickCount,Long commentCount){  // TO-DO. source 생성되면 해당 생성자 삭제
                this.postId = postId;
                this.pickCount = pickCount;
                this.content = content;
                this.title = title;
                this.userNickname = userNickname;
                this.commentCount = commentCount;
                this.textCount = content.length();
            }
        }
    }

}
