package com.menzhen.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.menzhen.bean.Manager;
import com.menzhen.bean.ManagerRoleRF;
import com.menzhen.dao.ManagerMapper;
import com.menzhen.dao.ManagerRoleRFMapper;
import com.menzhen.util.Contents;
import com.menzhen.util.ResultBean;
import com.menzhen.util.poi.PoiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("admin/manager")
public class ManagerController {
    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private ManagerRoleRFMapper rfMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("html")
    public String page(){
        return "admin/manager/list";
    }

    @RequestMapping("zhuxiao")
    @ResponseBody
    public Object zhuxiao(HttpServletRequest request){
        ResultBean bean=null;
        request.getSession().removeAttribute(Manager.CURRENT_MANAGER);
        bean=new ResultBean(ResultBean.Code.SUCCESS);//枚举写法
        bean.setObj("util/login");
        return bean;
    }

    /**
     * 查询所有 管理者
     * @param index
     * @param size
     * @param param
     * @param phone
     * @return    结果bean
     */
    @RequestMapping("list")
    @ResponseBody
    public Object findall(Integer index, Integer size,String order,String prop,
                          @RequestParam(required = false) String param,
                          @RequestParam(required = false) String phone)
    {
        ResultBean bean=null;
        try {
            index=index==null?1:index;
            size=size==null?20:size;

            Map map=new HashMap();
            map.put("param",param);
            map.put("phone",phone);
            map.put("orderx",order);
            map.put("prop", Contents.to_col(prop));


            PageHelper.startPage(index,size);
            List<Manager> list=managerMapper.selectAll(map);
            PageInfo<Manager> info=new PageInfo<>(list);
            bean=new ResultBean(ResultBean.Code.SUCCESS);//枚举写法
            bean.setObj(info);

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
            int rows=managerMapper.deleteByPrimaryKey(id);
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

    //删除
    @RequestMapping("deleteall")
    @ResponseBody
    public Object deleteAll(@RequestParam("ids") String   ids)
    {
        ResultBean bean=null;
        try {
            String [] idsx=ids.split(",");
            List<String> list=Arrays.asList(idsx);
          int rows=managerMapper.deletall(list);
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
    public Object save(Manager manager)
    {
        System.out.println(manager.getManagerId());
        ResultBean bean=null;
        try {
            int rows=0;
            if(manager.getManagerId()!=null){
                manager.setManagerLastmodify(new Date());
                rows=managerMapper.updateByPrimaryKeySelective(manager);//更新
            }else {
                manager.setManagerCreatetime(new Date());
                manager.setManagerLastmodify(new Date());
                manager.setManagerPassword("666666");
                rows=managerMapper.insertSelective(manager);
            }

            if(rows>0){
                manager=managerMapper.selectByPrimaryKey(manager.getManagerId());
                redisTemplate.opsForValue().set(manager.getManagerPhone(),manager);
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

    //上传头像
    @RequestMapping("upload")
    @ResponseBody
    public Object upload(@RequestParam(value = "file",required = true) MultipartFile file,
                         HttpServletRequest request)
    {
        ResultBean bean=null;
        try {
            if(file!=null)
            {
             String filepath=request.getSession().getServletContext().getRealPath("/uploadFile");
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DATE);
                String dirName="/"+year+"/"+month+"/"+day;
                File file1=new File(filepath,dirName); //当日文件夹下的文件
                if(!file1.exists()){
                    boolean flag=file1.mkdirs();
                }
                String original=file.getOriginalFilename();
                String fliname=UUID.randomUUID().toString()+original.substring(original.lastIndexOf("."));
                //存贮在数据库中的地址
                String dbpath="/uploadFile"+dirName+"/"+fliname; //找到图片
                //目标文件
                File targetFile=new File(file1,fliname);
                file.transferTo(targetFile);  //copy 进去。

                bean=new ResultBean(ResultBean.Code.SUCCESS);//枚举写法
                bean.setObj(dbpath);
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

    //上传文件
    @RequestMapping("import")
    @ResponseBody
    public Object importManager(@RequestParam(value = "file",required = true) MultipartFile file,
                         HttpServletRequest request)
    {
        ResultBean bean=null;
        try {
            if(file!=null)
            {
                List<Manager>list=PoiUtil.importExcel(file,0,1,Manager.class);
                for (Manager s:list
                     ) {
                    s.setManagerPassword("666666");
                    s.setManagerCreatetime(new Date());
                    s.setManagerLastmodify(new Date());
                }
                int rows=managerMapper.insertAll(list);
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

    //删除
    @RequestMapping("savepower")
    @ResponseBody
    public Object savepower(String   roleids,Integer managerid)
    {
        ResultBean bean=null;
        try {
            String [] idsx=roleids.split(",");
            List<ManagerRoleRF> list=new ArrayList<>();
            for (String id:idsx
                 ) {
                ManagerRoleRF rf=new ManagerRoleRF();
                rf.setRfCreatetime(new Date());
                rf.setRfLastmodify(new Date());
                rf.setRfMangerid(managerid);
                rf.setRfRoleid(Integer.valueOf(id));
                list.add(rf);
            }
            int rows=0;
            rows=rfMapper.delectbyManagerid(managerid);
            if(list.size()>0){
                rows=rfMapper.insertAll(list);
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
}
