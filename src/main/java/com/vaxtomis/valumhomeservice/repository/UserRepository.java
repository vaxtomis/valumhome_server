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
    User selectUserByAccount(String userAccount);
    //修改用户家庭编号
    Integer updateUserHomeId(int userHomeId,String userAccount);
    //获取家庭编号（从Home表中）
    Integer selectHomeIdFromHomeByAccount(String userAccount);
    //获取用户当前家庭编号（从User表中）
    Integer selectHomeIdByAccount(String userAccount);

}
