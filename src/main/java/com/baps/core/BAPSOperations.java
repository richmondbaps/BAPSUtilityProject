/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baps.core;

import com.baps.dbconnections.DBUtility;
import com.baps.model.Member;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author mac
 */
public class BAPSOperations {

    public int uploadDataFromFileToDB(String filePath) throws IOException, SQLException {

        List<Member> membersFromCSV = CSVOperations.getMembersFromCSV(filePath);
        return DBUtility.insertAllMembers(membersFromCSV);
    }

    public void exportAllDataToCSV() throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, SQLException {
        List<Member> members = DBUtility.getMembers(null);
        Member member = new Member();
        member.setMEMBERID("TestID");
        member.setFIRSTNAME("TestName");
        members.add(member);
        CSVOperations.generateCSVFile(members, null);
    }

    public static void main(String[] args) throws IOException, SQLException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        BAPSOperations operations = new BAPSOperations();
//        String fileName = "/home/mac/Desktop/Spiritual/BAPS_Work/Westendzone.csv";
//        int dataUpdated = operations.uploadDataFromFileToDB(fileName);
//        System.out.println("Total Data Updated: "+dataUpdated);
        operations.exportAllDataToCSV();

    }
}
