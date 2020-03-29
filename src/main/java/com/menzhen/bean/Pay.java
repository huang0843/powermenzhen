package com.menzhen.bean;

import java.io.Serializable;
import java.util.Date;

public class Pay implements Serializable {
    private Integer payId;

    private String payName;

    private Float payRegister;

    private Float payDrug;

    private Float payAll;

    private Date payDate;

    private static final long serialVersionUID = 1L;

    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName == null ? null : payName.trim();
    }

    public Float getPayRegister() {
        return payRegister;
    }

    public void setPayRegister(Float payRegister) {
        this.payRegister = payRegister;
    }

    public Float getPayDrug() {
        return payDrug;
    }

    public void setPayDrug(Float payDrug) {
        this.payDrug = payDrug;
    }

    public Float getPayAll() {
        return payAll;
    }

    public void setPayAll(Float payAll) {
        this.payAll = payAll;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }
}