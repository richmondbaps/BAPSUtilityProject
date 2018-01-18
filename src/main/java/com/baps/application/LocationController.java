package com.baps.application;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baps.model.Location;
import com.baps.utils.LocationUtils;
import com.baps.core.Geolocation;
import com.baps.core.exception.ErrorResponse;
import com.baps.core.exception.LocationException;

/**
 * The Controller Class with the REST methods.
 * The Class Exposes two main methods to fulfill the requirement of the 
 * Problem.
 * 
 * RESTful web service (HTTP) which is capable of looking up a physical street address 
 * given a set of geographic coordinates (longitude and latitude values). 
 * For example, given the latitude '33.969601' and longitude '-84.100033', 
 * the service is returning the address of the NCR office in Duluth, GA (2651 Satellite Blvd, Duluth, GA 30096).  
 * The implementation is delegating to an online geocoding API (i.e., Google Maps or Nominatim) to perform the lookup; 
 * the implementation will serve as a basic abstraction to simplify usage of one or more external services that handle the geo-location aspects.
 * 
 */
@RestController
public class LocationController {

	private Geolocation location = new Geolocation();

	/**
	 * This the REST method to search for the Geo Graphic address based on the coordinates and 
	 * Service name suggested.
	 * 
	 * If no Service is specified then it will use Google as Default Geo-graphical API.
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
    @RequestMapping("/getLocation")
    public Location getLocation(@RequestParam(value="lat", defaultValue="") String lat, @RequestParam(value="log", defaultValue="") String log, @RequestParam(value="service", defaultValue="google") String service) throws LocationException {
    	return location.getReverseLocation(lat, log, service);
    }
    
    /**
     * It Fulfills the Requirement of caching (locally) the last 10 lookups 
     * and provide an additional RESTful API for retrieving this stored data.  
     * The data returned from this API should be a collection of the lookups performed, 
     * including the longitude and latitude values, the address found, and the date/time of the lookup. 
     * 
     * @return List of JSON Location.
     * 
     */
    @RequestMapping("/getCachedLocations")
    public ArrayList<Location> getAllLocations() {
        return location.getCachedLocations();
    }
    
    /**
     * This is the Exception Handler method to handle the Location Exceptions thrown by the code. 
     * 
     * @param ex LocationException thrown at Runtime
     * 
     * @return The ResponseEntity with ErrorResponse as JSON
     */
    @ExceptionHandler(LocationException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(LocationException ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(ex.getErrorCode());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}
}
