package com.menzhen.dao;

import com.menzhen.bean.RoleMenuRF;

import java.util.List;

public interface RoleMenuRFMapper {
    int deleteByPrimaryKey(Integer rmrfId);

    int insert(RoleMenuRF record);

    int insertSelective(RoleMenuRF record);

    RoleMenuRF selectByPrimaryKey(Integer rmrfId);

    int updateByPrimaryKeySelective(RoleMenuRF record);

    int updateByPrimaryKey(RoleMenuRF record);

    int insertAll(List<RoleMenuRF> list);
    int delectbyroleid(Integer roleid);

    List<RoleMenuRF> selectByroleid(Integer roleid);
}