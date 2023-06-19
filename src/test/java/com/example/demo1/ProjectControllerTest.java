package com.example.demo1;

import com.alibaba.fastjson2.JSON;
import com.example.demo1.common.util.UUIDUtil;
import com.example.demo1.dao.entity.ProjectEntity;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Timestamp;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@Rollback()
public class ProjectControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testProjectQueryAll() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/queryProjectList")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                                     .content(JSON.toJSONString(projectEntity))
                                     .accept(MediaType.APPLICATION_JSON))
                                     .andExpect(MockMvcResultMatchers.status().isOk())
                                     .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    public void testProjectAdd() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("1");
        projectEntity.setUserId("12345");
        projectEntity.setProjectName("测试项目");
        projectEntity.setProjectContent("测试项目内容");
        projectEntity.setCreatedBy("admin");
        projectEntity.setLastUpdatedBy("admin");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/addProjectInfo")
                                     .contentType(MediaType.APPLICATION_JSON_UTF8)
                                     .content(JSON.toJSONString(projectEntity))
                                     .accept(MediaType.APPLICATION_JSON))
                                     .andExpect(MockMvcResultMatchers.status().isOk())
                                     .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    public void testProjectDelete() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("615d3db5630541f7bb2bb652833f6a7c");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/deleteProjectById")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(JSON.toJSONString(projectEntity))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    public void testProjectUpdate() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("283dcf241cf245aea824dc10bbb3d680");
        projectEntity.setUserId("8ceeee2995f3459ba1955f85245dc7a5");
        projectEntity.setProjectName("第一个项目");
        projectEntity.setProjectContent("第一个项目描述 问问");
        projectEntity.setCreatedBy("admin");
        projectEntity.setLastUpdatedBy("admin");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/modifyProjectInfo")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(JSON.toJSONString(projectEntity))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }
}
