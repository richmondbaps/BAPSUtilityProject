/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baps.core.impl;

import com.baps.core.exception.LocationException;
import com.baps.model.Location;
import com.baps.utils.LocationUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import org.springframework.http.HttpStatus;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 * This is the Strategy class which implements the Geo code lookup based on the 
 * Google API. it requests the reverse Geo lookup to Free Look Up service of Nominatim and return 
 * the Location Object.
 *
 * @author Mayuresh Trivedi
 */
public class NominatimReverseGeoImplStrategy implements IReverseGeoCodeStrategy{

	/**
	 * The Method to get the Location Address based on the coordinate provided using Nominatim Services.
	 * 
	 * @param lat Latitude
	 * @param log Longitude
	 * 
	 * @return Location JSON
	 * 
	 * @throws LocationException The JSON return of LocationException with Error Message and HTTP Error code.
	 * 
	 */
  
    @Override
	public Location reverseGeoCode(String lat, String log) throws LocationException {

		if (!LocationUtils.isCoordinatesValid(lat, log)) {
			throw new LocationException("Invalid Coordinates");
		}
		Location loc = new Location();
		loc.setLatitude(lat);
		loc.setLongitude(log);
		loc.setServiceused("Nominatim");
		loc.setLookupdatetime(LocationUtils.getCurrentTimeStamp());
		// making url request
		try {
			BufferedReader br;
			String output;
			String out = "";

			URL url = new URL("http://nominatim.openstreetmap.org/reverse?format=json&lat="+lat+"&lon="+log+"&zoom=18&addressdetails=1");
			// making connection
			try {
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");

				if (conn.getResponseCode() != 200) {
					throw new LocationException("Failed : HTTP error code : ", conn.getResponseCode());
				}

				// Reading data's from url
				br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				while ((output = br.readLine()) != null) {
					out += output;
				}
				conn.disconnect();
			} catch (Exception exConnection) {
				throw new LocationException("Not Able to Connect Host or Service Unavailable", HttpStatus.SERVICE_UNAVAILABLE.value());
			}

			// Converting Json formatted string into JSON object
            JSONObject json = (JSONObject) JSONSerializer.toJSON(out);
            System.out.println("JSON: "+json);
            
            JSONObject rec = json.getJSONObject("address");
            StringBuffer buffer = new StringBuffer();
            
            for (Iterator iterator = rec.keys(); iterator.hasNext();) {
                String keyName = (String) iterator.next();
                if(keyName.equalsIgnoreCase("country")){
                    loc.setCountry(rec.getString(keyName));
                }
                
                if(keyName.equalsIgnoreCase("county")){
                    loc.setCity(rec.getString(keyName));
                }
                //System.out.println("rec: "+keyName);
                buffer.append(rec.getString(keyName));
                buffer.append(", ");
                
            }
            loc.setAddress(buffer.toString().substring(0, buffer.toString().length()-2));

		} catch (MalformedURLException e) {
			throw new LocationException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
		} catch (IOException e) {
			throw new LocationException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
		}

		return loc;
	}
}
