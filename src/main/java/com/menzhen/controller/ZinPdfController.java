package com.menzhen.controller;

import com.menzhen.util.PdfExport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: huanghui
 * @Date: 2020/5/3  22:59
 **/
@Controller
@RequestMapping("/zinPdf")
public class ZinPdfController {

//	@RequestMapping(value = "/pdf", produces = "application/json; charset=utf-8")
//	@ResponseBody
//	public Object  selectApplyHorseRegByApplyTypeStatus(Map<String, Object> o, HttpServletResponse response) {
//		String resultMap = "1";
//		try {
//			/***************************************主要是这里的东西*******************/
//			Map<String, Object> map = new HashMap<String, Object>();
//
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
//			// 获取String类型的时间
//			String date = sdf.format(new Date());
//
//			map.put("date", date);// date
//			map.put("weather", "晴");
//			map.put("todayF", "阳光明媚");
//
//			//图片
//			Map<String, Object> map2 = new HashMap<String, Object>();
//			map2.put("img", "D:\\bgimg.jpg");//
//
//			o.put("datemap", map);
//			o.put("imgmap", map2);
//			PdfExport.pdfout(o, response);
//			/***********************************************************************/
//			resultMap ="成功" ;
//
//		} catch (Exception e) {
//			resultMap = e.getLocalizedMessage();
//		}
//
//		return resultMap;
//	}
}

