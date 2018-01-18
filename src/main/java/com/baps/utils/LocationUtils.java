package com.baps.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.baps.core.exception.ErrorResponse;
import com.baps.core.exception.LocationException;

/**
 * This class is the Utility classes for the Location services.
 * it validates coordinates provides. it also provides the current time stamp
 * in String form with yyyy-MM-dd HH:mm:ss format.
 *
 * @author Mayuresh Trivedi
 */
public class LocationUtils {

	protected static final String LATITUDE_PATTERN = "^(\\+|-)?(?:90(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,6})?))$";
	protected static final String LONGITUDE_PATTERN = "^(\\+|-)?(?:180(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,6})?))$";
	private static double latitude;
	private static double longitude;

	/**
	 * It returns the current time stamp in yyyy-MM-dd HH:mm:ss format.
	 * 
	 * @return String of current time stamp.
	 */
	public static synchronized String getCurrentTimeStamp() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;
	}

	/**
	 * This method validates the coordinates in case of wrong values it returns either coordinates 
	 * are valid or not in case of exception it will return LocationException with proper HTTP
	 * response code.
	 * 
	 * @param lat
	 * @param log
	 * @return
	 * @throws LocationException
	 */
	public static boolean isCoordinatesValid(String lat, String log) throws LocationException {

		boolean isValidCoordinate = false;

		if ((lat != null && lat.trim().length() > 0) && (log != null && log.trim().length() > 0)) {
			try {
				DecimalFormat df = new DecimalFormat("#.######");
				df.setRoundingMode(RoundingMode.UP);

				latitude = Double.parseDouble(lat);
				longitude = Double.parseDouble(log);

				if (df.format(latitude).matches(LATITUDE_PATTERN) && df.format(longitude).matches(LONGITUDE_PATTERN)) {
					isValidCoordinate = true;
				}

			} catch (Exception exParse) {
				throw new LocationException("Invalid Coordinates",HttpStatus.UNPROCESSABLE_ENTITY.value());
			}

		} else {
			throw new LocationException("Coordinates Required", HttpStatus.PRECONDITION_FAILED.value());
		}

		return isValidCoordinate;
	}

}
