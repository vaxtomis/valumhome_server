package com.vaxtomis.valumhomeservice.service;

import com.vaxtomis.valumhomeservice.entity.User;

public interface UserService {
    //增加一个用户
    int userRegisterState(String account,String password,String email);
    //登录确认
    int userLogState(String account,String password);
    //修改用户家庭编号（User表）
    int changeHomeIdByOwnerId(String userAccount,String ownerAccount,String ownerPassword);
    //获取用户家庭编号(User表)
    int getHomeIdByAccount(String userAccount);
}
