package com.menzhen.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class PdfExport {
	// 利用模板生成pdf
	public static void pdfout(Map<String, Object> o, HttpServletResponse response) {
		// 模板路径
		String templatePath = "D:\\workspace_idea\\powermenzhen\\src\\main\\web\\static\\template\\hospital.pdf";

		PdfReader reader;
		ServletOutputStream out;
		ByteArrayOutputStream bos;
		PdfStamper stamper;
		try {
			//字体设置没用，但好像又有用。有用的话最好，没用的话试试“最后”的“1”
//			BaseFont bf = BaseFont.createFont("F:/华文新魏.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
			// 不同字体（这里定义为同一种字体：包含不同字号、不同style）
			BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			out = response.getOutputStream();// 输出流
			reader = new PdfReader(templatePath);// 读取pdf模板
			bos = new ByteArrayOutputStream();
			stamper = new PdfStamper(reader, bos);
			AcroFields form = stamper.getAcroFields();
			//文字类的内容处理
			Map<String,String> datemap = (Map<String,String>)o.get("datemap");
//          form.addSubstitutionFont(bf);//字体
          form.addSubstitutionFont(bfChinese);//字体
			for(String key : datemap.keySet()){
				String value = datemap.get(key);
				form.setField(key,value);
			}
			//图片类的内容处理
			Map<String,String> imgmap = (Map<String,String>)o.get("imgmap");
			for(String key : imgmap.keySet()) {
				String value = imgmap.get(key);
				String imgpath = value;
				int pageNo = form.getFieldPositions(key).get(0).page;
				Rectangle signRect = form.getFieldPositions(key).get(0).position;
				float x = signRect.getLeft();
				float y = signRect.getBottom();
				//根据路径读取图片
				Image image = Image.getInstance(imgpath);
				//获取图片页面
				PdfContentByte under = stamper.getOverContent(pageNo);
				//图片大小自适应
				image.scaleToFit(signRect.getWidth(), signRect.getHeight());
				//添加图片
				image.setAbsolutePosition(x, y);
				under.addImage(image);
			}
			stamper.setFormFlattening(true);// 如果为false，生成的PDF文件可以编辑，如果为true，生成的PDF文件不可以编辑
			stamper.close();
			Document doc = new Document();

			PdfCopy copy = new PdfCopy(doc, out);
			doc.open();
			PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
			copy.addPage(importPage);
			doc.close();
			out.flush();
			out.close();

		} catch (IOException e) {
			System.out.println(e);
		} catch (DocumentException e) {
			System.out.println(e);
		}

	}
}
