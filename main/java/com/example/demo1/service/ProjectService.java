package com.example.demo1.service;

import com.example.demo1.common.util.UUIDUtil;
import com.example.demo1.dao.ProjectEntityMapper;
import com.example.demo1.dao.entity.ProjectEntity;
import com.example.demo1.dao.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjectService {

    @Autowired
    private ProjectEntityMapper projectEntityMapper;

    public List<ProjectEntity> queryProjectList(ProjectEntity projectEntity){

        List<ProjectEntity> result=projectEntityMapper.queryProjectList(projectEntity);

        return result;
    }

    public int addProjectInfo(ProjectEntity projectEntity){

        projectEntity.setId(UUIDUtil.getOneUUID());

//        userEntity.setStatus("1");

        int projcetResult= projectEntityMapper.insert(projectEntity);
        System.out.println(projcetResult);

        if (projcetResult !=0){
            return 3;
        }
        return projcetResult;
    }

    public int deleteProjectById(ProjectEntity projectEntity){

        int projectResult = projectEntityMapper.deleteProjectById(projectEntity);

        if (projectResult !=0){
            return 3;
        }
        return projectResult;
    }

    public int modifyProjectInfo(ProjectEntity projectEntity){

        int projectResult = projectEntityMapper.updateByPrimaryKeySelective(projectEntity);

        if (projectResult !=0){
            return 3;
        }
        return projectResult;
    }

}
