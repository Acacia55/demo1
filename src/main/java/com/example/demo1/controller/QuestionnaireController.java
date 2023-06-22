package com.example.demo1.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.demo1.beans.HttpResponseEntity;
import com.example.demo1.dao.entity.QuestionEntity;
import com.example.demo1.dao.entity.QuestionnaireEntity;
import com.example.demo1.service.QuestionService;
import com.example.demo1.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:8085")
public class QuestionnaireController {

    @Autowired
    private QuestionnaireService questionnaireService;
    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/modifyQuestionnaire",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity addQuestionnaire (@RequestBody String json){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {

            JSONObject jsonObject = JSON.parseObject(json);

            // 在转成不同的实体类
            QuestionnaireEntity questionnaireEntity = jsonObject.getObject("questionnaire", QuestionnaireEntity.class);

            System.out.println(questionnaireEntity.getProjectId());

            QuestionEntity questionEntity = jsonObject.getObject("question", QuestionEntity.class);

            int result = questionnaireService.addQuestionnaire(questionnaireEntity);

            int resultQuestion = questionService.addQuestion(questionEntity);

            if (result!=0 && resultQuestion !=0){
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("创建问卷第一阶段成功");
            }
            else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("创建问卷第一阶段失败");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

}
