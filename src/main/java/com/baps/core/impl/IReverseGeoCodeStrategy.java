package com.baps.core.impl;

import com.baps.core.exception.LocationException;
import com.baps.model.Location;

/**
 * This Interface is used as strategy pattern for deciding the strategy
 * at runtime based on the service name which  service to be called.
 * 
 * @author Mayuresh Trivedi
 */
public interface IReverseGeoCodeStrategy {
	
	/**
	 * The Method to get the Location Address based on the coordinate provided.
	 * 
	 * @param lat Latitude
	 * @param log Longitude
	 * 
	 * @return Location JSON
	 * 
	 * @throws LocationException The JSON return of LocationException with Error Message and HTTP Error code.
	 * 
	 */
    Location reverseGeoCode(String lat, String log) throws LocationException;
}
