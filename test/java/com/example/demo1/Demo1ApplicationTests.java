package com.example.demo1;

import com.example.demo1.common.util.UUIDUtil;
import com.example.demo1.dao.ProjectEntityMapper;
import com.example.demo1.dao.UserEntityMapper;
import com.example.demo1.dao.entity.ProjectEntity;
import com.example.demo1.dao.entity.UserEntity;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;


import org.apache.log4j.Logger;

//@SpringBootTest
class Demo1ApplicationTests {
//    @Test
//    void contextLoads() {

//    }
    Logger log = Logger.getLogger(Demo1ApplicationTests.class);
//    @Test
    public void queryUserList() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        UserEntity userEntity = new UserEntity();
        List<UserEntity> list = userEntityMapper.queryUserList(userEntity);
        if(CollectionUtils.isEmpty(list)){
            // 记录error级别的信息
        }else{
            System.out.println(list);
            // 记录info级别的信息
            log.info(">>queryUserList用户列表查询测试成功");
        }
    }

    //@Test
    public void selectUserInfo() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("admin");
        userEntity.setPassword("123");
        List<UserEntity> list = userEntityMapper.selectUserInfo(userEntity);
        if(CollectionUtils.isEmpty(list)){
            // 记录error级别的信息
        }else{
            System.out.println(list);
            // 记录info级别的信息
            log.info(">>qselectUserInfo用户登录测试成功");
        }
    }

    @Test
    public void insert() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        UserEntity userEntity = new UserEntity();
        userEntity.setId(UUIDUtil.getOneUUID());
        userEntity.setStatus("1");
        userEntity.setUsername("LS");
        userEntity.setPassword("123");
        int i = userEntityMapper.insert(userEntity);
        if(i==0){
            // 记录error级别的信息
        }else{
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>insert用户插入测试成功");
        }
    }

    //@Test
    public void deleteUserByName() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("LS");
        int i = userEntityMapper.deleteUserByName(userEntity);
        System.out.println(i);
        if(i==0){
            // 记录error级别的信息
        }else{
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>delete用户删除测试成功");
        }
    }

    private ProjectEntityMapper getProjectEntityMapper() {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProjectEntityMapper mapper = sqlSession.getMapper(ProjectEntityMapper.class);
        return mapper;
    }

    // 测试项目的增删改查
    @Test
    public void ProjectTest() throws IOException {
        ProjectEntityMapper mapper = getProjectEntityMapper();
// 测试queryList方法
        List<ProjectEntity> list = mapper.queryProjectList(null);
        if (list.isEmpty()) {
// 记录error级别的信息
            log.error("查询失败");
        } else {
            System.out.println(list);
// 记录info级别的信息
            log.info(">>queryProjectListTest项目列表查询测试成功");
        }

// 测试insert方法
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(UUIDUtil.getOneUUID());
        projectEntity.setUserId("1e5a47c78704070b5378fd5b");
        projectEntity.setProjectName("测试项目");
        projectEntity.setProjectContent("测试项目内容");
        projectEntity.setCreatedBy("admin");
        projectEntity.setCreationDate(new Date());
        projectEntity.setLastUpdatedBy("admin");
        projectEntity.setLastUpdateDate(new Date());
        int i = mapper.insert(projectEntity);
        if (i == 0) {
            log.error("插入失败");
        } else {
            System.out.println(i);
// 记录info级别的信息
            log.info(">>insertProjectTest项目插入测试成功");
        }

// 测试更新方法
        projectEntity.setProjectName("测试项目更新");
        i = mapper.updateByPrimaryKeySelective(projectEntity);
        if (i == 0) {
// 记录error级别的信息
            log.error("更新失败");
        } else {
            System.out.println(i);
// 记录info级别的信息
            log.info(">>updateByPrimaryKeySelectiveProjectTest项目更新测试成功");
        }

// 测试删除方法
        i = mapper.deleteProjectById(projectEntity);
        if (i == 0) {
            log.error("删除失败");
        } else {
            System.out.println(i);
// 记录info级别的信息
            log.info(">>deleteProjectByIdTest项目删除测试成功");
        }
    }


}
