package com.vaxtomis.valumhomeservice.service.impl;

import com.vaxtomis.valumhomeservice.repository.UserRepository;
import com.vaxtomis.valumhomeservice.entity.User;
import com.vaxtomis.valumhomeservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public int userRegisterState(String account, String password, String email) {
        User user = userRepository.selectUserByAccount(account);
        if(user!=null){
            return -1001;
        }else{
            if((userRepository.insertUser(account,password,email)==1)){
                userRepository.addhome(account);
                return 200;
            }else{
                return -1002;
            }
        }
    }

    @Override
    public int userLogState(String account, String password) {
        User user = userRepository.selectUserByAccount(account);
        if(user==null){
            return -1011;
        }else{
            if(user.getUserPassword().equals(password)){
                return userRepository.selectHomeIdByAccount(account);
            }else {
                return -1012;
            }
        }
    }

    @Override
    public int changeHomeIdByOwnerId(String userAccount, String ownerAccount,String ownerPassword) {
        Integer result =userRepository.selectHomeIdByAccount(ownerAccount);
        User user = userRepository.selectUserByAccount(ownerAccount);
        int code = -1031;
        if (result!=null&&user!=null){
            if(user.getUserPassword().equals(ownerPassword)) {
                if (userRepository.updateUserHomeId(result, userAccount) > 0) {
                    code = result;
                }
            }else{
                code = -1033;
            }
        }
        return code;
    }


    @Override
    public int getHomeIdByAccount(String userAccount) {
        Integer result = userRepository.selectHomeIdByAccount(userAccount);
        if (result!=null){
            return result;
        }
        return -1032;
    }

}
