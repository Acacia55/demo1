package com.example.demo1.dao;

import com.example.demo1.dao.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface UserEntityMapper {

//    查询用户列表
    List<UserEntity> queryUserList(UserEntity userEntity);
//    创建用户的基本信息
    int insert(UserEntity userEntity);
//    删除和修改
    int deleteUserByName(UserEntity userEntity);
    int updateByPrimaryKeySelective(UserEntity userEntity);

    List<UserEntity> selectUserInfo(UserEntity userEntity);

}
