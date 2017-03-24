package com.realestate.bean;

public class Links {
	private String forSaleByOwner;

	private String overview;

	private String forSale;

	public String getForSaleByOwner() {
		return forSaleByOwner;
	}

	public void setForSaleByOwner(String forSaleByOwner) {
		this.forSaleByOwner = forSaleByOwner;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getForSale() {
		return forSale;
	}

	public void setForSale(String forSale) {
		this.forSale = forSale;
	}

	@Override
	public String toString() {
		return "ClassPojo [forSaleByOwner = " + forSaleByOwner
				+ ", overview = " + overview + ", forSale = " + forSale + "]";
	}
}