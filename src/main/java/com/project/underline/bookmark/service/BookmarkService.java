package com.project.underline.bookmark.service;

import com.project.underline.bookmark.entity.Bookmark;
import com.project.underline.bookmark.entity.repository.BookmarkRepository;
import com.project.underline.bookmark.web.dto.BookmarkRequest;
import com.project.underline.bookmark.web.dto.BookmarkResponse;
import com.project.underline.common.exception.UnderlineException;
import com.project.underline.common.metadata.ErrorCode;
import com.project.underline.common.util.SecurityUtil;
import com.project.underline.post.entity.Post;
import com.project.underline.post.entity.repository.PostRepository;
import com.project.underline.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final PostRepository postRepository;
    private final UserService userService;

    @Transactional
    public void registerBookmark(BookmarkRequest bookmarkRequest) {
        Boolean bookmarkExist = bookmarkRepository.existsByPost_PostIdAndUser_Id(bookmarkRequest.getPostId(),SecurityUtil.getCurrentUserId());
        if(bookmarkExist){
            bookmarkRepository.deleteBookmarkByPost_PostIdAndUser_Id(bookmarkRequest.getPostId(),SecurityUtil.getCurrentUserId());
        }else{
            bookmarkRepository.save(new Bookmark(userService.existUser(SecurityUtil.getCurrentUserId())
                    ,checkPostExistence(bookmarkRequest.getPostId()))
            );
        }
    }

    @Transactional
    public BookmarkResponse inquiryBookmarks() {
        BookmarkResponse bookmarkResponse = new BookmarkResponse(bookmarkRepository.findAllByUserId(SecurityUtil.getCurrentUserId()));
        return bookmarkResponse;
    }

    private Post checkPostExistence(Long postId){
        return postRepository.findById(postId)
                .orElseThrow(() -> new UnderlineException(ErrorCode.CANNOT_FOUND_POST));
    }

}
