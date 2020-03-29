package com.menzhen.controller;

import com.menzhen.bean.Register;
import com.menzhen.dao.RegisterMapper;
import com.menzhen.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: huanghui
 * @Date: 2020/3/28  14:28
 **/
@Controller
@RequestMapping("admin/menzhen")
public class RegisterController extends BaseController{
 	@Autowired
	private RegisterMapper registerMapper;

	@RequestMapping("html")
	public String page(){
		return "admin/menzhen/guahao";
	}

	@RequestMapping("list")
	@ResponseBody
	public Object findall()
	{
		ResultBean bean=null;
		try {
			List<Register> list=registerMapper.selectAll();
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
	public Object save(Register register)
	{
		ResultBean bean=null;
		try {
				int rows=0;
				register.setRegisterDate(new Date());
				rows=registerMapper.insertSelective(register);
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
