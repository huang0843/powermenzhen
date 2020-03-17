package com.menzhen.dao;

import com.menzhen.bean.ManagerRoleRF;

import java.util.List;

public interface ManagerRoleRFMapper {
    int deleteByPrimaryKey(Integer rfId);

    int insert(ManagerRoleRF record);

    int insertSelective(ManagerRoleRF record);

    ManagerRoleRF selectByPrimaryKey(Integer rfId);

    int updateByPrimaryKeySelective(ManagerRoleRF record);

    int updateByPrimaryKey(ManagerRoleRF record);
    int delectbyManagerid(Integer managerid);
    int insertAll(List<ManagerRoleRF> list);
}