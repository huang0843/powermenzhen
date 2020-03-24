package com.menzhen.controller;
import com.menzhen.bean.Manager;
import com.menzhen.bean.SystemMenu;
import com.menzhen.dao.SystemMenuMapper;
import com.menzhen.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("admin/menu")
public class SystemMenuController extends BaseController{
    @Autowired
    private SystemMenuMapper mapper;
    @RequestMapping("tree")
    public String page(){
        return "admin/menu/tree";
    }

    @RequestMapping("list")
    @ResponseBody
    public Object findall()
    {
        ResultBean bean=null;
        try {
            List<SystemMenu> list=mapper.selectAll();
            List<SystemMenu> temp=new ArrayList<>();
            for (SystemMenu s:list
                 ) {
                for (SystemMenu s2:list
                     ) {
                        if(s.getMenuId().intValue()==s2.getMenuPid()){
                            s.getList().add(s2);
                            temp.add(s2);
                        }
                }
            }
            list.removeAll(temp);
            bean=new ResultBean(ResultBean.Code.SUCCESS);//枚举写法
            bean.setObj(list);
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
    public Object save(SystemMenu menu)
    {
        ResultBean bean=null;
        try {
            int rows=0;
            if(menu.getMenuId()!=null){
                menu.setMenuLastmodify(new Date());
                rows=mapper.updateByPrimaryKeySelective(menu);//更新
            }else {
                menu.setMenuCreatetime(new Date());
                menu.setMenuLastmodify(new Date());
                rows=mapper.insertSelective(menu);
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

    @RequestMapping("manager")
    @ResponseBody
    public Object findbymanagerid()
    {
        ResultBean bean=null;
        try {
            Manager manager=getLoginManager();
            List<SystemMenu> list=mapper.selectbyManagerid(7);
            List<SystemMenu> temp=new ArrayList<>();
            for (SystemMenu s:list
            ) {
                for (SystemMenu s2:list
                ) {
                    if(s.getMenuId().intValue()==s2.getMenuPid()){
                        s.getList().add(s2);
                        temp.add(s2);
                    }
                }
            }
            list.removeAll(temp);
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
