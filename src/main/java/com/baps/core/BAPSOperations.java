/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baps.core;

import com.baps.dbconnections.DBUtility;
import com.baps.model.Member;
import com.baps.model.Zipcode;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mac
 */
public class BAPSOperations {

    public int uploadKaryakarsDataFromFileToDB(String filePath) throws IOException, SQLException {

        List<Member> membersFromCSV = CSVOperations.getMembersFromCSV(filePath);
        return DBUtility.insertAllMembers(membersFromCSV);
    }

    public int uploadZipCodesDataFromFileToDB(String filePath) throws IOException, SQLException {

        List<Zipcode> zipsFromCSV = CSVOperations.getZipCodesFromCSV(filePath);
        return DBUtility.insertAllZip(zipsFromCSV);
    }

    
    public void exportAllDataToCSV() throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, SQLException {
        List<Member> members = DBUtility.getMembers(null);
        Member member = new Member();
        member.setMEMBERID("TestID");
        member.setFIRSTNAME("TestName");
        members.add(member);
        CSVOperations.generateCSVFile(members, null);
    }
    
    public void uploadKaryakarsDetails(String fileName) throws IOException, SQLException{
    	BAPSOperations operations = new BAPSOperations();
    	fileName = (fileName!=null ? fileName : "/home/mac/Desktop/Spiritual/BAPS_Work/Westendzone.csv");
        int dataUpdated = operations.uploadKaryakarsDataFromFileToDB(fileName);
        System.out.println("Total Data Updated: "+dataUpdated);
    }
    
    public void uploadZipDatabase(String fileName) throws IOException, SQLException{
    	BAPSOperations operations = new BAPSOperations();
    	fileName = (fileName!=null ? fileName : "/home/mac/Desktop/Spiritual/BAPS_Work/free-zipcode-database.csv");
        int dataUpdated = operations.uploadZipCodesDataFromFileToDB(fileName);
        System.out.println("Total Data Updated: "+dataUpdated);
    }

    public Map<String,String> getZoneCounts(String zone) throws SQLException {
    	return DBUtility.zipZoneGroups(zone);
    }
    public static void main(String[] args) throws IOException, SQLException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
    	BAPSOperations operations = new BAPSOperations();
    	System.out.println(operations.getZoneCounts(null));
    	//operations.uploadZipDatabase(null);
        //operations.exportAllDataToCSV();
//    	operations.uploadKaryakarsDataFromFileToDB("/home/mac/Desktop/Spiritual/BAPS_Work/Westendzone.csv");
    	

    }
}
