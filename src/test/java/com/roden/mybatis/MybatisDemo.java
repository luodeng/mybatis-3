package com.roden.mybatis;

import com.roden.mybatis.dao.IdCardDAO;
import com.roden.mybatis.dao.UserDAO;
import com.roden.mybatis.domain.IdCardDO;
import com.roden.mybatis.domain.UserDO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.time.LocalDateTime;

/**
 * XMLConfigBuilder.parseConfiguration 解析配置到 Configuration
 * <p>
 * MapperProxyFactory.newInstance 生成代理接口对象，最张调用MapperProxy.invoke代理执行
 * <p>
 * SQL执行调用方法  通过debug可以复制出从整个执行过程

 query:64, PreparedStatementHandler (org.apache.ibatis.executor.statement)
 query:80, RoutingStatementHandler (org.apache.ibatis.executor.statement)
 doQuery:65, SimpleExecutor (org.apache.ibatis.executor)
 queryFromDatabase:336, BaseExecutor (org.apache.ibatis.executor)
 query:158, BaseExecutor (org.apache.ibatis.executor)
 query:110, CachingExecutor (org.apache.ibatis.executor)
 query:90, CachingExecutor (org.apache.ibatis.executor)
 selectList:154, DefaultSqlSession (org.apache.ibatis.session.defaults)
 selectList:147, DefaultSqlSession (org.apache.ibatis.session.defaults)
 selectList:142, DefaultSqlSession (org.apache.ibatis.session.defaults)
 selectOne:75, DefaultSqlSession (org.apache.ibatis.session.defaults)
 execute:87, MapperMethod (org.apache.ibatis.binding)
 invoke:141, MapperProxy$PlainMethodInvoker (org.apache.ibatis.binding)
 invoke:86, MapperProxy (org.apache.ibatis.binding)
 getById:-1, $Proxy9 (jdk.proxy2)
 getById:48, MybatisDemo (com.roden.mybatis)
 */

public class MybatisDemo {

  @Test
  public void getById() throws Exception {
    String resource = "com/roden/mybatis/mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    try (SqlSession session = sqlSessionFactory.openSession()) {
      UserDAO userDAO = session.getMapper(UserDAO.class);
      UserDO userDO = userDAO.getById(1);
      System.out.println(userDO.getUserName());

    }
  }

  @Test
  public void getByUserName() throws Exception {
    String resource = "com/roden/mybatis/mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    try (SqlSession session = sqlSessionFactory.openSession()) {
      UserDAO userDAO = session.getMapper(UserDAO.class);
      UserDO userDO = userDAO.getByUserName("roden");
      System.out.println(userDO.getUserName());

    }
  }

  @Test
  public void insert() throws Exception {
    String resource = "com/roden/mybatis/mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    try (SqlSession session = sqlSessionFactory.openSession(false)) {
      IdCardDAO idCardDAO = session.getMapper(IdCardDAO.class);
      IdCardDO idCardDO = new IdCardDO();
      idCardDO.setIdCardNo("431027199104233128");
      idCardDO.setIdCardName("林竹");
      idCardDO.setStatus(1);
      idCardDO.setCreateTime(LocalDateTime.now());
      idCardDO.setUpdateTime(idCardDO.getCreateTime());
      System.out.println(idCardDAO.save(idCardDO));
      System.out.println(idCardDO.getId());
      session.commit();
    } catch (Exception e) {
      //session.rollback();
    }
  }
}
