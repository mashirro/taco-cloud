package com.mashirro.tacocloud.controller;

import com.mashirro.tacocloud.entity.UserInfo;
import com.mashirro.tacocloud.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private final static Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(UserInfo userInfo) {
        logger.info("UserInfo submitted: " + userInfo);
        userInfoService.register(userInfo);
        return "redirect:/login";
    }
}
