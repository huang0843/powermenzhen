package com.menzhen.bean;

import java.io.Serializable;

public class Paydrug implements Serializable {
    private Integer pdId;

    private Integer pdNumber;

    private String pdName1;

    private Integer pdDay1;

    private String pdName2;

    private Integer pdDay2;

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

    public Integer getPdDay1() {
        return pdDay1;
    }

    public void setPdDay1(Integer pdDay1) {
        this.pdDay1 = pdDay1;
    }

    public String getPdName2() {
        return pdName2;
    }

    public void setPdName2(String pdName2) {
        this.pdName2 = pdName2 == null ? null : pdName2.trim();
    }

    public Integer getPdDay2() {
        return pdDay2;
    }

    public void setPdDay2(Integer pdDay2) {
        this.pdDay2 = pdDay2;
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