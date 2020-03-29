package com.menzhen.bean;

import java.io.Serializable;
import java.util.Date;

public class Drug implements Serializable {
    private Integer drugId;

    private String drugName;

    private String drugFunction;

    private Float drugMoney;

    private String drugSort;

    private Integer drugNumber;

    private Integer drugCount;

    private Date drugCreattime;

    private Date drugLasttime;

    private static final long serialVersionUID = 1L;

    public Integer getDrugId() {
        return drugId;
    }

    public void setDrugId(Integer drugId) {
        this.drugId = drugId;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName == null ? null : drugName.trim();
    }

    public String getDrugFunction() {
        return drugFunction;
    }

    public void setDrugFunction(String drugFunction) {
        this.drugFunction = drugFunction == null ? null : drugFunction.trim();
    }

    public Float getDrugMoney() {
        return drugMoney;
    }

    public void setDrugMoney(Float drugMoney) {
        this.drugMoney = drugMoney;
    }

    public String getDrugSort() {
        return drugSort;
    }

    public void setDrugSort(String drugSort) {
        this.drugSort = drugSort == null ? null : drugSort.trim();
    }

    public Integer getDrugNumber() {
        return drugNumber;
    }

    public void setDrugNumber(Integer drugNumber) {
        this.drugNumber = drugNumber;
    }

    public Integer getDrugCount() {
        return drugCount;
    }

    public void setDrugCount(Integer drugCount) {
        this.drugCount = drugCount;
    }

    public Date getDrugCreattime() {
        return drugCreattime;
    }

    public void setDrugCreattime(Date drugCreattime) {
        this.drugCreattime = drugCreattime;
    }

    public Date getDrugLasttime() {
        return drugLasttime;
    }

    public void setDrugLasttime(Date drugLasttime) {
        this.drugLasttime = drugLasttime;
    }
}