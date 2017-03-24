package com.realestate.bean;

public class ValueChange {
	private String content;

	private String duration;

	private String currency;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "ClassPojo [content = " + content + ", duration = " + duration
				+ ", currency = " + currency + "]";
	}
}
