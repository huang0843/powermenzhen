package com.menzhen.dao;

import com.menzhen.bean.Pay;

import java.util.List;
import java.util.Map;

public interface PayMapper {
    int deleteByPrimaryKey(Integer payId);

    int insert(Pay record);

    int insertSelective(Pay record);

    Pay selectByPrimaryKey(Integer payId);

    int updateByPrimaryKeySelective(Pay record);

    int updateByPrimaryKey(Pay record);

    List<Pay> selectName(Map map);
}
