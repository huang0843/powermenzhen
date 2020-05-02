package com.menzhen.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pay implements Serializable {
    private Integer payId;

    private String payName;

    private Float payRegister;

    private Float payDrug;

    private Float payAll;

    private Date payDate;

    private static final long serialVersionUID = 1L;

    private List<Register> register=new ArrayList<>();
    private List<Paydrug> paydrug=new ArrayList<>();

    public List<Register> getRegister() {
        return register;
    }

    public void setRegister(List<Register> register) {
        this.register = register;
    }

    public List<Paydrug> getPaydrug() {
        return paydrug;
    }

    public void setPaydrug(List<Paydrug> paydrug) {
        this.paydrug = paydrug;
    }

    private String registerName;
    private String registerType;
    private float registerMoney;
    private float pdMoneyall;

    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName;
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    public float getRegisterMoney() {
        return registerMoney;
    }

    public void setRegisterMoney(float registerMoney) {
        this.registerMoney = registerMoney;
    }

    public float getPdMoneyall() {
        return pdMoneyall;
    }

    public void setPdMoneyall(float pdMoneyall) {
        this.pdMoneyall = pdMoneyall;
    }

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
