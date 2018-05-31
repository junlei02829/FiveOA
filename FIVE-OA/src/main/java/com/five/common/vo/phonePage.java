package com.five.common.vo;

import java.io.Serializable;

import com.five.entity.FivePhone;


public class phonePage implements Serializable{
	
	private static final long serialVersionUID = 8952279830044877175L;
	private FivePhone fivephone;

	public FivePhone getFivephone() {
		return fivephone;
	}

	public void setFivephone(FivePhone fivephone) {
		this.fivephone = fivephone;
	}

	@Override
	public String toString() {
		return "phonePage [fivephone=" + fivephone + "]";
	}
	
}
