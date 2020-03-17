package com.menzhen.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

//dateConvert 日期转换器
@Component
public class DateConvert implements Converter <String, Date>{
    //从前台接受的类型。
    SimpleDateFormat []  simpleDateFormats=new SimpleDateFormat[]{
            new SimpleDateFormat("yyyy-MM-dd"),
            new SimpleDateFormat("yyyy/MM/dd"),
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
            new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"),
    };

    @Override
    public Date convert(String s) {
        Date date=new Date();
        for (SimpleDateFormat sdf:simpleDateFormats
             ) {
            try {
                date=sdf.parse(s);//把接受的日期强转，出错就continue下一个
            }catch (Exception e){
                continue;
            }
        }
        return date;
    }
}
