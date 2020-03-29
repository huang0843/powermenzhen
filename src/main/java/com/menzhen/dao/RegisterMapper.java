package com.menzhen.dao;

import com.menzhen.bean.Register;

import java.util.List;

public interface RegisterMapper {
    int deleteByPrimaryKey(Integer registerId);

    int insert(Register record);

    int insertSelective(Register record);

    Register selectByPrimaryKey(Integer registerId);

    int updateByPrimaryKeySelective(Register record);

    int updateByPrimaryKey(Register record);

    List<Register> selectAll();
}
