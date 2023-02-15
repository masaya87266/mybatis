package com.masaya.mybatis.mapper;

import com.masaya.mybatis.model.UserInfo;

public interface UserInfoMapper {

    UserInfo selectByPrimaryKey(Integer id);
}
