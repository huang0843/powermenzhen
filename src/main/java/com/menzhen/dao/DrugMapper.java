package com.menzhen.dao;

import com.menzhen.bean.Drug;

public interface DrugMapper {
    int deleteByPrimaryKey(Integer drugId);

    int insert(Drug record);

    int insertSelective(Drug record);

    Drug selectByPrimaryKey(Integer drugId);

    int updateByPrimaryKeySelective(Drug record);

    int updateByPrimaryKey(Drug record);
}