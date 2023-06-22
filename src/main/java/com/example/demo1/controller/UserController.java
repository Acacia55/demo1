package com.example.demo1.controller;

import com.example.demo1.beans.HttpResponseEntity;
import com.example.demo1.dao.entity.UserEntity;
import com.example.demo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:8085")
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;

//    登录验证
    @RequestMapping(value = "/userLogin",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity userLogin (@RequestBody UserEntity userEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            List<UserEntity> hasuser = userService.selectUserInfo(userEntity);

            if (CollectionUtils.isEmpty(hasuser)){
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("用户名或密码错误");
            }
            else {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasuser.get(0));
                httpResponseEntity.setMessage("登陆成功");
            }

        }catch (Exception e){

            System.out.println(e.getMessage());
            e.printStackTrace();

        }
        return httpResponseEntity;
    }

    //    用户列表查询
    @RequestMapping(value = "/queryUserList",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity queryUserList (@RequestBody UserEntity userEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            List<UserEntity> hasuser = userService.queryUserList(userEntity);

            if (CollectionUtils.isEmpty(hasuser)){
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(hasuser.get(0));
                httpResponseEntity.setMessage("无用户信息");
            }
            else {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasuser);
                httpResponseEntity.setMessage("查询成功");
            }

        }catch (Exception e){

            System.out.println(e.getMessage());
            e.printStackTrace();

        }
        return httpResponseEntity;
    }

    //    添加
    @RequestMapping(value = "/addUserInfo",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity addUserinfo (@RequestBody UserEntity userEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            int result = userService.addUserInfo(userEntity);

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

    //    修改
    @RequestMapping(value = "/modifyUserInfo",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity modifyUser (@RequestBody UserEntity userEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            int result = userService.modifyUserInfo(userEntity);

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

    //    删除
    @RequestMapping(value = "/deleteUserinfo",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity deleteUser (@RequestBody UserEntity userEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            int result = userService.deleteUserByName(userEntity);

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


}
