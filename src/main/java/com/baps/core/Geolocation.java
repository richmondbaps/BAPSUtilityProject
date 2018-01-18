package com.baps.core;


import com.baps.core.exception.LocationException;
import com.baps.core.impl.ReverseGeoCodeContext;
import com.baps.core.impl.ReverseGeoCodeFactory;
import com.baps.model.Location;
import com.baps.utils.LocationCacher;
import java.util.ArrayList;


/**
 * 
 * The Delegate Business Class which will get request from REST API to perform Business functions.
 * This is an example of a delegate pattern, this class can also be called by SOAP wrapper or 
 * any other client.
 *  
 * @author Mayuresh Trivedi
 */

public class Geolocation {

	/**
	 * This method is used to look-up the reverse geo code.
	 * Here, it uses the factory pattern based on the service suggested.
	 * 
	 * @param lat Latitude
	 * @param log Longitude
	 * @param service Service name either google or Nominatim
	 * 
	 * @return Location JSON
	 * 
	 * @throws LocationException The JSON return of LocationException with Error Message and HTTP Error code.
	 * 
	 */
    public Location getReverseLocation(String lat, String log, String locService) throws LocationException {
        ReverseGeoCodeFactory factory = new ReverseGeoCodeFactory();
        ReverseGeoCodeContext codeContext = factory.getReverseGeoFactory(locService);
        Location loc = codeContext.reverseGeoCode(lat, log);
        return loc;
    }

    /**
     * It returns last 10 cached locations as a List of Location Objects in JSON format.
     * 
     * @return List of the Locations
     * 
     */
    public ArrayList<Location> getCachedLocations() {
        return LocationCacher.getLocationCacher().getLocations();
    }

    
}
