package com.govtech.project.controls;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserDetailsController {

    @GetMapping("/time")
    public @ResponseBody String user() {
        return "Ramesh : " + new Date();
    }
}
