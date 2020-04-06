package com.menzhen.controller;

import com.menzhen.bean.Pay;
import com.menzhen.dao.PayMapper;
import com.menzhen.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: huanghui
 * @Date: 2020/4/6  12:17
 **/
public class PayControler extends BaseController {
	@Autowired
	private PayMapper payMapper;

	@RequestMapping("payhtml")
	public String page(){
		return "admin/menzhen/pay";
	}

	@RequestMapping("payName")
	@ResponseBody
	public Object findName(@RequestParam(required = false) String name)
	{
		ResultBean bean=null;
		Map map=new HashMap();
		map.put("drug_name",name);
		try {
			List<Pay> list=payMapper.selectName(map);
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
