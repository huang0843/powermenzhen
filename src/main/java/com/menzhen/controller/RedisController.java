package com.menzhen.controller;

import com.menzhen.bean.Manager;
import com.menzhen.dao.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("redis")
public class RedisController {
    @Autowired
    RedisTemplate template;
    @Autowired
    ManagerMapper mapper;

    @RequestMapping("manager")
    @ResponseBody
    public String initRedis(){
        List<Manager> list=mapper.selectAll(new HashMap());//存进数据库
        ValueOperations<String,Manager> valueOperations=template.opsForValue();
        for (Manager m:list
             ) {
            valueOperations.set(m.getManagerPhone(),m);
        }
          return "ok";

//        HashOperations hashOperations=template.opsForHash();//hashOperations
//        HashMap map=new HashMap();
//        map.put("1","a");
//        map.put("1","b");
//        map.put("1","c");
//        hashOperations.putAll("testmap",map);
//        hashOperations.put("testmap","4","d");
//        Object obj=hashOperations.get("testmap","1");
//
//        SetOperations setOperations=template.opsForSet();
//        setOperations.add("users","里斯","王五","老刘");
//        Set<String> set=new HashSet<>();
//        set.add("里斯");
//        set.add("王五");

    }
}
