package com.baps.core.impl;

import com.baps.core.exception.LocationException;
import com.baps.model.Location;
import com.baps.utils.LocationCacher;

/**
 * The class which implements the Strategy pattern to defines the strategy of looking up
 * the service for reverse geo code.
 * 
 * @author Mayuresh Trivedi
 */
public class ReverseGeoCodeContext {

    private IReverseGeoCodeStrategy strategy;

    
    /**
     * This can be set at runtime by the application preferences
     * 
     * @param strategy The Strategy set by context.
     * 
     */
    void setReverseGeoCodeStrategy(IReverseGeoCodeStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * This method returns the Location Object based on the coordinates provided and
     * strategy defined, it actually executes the strategy. It also caches the locations
     * requested.
     * 
	 * @param lat Latitude
	 * @param log Longitude
	 * 
	 * @return Location JSON
	 * 
	 * @throws LocationException The JSON return of LocationException with Error Message and HTTP Error code.
	 * 
	 */
    public Location reverseGeoCode(String lat, String log) throws LocationException {
        Location location = strategy.reverseGeoCode(lat, log);
        LocationCacher.getLocationCacher().cacheLocation(location);
        return location;
    }
}
