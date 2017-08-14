package com.noteanalyzer.webservice.appraisal;

import java.io.IOException;

import org.xml.sax.SAXException;

import com.noteanalyzer.appraisal.exceptions.AddressNotAvailableException;

public interface IAppraisalSource {

	String getApprisalSourcePropertyId(String streetAddress, String cityStateZip)
			throws SAXException, IOException, AddressNotAvailableException;

	String getMarketValueWithAddress(String streetAddress, String city, String state, String zipCode)
			throws SAXException, IOException, AddressNotAvailableException;

	String getMarketValueWithApprisalSourceId(String apprsialSourcePropertyId)
			throws SAXException, IOException, AddressNotAvailableException;

	AppraisalPropertyBean getPropertyDetailsWithAddress(String streetAddress, String city, String state, String zipCode);

}
