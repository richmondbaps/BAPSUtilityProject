package com.baps.utils;

import com.baps.model.Location;
import java.util.ArrayList;

/**
 * This class is responsible for caching the location as and when request for
 * location by REST service. This uses the Singleton Pattern to make sure
 * only one cacher exist per application.
 * 
 * @author Mayuresh Trivedi
 */
public final class LocationCacher {

    private ArrayList<Location> locations = new ArrayList<>();
    
    private static LocationCacher cacher;

    private LocationCacher() {
    }
    
    /**
     * It provides the single LocationCacher Object.
     * 
     * @return LocationCacher 
     */
    public static LocationCacher getLocationCacher(){
        if(cacher == null){
            cacher = new LocationCacher();
        }
        
        return cacher;
    }

    /**
     * It caches the last 10 location which fulfills the requirement of problem
     * The service should cache (locally) the last 10 lookups and provide an 
     * additional RESTful API for retrieving this stored data.  
     * The data returned from this API should be a collection of the lookups performed, 
     * including the longitude and latitude values, the address found, 
     * and the date/time of the lookup. 
     * 
     * @param loc Location Object to be stored.
     */
    public synchronized void cacheLocation(Location loc) {
        if (locations.size() >= 10) {
            locations.remove(0);
        }

        locations.add(loc);

    }

    /**
     * It returns the last 10 location which fulfills the requirement of problem
     * The service should cache (locally) the last 10 lookups and provide an 
     * additional RESTful API for retrieving this stored data.  
     * The data returned from this API should be a collection of the lookups performed, 
     * including the longitude and latitude values, the address found, 
     * and the date/time of the lookup. 
     * 
     * @return
     */
    public synchronized ArrayList<Location> getLocations() {
        return locations;
    }
    
    

}
