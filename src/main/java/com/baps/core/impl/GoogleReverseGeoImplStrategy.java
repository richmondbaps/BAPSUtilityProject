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

import org.springframework.http.HttpStatus;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 * This is the Strategy class which implements the Geo code lookup based on the 
 * Google API. it requests the reverse Geo lookup to Google Service and returs
 * the Location Object.
 *
 * @author Mayuresh Trivedi
 */
public class GoogleReverseGeoImplStrategy implements IReverseGeoCodeStrategy {

	/**
	 * The Method to get the Location Address based on the coordinate provided using Google Services.
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
		loc.setServiceused("google");
		loc.setLookupdatetime(LocationUtils.getCurrentTimeStamp());
		// making url request
		try {
			BufferedReader br;
			String output;
			String out = "";

			URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat + "," + log + "&sensor=true");
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

			JSONArray results = json.getJSONArray("results");
			if (results == null || results.size() == 0) {
				throw new LocationException(" No Data Found for given Coordinates", HttpStatus.NOT_FOUND.value());
			}
			JSONObject rec = results.getJSONObject(0);
			JSONArray address_components = rec.getJSONArray("address_components");
			for (int i = 0; i < address_components.size(); i++) {
				JSONObject rec1 = address_components.getJSONObject(i);
	
				JSONArray types = rec1.getJSONArray("types");
				String comp = types.getString(0);

				if (comp.equals("locality")) {
					loc.setCity(rec1.getString("long_name"));
				} else if (comp.equals("country")) {
					loc.setCountry(rec1.getString("long_name"));
				}
			}
			loc.setAddress(rec.getString("formatted_address"));


		} catch (MalformedURLException e) {
			throw new LocationException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
		} catch (IOException e) {
			throw new LocationException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
		}

		return loc;
	}

}
