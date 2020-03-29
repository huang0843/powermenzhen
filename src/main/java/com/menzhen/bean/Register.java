package com.menzhen.bean;

import java.io.Serializable;
import java.util.Date;

public class Register implements Serializable {
    private Integer registerId;

    private Integer registerNumber;

    private String registerName;

    private String registerReason;

    private String registerDoctor;

    private String registerType;

    private Float registerMoney;

    private Date registerDate;

    private static final long serialVersionUID = 1L;

    public Integer getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Integer registerId) {
        this.registerId = registerId;
    }

    public Integer getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(Integer registerNumber) {
        this.registerNumber = registerNumber;
    }

    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName == null ? null : registerName.trim();
    }

    public String getRegisterReason() {
        return registerReason;
    }

    public void setRegisterReason(String registerReason) {
        this.registerReason = registerReason == null ? null : registerReason.trim();
    }

    public String getRegisterDoctor() {
        return registerDoctor;
    }

    public void setRegisterDoctor(String registerDoctor) {
        this.registerDoctor = registerDoctor == null ? null : registerDoctor.trim();
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType == null ? null : registerType.trim();
    }

    public Float getRegisterMoney() {
        return registerMoney;
    }

    public void setRegisterMoney(Float registerMoney) {
        this.registerMoney = registerMoney;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}