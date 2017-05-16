package com.noteanalyzer.mvc.model;

import java.io.Serializable;

public class NoteSummaryModel  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1746624356754962655L;
	
	
	private String noteId;
	private String assetImgSrc;
	private String yield;
	private String itv;
	private String ltv;
	private String marketValue;
	private String crime;
	private String overAllScore;
	
	
	public NoteSummaryModel() {
	}
	
	
	public NoteSummaryModel(String assetImgSrc, String yield, String itv, String ltv, String marketValue, String crime,
			String overAllScore) {
		super();
		this.assetImgSrc = assetImgSrc;
		this.yield = yield;
		this.itv = itv;
		this.ltv = ltv;
		this.marketValue = marketValue;
		this.crime = crime;
		this.overAllScore = overAllScore;
	}
	
	
	/**
	 * @return the noteId
	 */
	public String getNoteId() {
		return noteId;
	}


	/**
	 * @param noteId the noteId to set
	 */
	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}


	public String getAssetImgSrc() {
		return assetImgSrc;
	}
	public void setAssetImgSrc(String assetImgSrc) {
		this.assetImgSrc = assetImgSrc;
	}
	public String getYield() {
		return yield;
	}
	public void setYield(String yield) {
		this.yield = yield;
	}
	public String getItv() {
		return itv;
	}
	public void setItv(String itv) {
		this.itv = itv;
	}
	public String getLtv() {
		return ltv;
	}
	public void setLtv(String ltv) {
		this.ltv = ltv;
	}
	public String getMarketValue() {
		return marketValue;
	}
	public void setMarketValue(String marketValue) {
		this.marketValue = marketValue;
	}
	public String getCrime() {
		return crime;
	}
	public void setCrime(String crime) {
		this.crime = crime;
	}
	public String getOverAllScore() {
		return overAllScore;
	}
	public void setOverAllScore(String overAllScore) {
		this.overAllScore = overAllScore;
	}
	@Override
	public String toString() {
		return "NoteSummaryModel [assetImgSrc=" + assetImgSrc + ", yield=" + yield + ", itv=" + itv + ", ltv=" + ltv
				+ ", marketValue=" + marketValue + ", crime=" + crime + ", overAllScore=" + overAllScore + "]";
	}
	
	
	

}
