package com.menzhen.controller;

import com.menzhen.bean.Manager;
import com.menzhen.dao.ManagerMapper;
import com.menzhen.util.ResultBean;
import com.menzhen.util.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("util")
public class UtilController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ManagerMapper mapper;

    @RequestMapping("code")
    public void  showcodeimg(HttpSession session, HttpServletResponse response){
        try {
            String code= VerifyCodeUtils.generateVerifyCode(4);
            session.setAttribute("code",code);
            int w=100;
            int h=44;
            VerifyCodeUtils.outputImage(w,h,response.getOutputStream(),code);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping("login")
    public String tologin(){
        return "/login/index";
    }

	@RequestMapping("huangying")
	public String huangying(){
		return "/admin/huanying";
	}

    @ResponseBody
    @RequestMapping("login/do")
    public Object login(String password,String username,
                        String code,HttpSession session){
        ResultBean bean=null;
        try {
           String mycode= (String) session.getAttribute("code");

           if(code.equalsIgnoreCase(mycode)){
               //删除验证码，避免充刷
               session.removeAttribute("code");
            Map map=new HashMap();//账号密码存进map
            map.put("username",username);
            map.put("password",password);

            //推进redis，改善服务器
            Object object=redisTemplate.opsForValue().get(username);
            if(object!=null){
                bean=new ResultBean(ResultBean.Code.SUCCESS);
                //取得第一个人
                session.setAttribute(Manager.CURRENT_MANAGER,(Manager)object);
            }else {
                bean=new ResultBean(ResultBean.Code.FAIL);
                bean.setMsg("账号密码不匹配");
            }

//                List<Manager> list=mapper.login(map);
//                if(list!=null&&list.size()>0){
//                    bean=new ResultBean(ResultBean.Code.SUCCESS);
//                    //取得第一个人
//                    session.setAttribute(Manager.CURRENT_MANAGER,list.get(0));
//                }else{
//                    bean=new ResultBean(ResultBean.Code.FAIL);
//                    bean.setMsg("账号密码不匹配");
//                }
           }else {
               bean=new ResultBean(ResultBean.Code.FAIL);
               bean.setMsg("验证码错误");
           }

        }catch (Exception e){
            e.printStackTrace();
            bean=new ResultBean(ResultBean.Code.EXCEPTION);
        }
        return bean;
    }
}
