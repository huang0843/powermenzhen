package com.menzhen.dao;

import com.menzhen.bean.Drug;

import java.util.List;
import java.util.Map;

public interface DrugMapper {
    int deleteByPrimaryKey(Integer drugId);

    int insert(Drug record);

    int insertSelective(Drug record);

    Drug selectByPrimaryKey(Integer drugId);

    int updateByPrimaryKeySelective(Drug record);

    int updateByPrimaryKey(Drug record);

    List<Drug> selectAll(Map map);

    List<String> selectName();

    Drug selectMoney(String drug_name);
}
