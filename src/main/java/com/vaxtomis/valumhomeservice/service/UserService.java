package com.vaxtomis.valumhomeservice.service;

import com.vaxtomis.valumhomeservice.entity.User;

public interface UserService {
    //增加一个用户
    int userRegisteState(String account,String password,String email);
    //登录确认
    int userLogiState(String account,String password);
}
