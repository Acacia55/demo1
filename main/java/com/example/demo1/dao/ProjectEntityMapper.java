package com.example.demo1.dao;

import com.example.demo1.dao.entity.ProjectEntity;
import com.example.demo1.dao.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface ProjectEntityMapper {

    List<ProjectEntity> queryProjectList(ProjectEntity projectEntity);

    int insert(ProjectEntity projectEntity);

    int deleteProjectById(ProjectEntity projectEntity);

    int updateByPrimaryKeySelective(ProjectEntity projectEntity);

}
