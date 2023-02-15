package com.masaya.mybatis.mapper;

import com.masaya.mybatis.model.OrderInfo;

public interface OrderInfoMapper {

    OrderInfo selectByPrimaryKey(Integer id);
}
