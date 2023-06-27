package com.example.demo1.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.demo1.beans.HttpResponseEntity;
import com.example.demo1.dao.entity.*;
import com.example.demo1.dao.entity.responseEntity.Problem;
import com.example.demo1.dao.entity.responseEntity.Questionnaire;
import com.example.demo1.service.AnswerService;
import com.example.demo1.service.OptionService;
import com.example.demo1.service.QuestionService;
import com.example.demo1.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:8085")
public class QuestionnaireController {

    @Autowired
    private QuestionnaireService questionnaireService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private OptionService optionService;
    @Autowired
    private AnswerService answerService;

    @RequestMapping(value = "/modifyQuestionnaire",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity addQuestionnaire (@RequestBody QuestionsAndOptionsEntity questionsAndOptionsEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {

            System.out.println(questionsAndOptionsEntity.toString());

            int questionresult = 0;
            int optionresult = 0;

            List<QuestionEntity> questionEntityList = questionsAndOptionsEntity.getQuestionEntityList();

            for (QuestionEntity question:
                 questionEntityList) {
                System.out.println(question.toString());
            }

            List<OptionEntity> optionEntityList = questionsAndOptionsEntity.getOptionEntityList();

            System.out.println(optionEntityList.size());

            List<QuestionEntity> tempquestionlist = new ArrayList<>();

            for (int i = 0; i<questionEntityList.size(); i++) {
                if (questionEntityList.get(i) != null) {
                    QuestionEntity questionEntity = new QuestionEntity();

                    questionEntity.setQuestionId(questionEntityList.get(i).getQuestionId());
                    questionEntity.setQuestionnaireId(questionEntityList.get(i).getQuestionnaireId());
                    questionEntity.setQuestionType(questionEntityList.get(i).getQuestionType());
                    questionEntity.setQuestionTypeName(questionEntityList.get(i).getQuestionTypeName());

                    tempquestionlist.add(questionEntity);
                }
                System.out.println(tempquestionlist.get(i).getQuestionId());
            }

            for (int i = 0;i<questionEntityList.size();i++)
            {
                questionresult = questionService.addQuestion(questionEntityList.get(i));

                for (int j = 0;j<optionEntityList.size(); j++) {

                    System.out.println(tempquestionlist.get(i).getQuestionId());

                    if (optionEntityList.get(j).getQuestionid().equals(tempquestionlist.get(i).getQuestionId())){

                        System.out.println(j);

                        optionEntityList.get(j).setQuestionid(questionEntityList.get(i).getQuestionId());

                        optionresult = optionService.addOptions(questionsAndOptionsEntity.getOptionEntityList().get(j));

                    }
                }
            }

            if (questionresult !=0 && optionresult !=0){
                httpResponseEntity.setCode("666");
//                1代表创建成功
                httpResponseEntity.setData(1);
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


    @RequestMapping(value = "/insertQuestionnaire",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity insertQuestionnaire (@RequestBody QuestionnaireEntity questionnaireEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {

            int result = questionnaireService.addQuestionnaire(questionnaireEntity);

            System.out.println(result);

            if (result!=0){
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(questionnaireEntity);
                httpResponseEntity.setMessage("创建问卷信息成功");
            }
            else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("创建问卷信息失败");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }



    @RequestMapping(value = "/queryQuestionnaireList",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity queryQuestionnaireList(@RequestBody ProjectEntity projectEntity){

        HttpResponseEntity httpResponseEntity =new HttpResponseEntity();
        try {
            List<Map<String, Object>> questionnaireEntityList = questionnaireService.queryQuestionnaireList(projectEntity);


//            for (Map<String, Object> list:
//                    questionnaireEntityList) {
//                System.out.println(list.toString());
//            }

            if (CollectionUtils.isEmpty(questionnaireEntityList)){
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("没有问卷信息");
            }
            else {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(questionnaireEntityList);
                httpResponseEntity.setMessage("当前问卷查询成功");
            }

        }catch (Exception e){

            System.out.println(e.getMessage());
            e.printStackTrace();

        }
        return httpResponseEntity;
    }

    /**
     *
     * @param info:包含两个信息，questionnaireId（问卷id）和answerName（答卷人姓名）
     */
    @PostMapping("/query")
    public HttpResponseEntity getQuestionnaire(@RequestBody Map<String, String> info) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        // 首先获取questionnaireId和answerName
        String questionnaireId = info.get("questionnaireId");
        String answerName = info.get("answerName");

        // 创建返回对象实体类
        Questionnaire responseEntity = new Questionnaire();
        // 根据questionnaireId获取问卷信息
        QuestionnaireEntity questionnaireEntity = questionnaireService.queryQuestionnaireById(questionnaireId);
        responseEntity.setQuestionnaireEntity(questionnaireEntity);

        // 根据questionnaireId获取问题列表
        List<QuestionEntity> questionEntityList = questionService.getQuestionByQuestionnaireId(questionnaireId);
        questionEntityList.forEach(questionEntity -> {
            // 根据questionId获取选项列表
            List<OptionEntity> options = optionService.getOptionByQuestionId(questionEntity.getQuestionId());
            // 封装成problem插入responseEntity
            Problem problem = new Problem();
            problem.setQuestionEntity(questionEntity);
            problem.setOptions(options);
            responseEntity.getProblems().add(problem);
        });

        if (!answerName.equals("")) {
            // 根据questionnaireId获取答案列表
            List<Problem> problems = responseEntity.getProblems();
            problems.forEach(problem -> {
                List<AnswerEntity> answers = problem.getAnswers();
                problem.getOptions().forEach(optionEntity -> {
                    AnswerEntity answerEntity = new AnswerEntity();
                    answerEntity.setOptionid(optionEntity.getId());
                    answerEntity.setUsername(answerName);
                    answerEntity = answerService.selectByPrimaryKey(answerEntity);
                    if (answerEntity != null) {
                        answers.add(answerEntity);
                    }
                });
            });
            // 告诉前端返回了答案
            httpResponseEntity.setCode("201");
        }else {
            // 不返回答案
            httpResponseEntity.setCode("200");
        }
        httpResponseEntity.setData(responseEntity);
        httpResponseEntity.setMessage("问卷查询成功");
        return httpResponseEntity;
    }

    @PostMapping("/queryQuestionnaires")
    public HttpResponseEntity queryQuestionnaireList(@RequestBody Map<String, String> info) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        String projectName = info.get("projectName");
        List<QuestionnaireEntity> questionnaireEntityList = questionnaireService.queryQuestionnaireByProjectName(projectName);
        if (questionnaireEntityList == null || questionnaireEntityList.size() == 0) {
            httpResponseEntity.setCode("0");
            httpResponseEntity.setMessage("没有问卷信息");
        }else {
            httpResponseEntity.setCode("200");
            httpResponseEntity.setData(questionnaireEntityList);
            httpResponseEntity.setMessage("问卷查询成功");
        }
        return httpResponseEntity;
    }

    @RequestMapping(value = "/addAnswers",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity addAnswers(@RequestBody List<AnswerEntity> answerEntityList){

        HttpResponseEntity httpResponseEntity =new HttpResponseEntity();
        int addresult=0;

        for (AnswerEntity answerEntity : answerEntityList) {
            addresult = answerService.insert(answerEntity);
        }

        if (addresult == 0){
            httpResponseEntity.setCode("0");
            httpResponseEntity.setData(0);
            httpResponseEntity.setMessage("添加答案失败");
        }
        else {
            httpResponseEntity.setCode("666");
            httpResponseEntity.setData(addresult);
            httpResponseEntity.setMessage("添加答案成功");
        }
        return httpResponseEntity;
    }

}
