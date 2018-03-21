package com.baps.model;

public class Zipcode {
	String zipcode;
	String city;
	String state;
	String lat;
	String longtitude;
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}
	@Override
	public String toString() {
		return "Zipcode [zipcode=" + zipcode + ", city=" + city + ", state=" + state + ", lat=" + lat + ", longtitude="
				+ longtitude + "]";
	}
	
}
