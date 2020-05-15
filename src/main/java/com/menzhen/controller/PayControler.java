package com.menzhen.controller;

import com.menzhen.bean.Pay;
import com.menzhen.bean.Paydrug;
import com.menzhen.bean.Register;
import com.menzhen.bean.Seek;
import com.menzhen.dao.PayMapper;
import com.menzhen.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @Description:
 * @Author: huanghui
 * @Date: 2020/4/6  12:17
 **/
@Controller
@RequestMapping("admin/menzhen")
public class PayControler extends BaseController {
	@Autowired
	private PayMapper payMapper;

	@RequestMapping("payhtml")
	public String page(){
		return "admin/menzhen/pay";
	}

	//保存缴纳记录
	@RequestMapping("paySave")
	@ResponseBody
	public Object save(Pay pay)
	{
		ResultBean bean=null;
		try {
			int rows=0;
			pay.setPayDate(new Date());
			float all=pay.getPayRegister()+pay.getPayDrug();
			pay.setPayAll(all);
			rows=payMapper.insertSelective(pay);
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

	@RequestMapping("selectAll")
	@ResponseBody
	public Object selectAll(@RequestParam(required = false) String name)
	{
		ResultBean bean=null;
		Map map=new HashMap();
		map.put("register_name",name);
		try {
			List<Pay> list=payMapper.selectAll(map);
			bean=new ResultBean(ResultBean.Code.SUCCESS);//枚举写法
			bean.setObj(list);
		}catch (Exception e){
			e.printStackTrace();
			bean=new ResultBean(ResultBean.Code.EXCEPTION);
			bean.setMsg(e.getMessage());
		}
		return bean;
	}

	@RequestMapping("payName")
	@ResponseBody
	public Object findName(@RequestParam(required = false) String name)
	{
		ResultBean bean=null;
		Map map=new HashMap();
		if(name!=null&&!"".equals(name)){
			map.put("register_name",name);
		}
		try {
			List<Pay> list=payMapper.selectName(map);
			int times=list.size();
			List<Pay> list2=new ArrayList<>();

			for (int i = 0; i <list.size() ; i++) {
				if(list.get(i).getPayName()!=null){
					continue;
				}else {
					for (Register r:list.get(i).getRegister()
					) {
						list.get(i).setRegisterName(r.getRegisterName());
						list.get(i).setRegisterType(r.getRegisterType());
						list.get(i).setRegisterMoney(r.getRegisterMoney());
					}
					for (Paydrug r:list.get(i).getPaydrug()
					) {
						list.get(i).setPdMoneyall(r.getPdMoneyall());
					}
					list2.add(list.get(i));
				}
			}
			bean=new ResultBean(ResultBean.Code.SUCCESS);//枚举写法
			bean.setObj(list2);
		}catch (Exception e){
			e.printStackTrace();
			bean=new ResultBean(ResultBean.Code.EXCEPTION);
			bean.setMsg(e.getMessage());
		}
		return bean;
	}

}
