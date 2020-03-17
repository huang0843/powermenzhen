package com.menzhen.controller;
import com.menzhen.bean.Role;
import com.menzhen.bean.RoleMenuRF;
import com.menzhen.dao.RoleMapper;
import com.menzhen.dao.RoleMenuRFMapper;
import com.menzhen.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("admin/role")
public class RoleController {

    @Autowired
    private RoleMapper mapper;
    @Autowired
    private RoleMenuRFMapper rfMapper;

    //页面展示
    @RequestMapping("html")
    public String page(){
        return "admin/role/list";
    }

    @RequestMapping("list")
    @ResponseBody
    public Object findall()
    {
        ResultBean bean=null;
        try {
            List<Role> list=mapper.selectAll();
            bean=new ResultBean(ResultBean.Code.SUCCESS);//枚举写法
            bean.setObj(list);
        }catch (Exception e){
            e.printStackTrace();
            bean=new ResultBean(ResultBean.Code.EXCEPTION);
            bean.setMsg(e.getMessage());
        }
        return bean;
    }

    //删除
    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Integer id)
    {
        ResultBean bean=null;
        try {
            int rows=mapper.deleteByPrimaryKey(id);
            if(rows>0){
                bean=new ResultBean(ResultBean.Code.SUCCESS);//枚举写法
            }else {
                bean=new ResultBean(ResultBean.Code.FAIL);//枚举写法
            }
        }catch (Exception e){
            e.printStackTrace();
            bean=new ResultBean(ResultBean.Code.EXCEPTION);
            bean.setMsg(e.getMessage());
        }
        return bean;
    }

    //保存与更新
    @RequestMapping("save")
    @ResponseBody
    public Object save(Role manager)
    {
        System.out.println(manager.getRoleId());
        ResultBean bean=null;
        try {
            int rows=0;
            if(manager.getRoleId()!=null){
                manager.setRoleLastmodify(new Date());
                rows=mapper.updateByPrimaryKeySelective(manager);//更新
            }else {
                manager.setRoleCreatetime(new Date());
                manager.setRoleLastmodify(new Date());
                rows=mapper.insertSelective(manager);
            }

            if(rows>0){
                bean=new ResultBean(ResultBean.Code.SUCCESS);//枚举写法
            }else {
                bean=new ResultBean(ResultBean.Code.FAIL);//枚举写法
            }
        }catch (Exception e){
            e.printStackTrace();
            bean=new ResultBean(ResultBean.Code.EXCEPTION);
            bean.setMsg(e.getMessage());
        }
        return bean;
    }

    //更新权利菜单
    @RequestMapping("power")
    @ResponseBody
    public Object power(String menuids,Integer roleid)
    {
        ResultBean bean=null;
        try {
            int rows=0;
            rows=rfMapper.delectbyroleid(roleid);
            if(menuids!=null&&menuids.length()>0) { //执行插入
                String[] ids = menuids.split("[,]");
                List<RoleMenuRF> list = new ArrayList<>();
                for (String id : ids
                ) {
                    RoleMenuRF rf = new RoleMenuRF();
                    rf.setRmrfMenuid(Integer.valueOf(id));
                    rf.setRmrfRoleid(roleid);
                    rf.setRmrfCreatetime(new Date());
                    rf.setRmrfLastmodify(new Date());
                    list.add(rf);
                }
                rows = rfMapper.insertAll(list);
            }
            if(rows>0){
                bean=new ResultBean(ResultBean.Code.SUCCESS);//枚举写法
            }else {
                bean=new ResultBean(ResultBean.Code.FAIL);//枚举写法
            }
        }catch (Exception e){
            e.printStackTrace();
            bean=new ResultBean(ResultBean.Code.EXCEPTION);
            bean.setMsg(e.getMessage());
        }
        return bean;
    }

    @RequestMapping("powerlist")
    @ResponseBody
    public Object powerlist(Integer roleid)
    {
        ResultBean bean=null;
        try {
            List<RoleMenuRF> list=rfMapper.selectByroleid(roleid);
            bean=new ResultBean(ResultBean.Code.SUCCESS);//枚举写法
            bean.setObj(list);
        }catch (Exception e){
            e.printStackTrace();
            bean=new ResultBean(ResultBean.Code.EXCEPTION);
            bean.setMsg(e.getMessage());
        }
        return bean;
    }

}
