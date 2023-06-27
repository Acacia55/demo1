package com.example.demo1.controller;

import com.example.demo1.beans.HttpResponseEntity;
import com.example.demo1.dao.entity.ProjectEntity;
import com.example.demo1.dao.entity.UserEntity;
import com.example.demo1.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:8085")
@RequestMapping("")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/queryProjectList",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity queryProjectList(@RequestBody ProjectEntity projectEntity){

        HttpResponseEntity httpResponseEntity =new HttpResponseEntity();
        try {
            List<ProjectEntity> hasproject = projectService.queryProjectList(projectEntity);
            for (ProjectEntity list:
                    hasproject) {
                System.out.println(list.toString());
            }
            if (CollectionUtils.isEmpty(hasproject)){
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("没有项目信息");
            }
            else {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasproject);
                httpResponseEntity.setMessage("查询成功");
            }

        }catch (Exception e){

            System.out.println(e.getMessage());
            e.printStackTrace();

        }
        return httpResponseEntity;
    }

    @RequestMapping(value = "/addProjectInfo",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity addProjectInfo (@RequestBody ProjectEntity projectEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            int result = projectService.addProjectInfo(projectEntity);

            if (result!=0){
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("添加成功");
            }
            else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("添加失败");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    @RequestMapping(value = "/deleteProjectById",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity deleteProjectById (@RequestBody ProjectEntity projectEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            int result = projectService.deleteProjectById(projectEntity);

            if (result!=0){
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("删除成功");
            }
            else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("删除失败");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    @RequestMapping(value = "/modifyProjectInfo",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity modifyProjectInfo (@RequestBody ProjectEntity projectEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            int result = projectService.modifyProjectInfo(projectEntity);

            if (result!=0){
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("修改成功");
            }
            else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("修改失败");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

}
