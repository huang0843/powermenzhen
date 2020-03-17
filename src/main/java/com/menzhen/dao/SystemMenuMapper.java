package com.menzhen.dao;

import com.menzhen.bean.SystemMenu;

import java.util.List;

public interface SystemMenuMapper {
    int deleteByPrimaryKey(Integer menuId);

    int insert(SystemMenu record);

    int insertSelective(SystemMenu record);

    SystemMenu selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(SystemMenu record);

    int updateByPrimaryKey(SystemMenu record);

    List<SystemMenu> selectAll();

    List<SystemMenu> selectbyManagerid(Integer managerid);
}