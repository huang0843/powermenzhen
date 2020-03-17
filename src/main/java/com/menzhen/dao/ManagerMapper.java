package com.menzhen.dao;

import com.menzhen.bean.Manager;

import java.util.List;
import java.util.Map;

public interface ManagerMapper {
    int deleteByPrimaryKey(Integer managerId);

    int insert(Manager record);

    int insertSelective(Manager record);

    Manager selectByPrimaryKey(Integer managerId);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);

    //map.put ("param",param)
    //map.put ("phone",phone) map.put("id",id)
    List<Manager> selectAll(Map map);
    int insertAll(List<Manager> list);

    int deletall(List<String> list);
    List<Manager> login(Map map);
}