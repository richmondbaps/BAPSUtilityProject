package com.baps.utils;

//This program uses a file of zip code information to allow a user
//to find zip codes within a certain distance of another zip code.

import java.util.*;

import com.baps.dbconnections.DBUtility;
import com.baps.model.Zipcode;

import java.io.*;
import java.sql.SQLException;

public class ZipLookup {
 // radius of sphere.  Here its the Earth, in miles
 public static final double RADIUS = 3956.6;

 public static void main(String[] args) throws FileNotFoundException {
     Scanner console = new Scanner(System.in);
     System.out.print("What zip code are you interested in? ");
     String target = console.next();
     ArrayList<String> input = new ArrayList<String>();
     input.add("23060");
     input.add("23059");
     input.add("23233");
     input.add("23294");
     input.add("23220");
     input.add("23229");
     input.add("23228");
     input.add("23238");
     Zipcode zipcode = findNearestZip(getCoordinates(target), input);
     System.out.println("Nearest: "+zipcode);
 }

 private static Zipcode getCoordinates(String zipcode){
	 Zipcode zipcode1 = null;
		try {
			zipcode1 = DBUtility.getZipDetails(zipcode);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return zipcode1;
		
 }

 // Shows all matches for the given coordinates within the
 // given number of miles
 public static Zipcode findNearestZip(Zipcode zipcode,
                                ArrayList<String> input) {
     double lat1 = Double.parseDouble(zipcode.getLat());
     double long1 = Double.parseDouble(zipcode.getLongtitude());
     double nearestDistance=500.00;
     Zipcode nearestZip = null;
     for (Iterator<String> iterator = input.iterator(); iterator.hasNext();) {
		String zip =  iterator.next();
         Zipcode zipcodeInFile = getCoordinates(zip);
         double lat2 = Double.parseDouble(zipcodeInFile.getLat());
         double long2 = Double.parseDouble(zipcodeInFile.getLongtitude());
         double distance = DistanceCalculator.distance(lat1, long1, lat2, long2, "M");
         System.out.println("zip: "+zip+" distance: "+distance);
         if(lat1 == lat2 && long1 == long2) {
        	 nearestZip = zipcodeInFile;
        	 return nearestZip;
         } else {
        	 if(distance<nearestDistance){
        		 nearestZip = zipcodeInFile;
        		 nearestDistance = distance;
        	 }
         }
		
	}
     return nearestZip;
 }

}