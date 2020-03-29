package com.menzhen.controller;

import com.menzhen.bean.Register;
import com.menzhen.bean.Seek;
import com.menzhen.dao.SeekMapper;
import com.menzhen.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: huanghui
 * @Date: 2020/3/28  18:27
 **/
@Controller
@RequestMapping("admin/menzhen")
public class SeekController {
	@Autowired
	private SeekMapper seekMapper;

	@RequestMapping("seekhtml")
	public String page(){
		return "admin/menzhen/seek";
	}


	@RequestMapping("SeekList")
	@ResponseBody
	public <register> Object findallbyNumber(@RequestParam(required = false) Integer number,
			@RequestParam(required = false) String name)
	{
		ResultBean bean=null;
		try {
			Map map=new HashMap();
			map.put("seek_number",number);
			map.put("seek_name",name);
			List<Seek> list=seekMapper.selectAll(map);
			Register register=new Register();
			for (int i = 0; i <list.size() ; i++) {
				for (Register r:list.get(i).getRegister()
				) {
                  list.get(i).setRegisterNumber(r.getRegisterNumber());
                  list.get(i).setRegisterName(r.getRegisterName());
                  list.get(i).setRegisterDoctor(r.getRegisterDoctor());
				}
			}
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
