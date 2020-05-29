package com.vaxtomis.valumhomeservice.repository;
import com.vaxtomis.valumhomeservice.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    //增加一个新用户
    int insertUser(String account,String password,String email);
    //查询某个用户(通过账号)
    User selectUserByAccount(String account);
    //查询用户Id（通过账号密码）
}
