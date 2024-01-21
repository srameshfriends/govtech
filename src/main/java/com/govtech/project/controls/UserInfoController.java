package com.govtech.project.controls;

import com.govtech.project.entity.UserInfo;
import com.govtech.project.repository.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserInfoController {

    private final UserInfoRepo userInfoRepository;

    @Autowired
    public UserInfoController(UserInfoRepo userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @GetMapping("/time")
    public @ResponseBody String time() {
        return "Ramesh : " + new Date();
    }

    @PostMapping("/")
    public @ResponseBody UserInfo saveUserInfo(@RequestBody UserInfo userInfo) {
        userInfo.setRev(1);
        userInfo.setCreatedOn(LocalDateTime.now());
        userInfo.setUpdatedOn(LocalDateTime.now());
        userInfo.setCreatedBy(1L);
        userInfo.setUpdatedBy(1L);
        //
        System.out.println(userInfo);
        return userInfoRepository.save(userInfo);
    }

    @GetMapping("/{id}")
    public @ResponseBody UserInfo findUserById(@PathVariable("id") Long userId) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userId);
        userInfo.setRev(1);

        userInfo.setCreatedBy(3002L);
        userInfo.setUpdatedBy(userInfo.getCreatedBy());

        userInfo.setCreatedOn(LocalDateTime.now());
        userInfo.setUpdatedOn(userInfo.getCreatedOn());

        userInfo.setGivenName("Selvaraj Ramesh");

        Optional<UserInfo> optional = userInfoRepository.findById(userId);
        if(optional.isEmpty()) {
            System.out.println("User info not found!");
        }
        return optional.orElse(userInfo);
    }
}
