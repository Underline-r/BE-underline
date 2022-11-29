package com.project.underline.user.entity;

import com.project.underline.user.entity.repository.UserRepository;
import com.project.underline.user.service.UserService;
import com.project.underline.user.web.dto.SignupRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional()
class UserTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Test
    @Rollback(value = false)
    public void 유저_팔로우_테스트() throws Exception {
        //given
        SignupRequestDto signupRequestDto1 = new SignupRequestDto("email1", "nick", "123");
        SignupRequestDto signupRequestDto2 = new SignupRequestDto("email2", "followerNick1", "123");
        SignupRequestDto signupRequestDto3 = new SignupRequestDto("email3", "followerNick2", "123");

        userService.createUser(signupRequestDto1);
        userService.createUser(signupRequestDto2);
        userService.createUser(signupRequestDto3);

        //when
        userService.addFollow("followerNick1", "nick");
        userService.addFollow("followerNick2", "nick");

        //then
        User checkFollower = userRepository.findByNickname("nick").orElseThrow(() -> new Exception("유저 존재하지 않음"));
        User checkFollowing1 = userRepository.findByNickname("followerNick1").orElseThrow(() -> new Exception("유저 존재하지 않음"));
        User checkFollowing2 = userRepository.findByNickname("followerNick2").orElseThrow(() -> new Exception("유저 존재하지 않음"));

        System.out.println("checkFollower.getFollowerList().toString() = " + checkFollower.getFollowerList().toString());

        for (User user : checkFollower.getFollowerList()) {
            System.out.println("test1 = " + user.getNickname());

            System.out.println("test2 = " + checkFollower.getFollowerList().toString());
        }

        Assertions.assertEquals(checkFollower.getFollowerList().contains(checkFollowing1), true);
        Assertions.assertEquals(checkFollowing1.getFollowingList().contains(checkFollower), true);
        Assertions.assertEquals(checkFollowing2.getFollowingList().contains(checkFollower), true);
    }

}