package com.noteanalyzer.bean;

public class LocalRealEstate {
	private Region region;

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	@Override
	public String toString() {
		return "ClassPojo [region = " + region + "]";
	}
}
