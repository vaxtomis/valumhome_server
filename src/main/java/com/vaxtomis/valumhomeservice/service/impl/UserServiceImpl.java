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
    public int userRegisteState(String account, String password, String email) {
        User user = userRepository.selectUserByAccount(account);
        if(user!=null){
            return 1001;
        }else{
            if((userRepository.insertUser(account,password,email)==1)){
                return 200;
            }else{
                return 1002;
            }
        }
    }

    @Override
    public int userLogiState(String account, String password) {
        User user = userRepository.selectUserByAccount(account);
        if(user==null){
            return 1011;
        }else{
            if(user.getUserPassword().equals(password)){
                return 200;
            }else {
                return 1012;
            }
        }
    }

}
