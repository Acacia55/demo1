package com.example.demo1.dao;

import com.example.demo1.dao.entity.UserEntity;

import java.util.List;
import java.util.Map;

public interface UserEntityMapper {

//    查询用户列表
    List<Map<String,Object>> queryUserList(UserEntity userEntity);
//    创建用户的基本信息
    int addUserinfo(UserEntity userEntity);
//    删除和修改
    int deleteUserById(UserEntity userEntity);
    int updateByPrimaryKeySelective(UserEntity userEntity);

}
