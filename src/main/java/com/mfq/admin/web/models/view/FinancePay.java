package com.mfq.admin.web.models.view;

import com.mfq.admin.web.bean.*;

/**
 * Created by hui on 2016/1/4.
 */
public class FinancePay extends PayRecord{

	String username;
	String mobile;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
