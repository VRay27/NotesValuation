package com.noteanalyzer.bean;

public class Region {
	private String id;

	private String name;

	private String zindexValue;

	private Links links;

	private String type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZindexValue() {
		return zindexValue;
	}

	public void setZindexValue(String zindexValue) {
		this.zindexValue = zindexValue;
	}

	public Links getLinks() {
		return links;
	}

	public void setLinks(Links links) {
		this.links = links;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ClassPojo [id = " + id + ", name = " + name
				+ ", zindexValue = " + zindexValue + ", links = " + links
				+ ", type = " + type + "]";
	}
}
