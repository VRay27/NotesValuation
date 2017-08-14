package com.noteanalyzer.entity.valuation;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;

import lombok.ToString;

@Entity
@Table(name="statistics")
@ToString(callSuper = true)
public class Statistics extends AbstractEntity  {
	
	private static final long serialVersionUID = -3262112354371474829L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "statistics_ID")
	private int statisticsId;

	@Column(name = "Stat_name")
	private Integer subscriptionId;

	@Column(name = "USER_ID")
	private Integer userId;
	
	@Column(name="Stat_type")
	private String statType;
	
	@Column(name="base_type")
	private String baseType;
	
	@Column(name = "base_ID")
	private Integer baseId;

	@Column(name = "description")
	private String description;
	
	@Column(name="Stat_value")
	private String statValue;
	
	@Column(name="Stat_Date")
	private Date statDate;
	
	@Column(name="data_source")
	private String dataSource;

	/**
	 * @return the statisticsId
	 */
	public int getStatisticsId() {
		return statisticsId;
	}

	/**
	 * @param statisticsId the statisticsId to set
	 */
	public void setStatisticsId(int statisticsId) {
		this.statisticsId = statisticsId;
	}

	/**
	 * @return the subscriptionId
	 */
	public Integer getSubscriptionId() {
		return subscriptionId;
	}

	/**
	 * @param subscriptionId the subscriptionId to set
	 */
	public void setSubscriptionId(Integer subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the statType
	 */
	public String getStatType() {
		return statType;
	}

	/**
	 * @param statType the statType to set
	 */
	public void setStatType(String statType) {
		this.statType = statType;
	}

	/**
	 * @return the baseType
	 */
	public String getBaseType() {
		return baseType;
	}

	/**
	 * @param baseType the baseType to set
	 */
	public void setBaseType(String baseType) {
		this.baseType = baseType;
	}

	/**
	 * @return the baseId
	 */
	public Integer getBaseId() {
		return baseId;
	}

	/**
	 * @param baseId the baseId to set
	 */
	public void setBaseId(Integer baseId) {
		this.baseId = baseId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the statValue
	 */
	public String getStatValue() {
		return statValue;
	}

	/**
	 * @param statValue the statValue to set
	 */
	public void setStatValue(String statValue) {
		this.statValue = statValue;
	}

	/**
	 * @return the statDate
	 */
	public Date getStatDate() {
		return statDate;
	}

	/**
	 * @param statDate the statDate to set
	 */
	public void setStatDate(Date statDate) {
		this.statDate = statDate;
	}

	/**
	 * @return the dataSource
	 */
	public String getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}


	
}
