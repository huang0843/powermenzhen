package com.menzhen.controller;

import com.menzhen.bean.Pdf;
import com.menzhen.bean.Register;
import com.menzhen.bean.Seek;
import com.menzhen.dao.SeekMapper;
import com.menzhen.util.PdfExport;
import com.menzhen.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
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

	@RequestMapping(value = "/pdf", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object  selectApplyHorseRegByApplyTypeStatus(Map<String, Object> o, HttpServletResponse response,
			String seekDocter,String seekName,String seekDrug,String seekProposal ) {
		String resultMap = "1";
		ResultBean bean=null;
		try {

			/***************************************主要是这里的东西*******************/
			Map<String, Object> map = new HashMap<String, Object>();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// 获取String类型的时间
			String date = sdf.format(new Date());

			map.put("hospital", "XX医院就诊报告单	");
			map.put("name",seekName);
			map.put("drugs", seekDrug);
			map.put("advisers", seekProposal);
			map.put("doctorname", seekDocter);
			map.put("time", date);// date

			//图片
			Map<String, Object> map2 = new HashMap<String, Object>();
//			map2.put("img", "D:\\bgimg.jpg");//

			o.put("datemap", map);
			o.put("imgmap", map2);
			PdfExport.pdfout(o, response);
			/***********************************************************************/
			resultMap ="成功" ;
			bean=new ResultBean(ResultBean.Code.SUCCESS);//枚举写法


		} catch (Exception e) {
			e.printStackTrace();
			bean=new ResultBean(ResultBean.Code.EXCEPTION);
			bean.setMsg(e.getMessage());
		}

		return bean;
	}

	@RequestMapping("selectDrug")
	@ResponseBody
	public <register> Object selectDrug(@RequestParam(required = false) Integer number,
			@RequestParam(required = false) String name)
	{
		ResultBean bean=null;
		if(name==""){
			name=null;
		}
		try {
			Map map=new HashMap();
			map.put("seek_number",number);
			map.put("seek_name",name);
			List<Seek> list=seekMapper.selectDrug(map);
			bean=new ResultBean(ResultBean.Code.SUCCESS);//枚举写法
			bean.setObj(list);
		}catch (Exception e){
			e.printStackTrace();
			bean=new ResultBean(ResultBean.Code.EXCEPTION);
			bean.setMsg(e.getMessage());
		}
		return bean;
	}

	@RequestMapping("SeekList")
	@ResponseBody
	public <register> Object findallbyNumber(@RequestParam(required = false) Integer number,
			@RequestParam(required = false) String name)
	{
		ResultBean bean=null;
		try {
			Map map=new HashMap();
			map.put("register_number",number);
			map.put("register_name",name);
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

	//保存就诊记录
	@RequestMapping("seekSave")
	@ResponseBody
	public Object save(Seek seek)
	{
		ResultBean bean=null;
		try {
			int rows=0;
			seek.setSeekDate(new Date());
			rows=seekMapper.insertSelective(seek);
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
