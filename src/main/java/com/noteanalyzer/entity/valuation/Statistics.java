package com.noteanalyzer.entity.valuation;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;
import com.noteanalyzer.entity.notes.Property;

import lombok.ToString;

@Entity
@Table(name="statistics")
@ToString(callSuper = true)
@NamedQueries({
		@NamedQuery(name = Statistics.GET_STATISTICS_DETAILS, query = "select s from Statistics s where  s.baseType =:baseType and  s.baseId =:baseId "),
		@NamedQuery(name = Statistics.GET_STATISTICS_DETAILS_CRIME_SCHOOL, query = "select s from Statistics s where  s.baseType =:baseType and  (s.statType='CRIME' and  s.baseId in(:crimeAreaIdList)) OR (s.statType='SCHOOL' and  s.baseId in(:schoolAreaIdList)) "),
		@NamedQuery(name = Statistics.GET_STATISTICS_DETAILS_BY_USER_ID, query = "select s from Statistics s, Note n ,Property p, PropertyArea pa where  pa.areaId = s.baseId and s.baseType='AREA'  and  pa.propertyId = p.propertyId and  p.propertyId = n.propertyId and n.userId =:userId")})

public class Statistics extends AbstractEntity  {
	
	private static final long serialVersionUID = -3262112354371474829L;

	public static final String GET_STATISTICS_DETAILS = "GET_STATISTICS_DETAILS";
	public static final String GET_STATISTICS_DETAILS_BY_USER_ID = "GET_STATISTICS_DETAILS_BY_USER_ID";
	public static final String GET_STATISTICS_DETAILS_CRIME_SCHOOL ="GET_STATISTICS_DETAILS_CRIME_SCHOOL";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "statistics_ID")
	private int statisticsId;

	@Column(name = "Stat_name")
	private String statName;

	@Column(name = "USER_ID")
	private Integer userId;
	
	@Column(name="Stat_type")
	private String statType;
	
	@Column(name="base_type")
	private String baseType;
	
	@Column(name = "base_ID")
	private String baseId;

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
	 * @return the statName
	 */
	public String getStatName() {
		return statName;
	}

	/**
	 * @param statName the statName to set
	 */
	public void setStatName(String statName) {
		this.statName = statName;
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
	public String getBaseId() {
		return baseId;
	}

	/**
	 * @param baseId the baseId to set
	 */
	public void setBaseId(String baseId) {
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
