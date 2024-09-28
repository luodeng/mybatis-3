package com.roden.mybatis.dao;

import com.roden.mybatis.domain.UserDO;

public interface UserDAO {
  UserDO getById(Integer id);
  UserDO getByUserName(String userName);
}
