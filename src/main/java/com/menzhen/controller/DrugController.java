package com.menzhen.controller;

import com.menzhen.bean.Drug;
import com.menzhen.bean.Register;
import com.menzhen.bean.Role;
import com.menzhen.dao.DrugMapper;
import com.menzhen.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: huanghui
 * @Date: 2020/3/29  21:17
 **/
@Controller
@RequestMapping("admin/menzhen")
public class DrugController {
	@Autowired
	private DrugMapper drugMapper;

	@RequestMapping("drughtml")
	public String page(){
		return "admin/menzhen/drug";
	}


	@RequestMapping("drugName")
	@ResponseBody
	public Object findName( )
	{
		ResultBean bean=null;
		try {
			List<String> list=drugMapper.selectName();
			bean=new ResultBean(ResultBean.Code.SUCCESS);//枚举写法
			bean.setObj(list);
		}catch (Exception e){
			e.printStackTrace();
			bean=new ResultBean(ResultBean.Code.EXCEPTION);
			bean.setMsg(e.getMessage());
		}
		return bean;
	}

	@RequestMapping("druglist")
	@ResponseBody
	public Object findall(@RequestParam(required = false) String name,
			@RequestParam(required = false) String sort )
	{
		ResultBean bean=null;
		Map map=new HashMap();
		map.put("drug_name",name);
		map.put("drug_sort",sort);
		try {
			List<Drug> list=drugMapper.selectAll(map);
			bean=new ResultBean(ResultBean.Code.SUCCESS);//枚举写法
			bean.setObj(list);
		}catch (Exception e){
			e.printStackTrace();
			bean=new ResultBean(ResultBean.Code.EXCEPTION);
			bean.setMsg(e.getMessage());
		}
		return bean;
	}
//
//	//保存与更新
//	@RequestMapping("save")
//	@ResponseBody
//	public Object save(Drug drug)
//	{
//		ResultBean bean=null;
//		try {
//			int rows=0;
//			rows=drugMapper.insertSelective(drug);
//			if(rows>0){
//				bean=new ResultBean(ResultBean.Code.SUCCESS);//枚举写法
//			}else {
//				bean=new ResultBean(ResultBean.Code.FAIL);//枚举写法
//			}
//		}catch (Exception e){
//			e.printStackTrace();
//			bean=new ResultBean(ResultBean.Code.EXCEPTION);
//			bean.setMsg(e.getMessage());
//		}
//		return bean;
//	}
}
