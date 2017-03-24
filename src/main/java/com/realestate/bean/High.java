package com.realestate.bean;

public class High {
	private String content;

	private String currency;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "ClassPojo [content = " + content + ", currency = " + currency
				+ "]";
	}
}
