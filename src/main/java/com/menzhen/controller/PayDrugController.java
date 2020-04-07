package com.menzhen.controller;

import com.menzhen.bean.Drug;
import com.menzhen.bean.Paydrug;
import com.menzhen.dao.DrugMapper;
import com.menzhen.dao.PaydrugMapper;
import com.menzhen.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @Author: huanghui
 * @Date: 2020/4/6  23:05
 **/
@Controller
@RequestMapping("admin/menzhen")
public class PayDrugController {

	@Autowired
	public PaydrugMapper paydrugMapper;
	@Autowired
	public DrugMapper drugMapper;

	//保存开药记录
	@RequestMapping("seekPayDrug")
	@ResponseBody
	public Object save(Paydrug paydrug)
	{
		ResultBean bean=null;
		try {
			int rows=0;
			String name1=paydrug.getPdName1();
			Integer num1=paydrug.getPdDay1();
			Drug date1= drugMapper.selectMoney(name1);//根据药名查 单价，余数，
			Float money1=date1.getDrugMoney();  //单价
			Integer number=date1.getDrugCount();  //仓库数
			Integer drugId=date1.getDrugId();  //id;
			int yu1=number.intValue()-num1.intValue();


			Drug drug=new Drug();
			drug.setDrugId(drugId);        //设置id；
			drug.setDrugCount(yu1);        //设置余数
//			drugMapper.updateByPrimaryKeySelective(drug);  //更新库存

			String name2=paydrug.getPdName2();
			if(name2!=null&&!name2.equals("")){
				Integer num2=paydrug.getPdDay2();
				Drug date2=drugMapper.selectMoney(name2);//根据药名查 单价，余数，
				Float money2=date2.getDrugMoney();
				Integer number2=date2.getDrugCount();
				Integer drugId2=date2.getDrugId();       //获取id;
				int yu2=number2.intValue()-num2.intValue();

				Drug drug2=new Drug();
				drug2.setDrugId(drugId2);        //设置id；
				drug2.setDrugCount(yu2);        //设置余数
				drugMapper.updateByPrimaryKeySelective(drug2);  //更新库存

				Float allMoney=money1*num1+money2*num2;
				paydrug.setPdMoneyall(allMoney);
			}else {
				Float allMoney=money1*num1;
				paydrug.setPdMoneyall(allMoney);
			}

			rows=paydrugMapper.insertSelective(paydrug);
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
