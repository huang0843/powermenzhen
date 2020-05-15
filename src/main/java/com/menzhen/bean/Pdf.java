package com.menzhen.bean;

import java.io.Serializable;

/**
 * @Description:
 * @Author: huanghui
 * @Date: 2020/5/15  10:22
 **/
public class Pdf implements Serializable {
	private String seekDocter;
	private String seekName;
	private String seekDrug;
	private String seekProposal;

	public String getSeekDocter() {
		return seekDocter;
	}

	public void setSeekDocter(String seekDocter) {
		this.seekDocter = seekDocter;
	}

	public String getSeekName() {
		return seekName;
	}

	public void setSeekName(String seekName) {
		this.seekName = seekName;
	}

	public String getSeekDrug() {
		return seekDrug;
	}

	public void setSeekDrug(String seekDrug) {
		this.seekDrug = seekDrug;
	}

	public String getSeekProposal() {
		return seekProposal;
	}

	public void setSeekProposal(String seekProposal) {
		this.seekProposal = seekProposal;
	}
}
