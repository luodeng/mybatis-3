package com.roden.mybatis;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.Properties;

/**
 * 1. java.util.date
 * 在除了SQL语句的情况下面使用
 * 日期格式：年月日时分秒
 * java.util.Date 是 java.sql.Date 的父类（注意拼写）
 * <p>
 * 2、java.sql.time 日期格式为：时分秒
 * <p>
 * 3、java.sql.date 日期格式：年月日[只存储日期数据不存储时间数据]
 * <p>
 * 4 java.sql.Timestamp 日期格式为：年月日时分秒纳秒（毫微秒）
 */
public class JdbcDemo {

  @Test
  public void query() throws SQLException {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      Properties properties = new Properties();
      properties.load(JdbcDemo.class.getResourceAsStream("config.properties"));

      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection(properties.get("url").toString() + "?serverTimezone=UTC", properties.get("username").toString(), properties.get("password").toString());
      //数据库元数据
      DatabaseMetaData dmd = conn.getMetaData();
      System.out.println(dmd.getDatabaseProductName() + dmd.getDatabaseProductVersion());
      //开启事务
      conn.setAutoCommit(false);
      ps = conn.prepareStatement("select id,user_name,birth_day,create_time from user;");
      rs = ps.executeQuery();
      //结果集元数据
      ResultSetMetaData rsmd = rs.getMetaData();
      int columnCount = rsmd.getColumnCount();
      for (int i = 1; i <= columnCount; i++) {
        System.out.println(rsmd.getColumnName(i));
        System.out.println(rsmd.getColumnClassName(i));
        System.out.println(rsmd.getSchemaName(i) + rsmd.getTableName(i));
        System.out.println(rsmd.getColumnTypeName(i));
      }
      while (rs.next()) {
        int num = rs.getInt("id");
        String name = rs.getString(2);
        Date birthDay = rs.getDate("birth_day");
        Timestamp createTime = rs.getTimestamp(4);
        System.out.print(num + "\t" + name + "\t" + birthDay + "\t" + createTime);
      }
      //try的最后提交事务
      conn.commit();
    } catch (Exception e) {
      //回滚事务
      //conn.rollback();
      e.printStackTrace();
    }
  }

  @Test
  public void update() throws SQLException {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      Properties properties = new Properties();
      properties.load(JdbcDemo.class.getResourceAsStream("config.properties"));

      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection(properties.get("url").toString() + "?serverTimezone=UTC", properties.get("username").toString(), properties.get("password").toString());
      //开启事务
      conn.setAutoCommit(false);
      ps = conn.prepareStatement("update user set create_time=? where id=?");
      ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
      ps.setInt(2, 1);
      System.out.println(ps.executeUpdate());
      //try的最后提交事务
      conn.commit();
    } catch (Exception e) {
      //回滚事务
      //conn.rollback();
      e.printStackTrace();
    }
  }
}
