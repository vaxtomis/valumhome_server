package com.vaxtomis.valumhomeservice.controller;

import com.vaxtomis.valumhomeservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userServiceImpl;
    @RequestMapping(value = "/registe")
    public int userRegiste(String account,String password,String email){
        return userServiceImpl.userRegisteState(account,password,email);
    }
    @RequestMapping(value = "/login")
    public int userLogin(String account,String password){
        return userServiceImpl.userLogiState(account,password);
    }
}
