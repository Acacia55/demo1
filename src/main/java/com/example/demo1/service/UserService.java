package com.example.demo1.service;

import com.example.demo1.common.util.UUIDUtil;
import com.example.demo1.dao.UserEntityMapper;
import com.example.demo1.dao.entity.UserEntity;
import org.apache.tomcat.util.buf.UriUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserEntityMapper userEntityMapper;

    //    查询用户列表
    public List<UserEntity> selectUserInfo(UserEntity userEntity){

        List<UserEntity> result=userEntityMapper.selectUserInfo(userEntity);

        return result;
    }

//    查询用户列表
    public List<UserEntity> queryUserList(UserEntity userEntity){

        List<UserEntity> result=userEntityMapper.queryUserList(userEntity);

        return result;
    }

//    创建用户
    public int addUserInfo(UserEntity userEntity){

        userEntity.setId(UUIDUtil.getOneUUID());

        userEntity.setStatus("1");

        int userResult= userEntityMapper.insert(userEntity);
        System.out.println(userResult);

        if (userResult !=0){
            return 3;
        }
        return userResult;
    }

//    修改用户信息
    public int modifyUserInfo(UserEntity userEntity){

        int userResult = userEntityMapper.updateByPrimaryKeySelective(userEntity);

        if (userResult !=0){
            return 3;
        }
        return userResult;
    }


    public int deleteUserByName(UserEntity userEntity){

        int userResult = userEntityMapper.deleteUserByName(userEntity);

        if (userResult !=0){
            return 3;
        }
        return userResult;
    }

}
