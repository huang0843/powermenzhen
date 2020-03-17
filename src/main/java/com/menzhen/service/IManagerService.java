package com.menzhen.service;

import com.menzhen.bean.Manager;

import java.util.List;
import java.util.Map;

public interface IManagerService {
    List<Manager> selectAll(Map map);

    int saveManager(Manager manager);
    void saveManager2(Manager manager);
}
