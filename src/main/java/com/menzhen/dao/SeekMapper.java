package com.menzhen.dao;

import com.menzhen.bean.Seek;

import java.util.List;
import java.util.Map;

public interface SeekMapper {
    int deleteByPrimaryKey(Integer seekId);

    int insert(Seek record);

    int insertSelective(Seek record);

    Seek selectByPrimaryKey(Integer seekId);

    int updateByPrimaryKeySelective(Seek record);
    int update2(Seek record);

    int updateByPrimaryKey(Seek record);

    List<Seek> selectAll(Map map);

    List<Seek> selectDrug(Map map);
}
