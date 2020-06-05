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
        return userServiceImpl.userRegisterState(account,password,email);
    }
    @RequestMapping(value = "/login")
    public int userLogin(String account,String password){
        return userServiceImpl.userLogState(account,password);
    }

    @RequestMapping(value = "/getHomeId")
    public int getHomeId(String userAccount){
        return userServiceImpl.getHomeIdByAccount(userAccount);
    }

    @RequestMapping(value = "/changeHomeId")
    public int changeHomeId(String userAccount,String ownerAccount,String ownerPassword){
        return userServiceImpl.changeHomeIdByOwnerId(userAccount,ownerAccount,ownerPassword);
    }
}
