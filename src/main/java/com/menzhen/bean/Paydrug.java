package com.menzhen.bean;

import java.io.Serializable;

public class Paydrug implements Serializable {
    private Integer pdId;

    private Integer pdNumber;

    private String pdName1;

    private Float pdMoney1;

    private String pdName2;

    private Float pdMoney2;

    private String pdPatient;

    private Float pdMoneyall;

    private static final long serialVersionUID = 1L;

    public Integer getPdId() {
        return pdId;
    }

    public void setPdId(Integer pdId) {
        this.pdId = pdId;
    }

    public Integer getPdNumber() {
        return pdNumber;
    }

    public void setPdNumber(Integer pdNumber) {
        this.pdNumber = pdNumber;
    }

    public String getPdName1() {
        return pdName1;
    }

    public void setPdName1(String pdName1) {
        this.pdName1 = pdName1 == null ? null : pdName1.trim();
    }

    public Float getPdMoney1() {
        return pdMoney1;
    }

    public void setPdMoney1(Float pdMoney1) {
        this.pdMoney1 = pdMoney1;
    }

    public String getPdName2() {
        return pdName2;
    }

    public void setPdName2(String pdName2) {
        this.pdName2 = pdName2 == null ? null : pdName2.trim();
    }

    public Float getPdMoney2() {
        return pdMoney2;
    }

    public void setPdMoney2(Float pdMoney2) {
        this.pdMoney2 = pdMoney2;
    }

    public String getPdPatient() {
        return pdPatient;
    }

    public void setPdPatient(String pdPatient) {
        this.pdPatient = pdPatient == null ? null : pdPatient.trim();
    }

    public Float getPdMoneyall() {
        return pdMoneyall;
    }

    public void setPdMoneyall(Float pdMoneyall) {
        this.pdMoneyall = pdMoneyall;
    }
}