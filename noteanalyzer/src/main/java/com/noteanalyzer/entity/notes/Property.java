package com.noteanalyzer.entity.notes;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;
import com.noteanalyzer.entity.address.Area;

import lombok.ToString;
 
@Entity
@Table(name="PROPERTY")
@ToString(callSuper = true)
@NamedQueries({ @NamedQuery(name = Property.FETCH_PROPERTY_DETAILS,query="select propertyId from Property p where p.propertyId =:propertyId")})
public class Property extends AbstractEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6408731281219126859L;

	public static final String FETCH_PROPERTY_DETAILS = "FETCH_PROPERTY_DETAILS";

	@Id
    @Column(name="PROP_ID")
    private Integer propertyId;
 
	@ManyToOne(optional = false)
	@JoinColumn(name="AREA_ID")
	private Area area;
	
	@Column(name="PROP_NAME")
    private String propertyName;
 
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PROPERTY_TYPE")
	private PropertyType propertyType;
	
	@Column(name="PROP_DESC")
    private String propertyDescription;
	
	
	@Column(name="STREET")
	private String street;
	
	@Column(name="CITY")
	private String	city;
	
	@Column(name="STATE")
	private String	state;
	
	@Column(name="ZIP")
	private Integer	Zip;
	
	@Column(name="AGE")
	private Integer	age;
	
	@Column(name="SIZE_SF")
	private Integer	size_sf;
	
	@Column(name="SUBDIVIDABLE")
	private boolean subdividable;
	
	@Column(name="OTH_HIGH_PRIO_DEBT")
	private Integer	otherHigherPriorityDebt;
	
	@Column(name="OTH_LOW_PRIO_DEBT")
	private Integer OtherLowerPriorityDebt;
	
	@Column(name="OTH_MONTH_EXPENSE")
	private Integer otherMonthlyExpenses;

	
	
	public Integer getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyDescription() {
		return propertyDescription;
	}

	public void setPropertyDescription(String propertyDescription) {
		this.propertyDescription = propertyDescription;
	}

	
	
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public PropertyType getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getZip() {
		return Zip;
	}

	public void setZip(Integer zip) {
		Zip = zip;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getSize_sf() {
		return size_sf;
	}

	public void setSize_sf(Integer size_sf) {
		this.size_sf = size_sf;
	}

	public boolean isSubdividable() {
		return subdividable;
	}

	public void setSubdividable(boolean subdividable) {
		this.subdividable = subdividable;
	}

	public Integer getOtherHigherPriorityDebt() {
		return otherHigherPriorityDebt;
	}

	public void setOtherHigherPriorityDebt(Integer otherHigherPriorityDebt) {
		this.otherHigherPriorityDebt = otherHigherPriorityDebt;
	}

	public Integer getOtherLowerPriorityDebt() {
		return OtherLowerPriorityDebt;
	}

	public void setOtherLowerPriorityDebt(Integer otherLowerPriorityDebt) {
		OtherLowerPriorityDebt = otherLowerPriorityDebt;
	}

	public Integer getOtherMonthlyExpenses() {
		return otherMonthlyExpenses;
	}

	public void setOtherMonthlyExpenses(Integer otherMonthlyExpenses) {
		this.otherMonthlyExpenses = otherMonthlyExpenses;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((propertyId == null) ? 0 : propertyId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Property other = (Property) obj;
		if (propertyId == null) {
			if (other.propertyId != null)
				return false;
		} else if (!propertyId.equals(other.propertyId))
			return false;
		return true;
	}

	
 
   
}
