package com.baps.core.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.baps.core.exception.ErrorResponse;
import com.baps.core.exception.LocationException;
import com.baps.model.Location;

/**
 * This class act as the Factory Pattern implementation, which defines and combines
 * Strategy Pattern and Factory pattern. based on the service name the Factory Object
 * will be return. 
 *
 * @author Mayuresh Trivedi
 */
public class ReverseGeoCodeFactory {
    
    ReverseGeoCodeContext codeContext = new ReverseGeoCodeContext();
            
    /**
     * This method returned the factory based on the service requested.
     * 
     * @param factoryName Google or Nominatim
     * 
     * @return ReverseGeoCodeContext It will return the Context for Strategy pattern based on service.
     * 
     * @throws LocationException throws the locationExcepion if the service name is not either google or Nominatim
     * 
     */
    public ReverseGeoCodeContext getReverseGeoFactory(String factoryName) throws LocationException{
        if(factoryName.equalsIgnoreCase("google")){
            codeContext.setReverseGeoCodeStrategy(new GoogleReverseGeoImplStrategy());
        } else if(factoryName.equalsIgnoreCase("Nominatim")){
            codeContext.setReverseGeoCodeStrategy(new NominatimReverseGeoImplStrategy());
        } else {
        	if (factoryName.trim().length()>0) {
    			throw new LocationException("Invalid Service Name",HttpStatus.PRECONDITION_FAILED.value());
    		}
        }
        
        return codeContext;
    } 
    
}
