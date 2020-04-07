package com.menzhen.dao;

import com.menzhen.bean.Paydrug;

import java.util.List;
import java.util.Map;

public interface PaydrugMapper {
    int deleteByPrimaryKey(Integer pdId);

    int insert(Paydrug record);

    int insertSelective(Paydrug record);

    Paydrug selectByPrimaryKey(Integer pdId);

    int updateByPrimaryKeySelective(Paydrug record);

    int updateByPrimaryKey(Paydrug record);

    List<Paydrug> selectAll(Map map);
}
