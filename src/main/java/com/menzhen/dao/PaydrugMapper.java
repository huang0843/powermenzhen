package com.menzhen.dao;

import com.menzhen.bean.Paydrug;

public interface PaydrugMapper {
    int deleteByPrimaryKey(Integer pdId);

    int insert(Paydrug record);

    int insertSelective(Paydrug record);

    Paydrug selectByPrimaryKey(Integer pdId);

    int updateByPrimaryKeySelective(Paydrug record);

    int updateByPrimaryKey(Paydrug record);
}