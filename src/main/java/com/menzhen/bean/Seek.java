package com.menzhen.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Seek implements Serializable {
    private Integer seekId;

    private String seekDocter;

    private Integer seekNumber;

    private String seekName;

    private String seekProposal;

    private String seekDrug;

    private Integer seekDrugcount;

    private Date seekDate;

    private List<Register> register=new ArrayList<>();

    public List<Register> getRegister() {
        return register;
    }

    public void setRegister(List<Register> register) {
        this.register = register;
    }

    private Integer registerNumber;
    private String registerName;
    private String registerDoctor;

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
        this.registerName = registerName;
    }

    public String getRegisterDoctor() {
        return registerDoctor;
    }

    public void setRegisterDoctor(String registerDoctor) {
        this.registerDoctor = registerDoctor;
    }

    private static final long serialVersionUID = 1L;

    public Integer getSeekId() {
        return seekId;
    }

    public void setSeekId(Integer seekId) {
        this.seekId = seekId;
    }

    public String getSeekDocter() {
        return seekDocter;
    }

    public void setSeekDocter(String seekDocter) {
        this.seekDocter = seekDocter == null ? null : seekDocter.trim();
    }

    public Integer getSeekNumber() {
        return seekNumber;
    }

    public void setSeekNumber(Integer seekNumber) {
        this.seekNumber = seekNumber;
    }

    public String getSeekName() {
        return seekName;
    }

    public void setSeekName(String seekName) {
        this.seekName = seekName == null ? null : seekName.trim();
    }

    public String getSeekProposal() {
        return seekProposal;
    }

    public void setSeekProposal(String seekProposal) {
        this.seekProposal = seekProposal == null ? null : seekProposal.trim();
    }

    public String getSeekDrug() {
        return seekDrug;
    }

    public void setSeekDrug(String seekDrug) {
        this.seekDrug = seekDrug == null ? null : seekDrug.trim();
    }

    public Integer getSeekDrugcount() {
        return seekDrugcount;
    }

    public void setSeekDrugcount(Integer seekDrugcount) {
        this.seekDrugcount = seekDrugcount;
    }

    public Date getSeekDate() {
        return seekDate;
    }

    public void setSeekDate(Date seekDate) {
        this.seekDate = seekDate;
    }
}
