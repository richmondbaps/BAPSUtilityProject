/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baps.dbconnections;

import com.baps.model.Member;
import com.baps.model.Zipcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mac
 */
public class DBUtility {

    private static String dbURL = "jdbc:mysql://localhost:3306/richmond_baps";
    private static String username = "root";
    private static String password = "mac";

    public static Connection getConnection() {
        Connection conn = null;
        try {

            conn = DriverManager.getConnection(dbURL, username, password);

            if (conn != null) {
                System.out.println("Connected");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        System.out.println("Obj: " + getMembers(null));
    }

    public static int deleteMembers() {
//        String sql = "DELETE FROM Users WHERE username=?";
//
//        PreparedStatement statement = conn.prepareStatement(sql);
//        statement.setString(1, "bill");
//
//        int rowsDeleted = statement.executeUpdate();
//        if (rowsDeleted > 0) {
//            System.out.println("A user was deleted successfully!");
//        }

        return 0;
    }

    public static int updateMembers() {
//        String sql = "UPDATE Users SET password=?, fullname=?, email=? WHERE username=?";
//
//        PreparedStatement statement = conn.prepareStatement(sql);
//        statement.setString(1, "123456789");
//        statement.setString(2, "William Henry Bill Gates");
//        statement.setString(3, "bill.gates@microsoft.com");
//        statement.setString(4, "bill");
//
//        int rowsUpdated = statement.executeUpdate();
//        if (rowsUpdated > 0) {
//            System.out.println("An existing user was updated successfully!");
//        }return 0;
        return 0;
    }

    public static List<Member> getMembers(String query) throws SQLException {
        String sql = null;

        if (query == null || query.trim().length() == 0) {
            sql = "SELECT member_id,first_name,middle_name, last_name, spouse_name, address1, address2, city, state, zipcode, satsang_category, mandal, relation, zone, primarycell, primaryhome, email, gender, iskaryakar, isvolunteer, language, remarks, subzipcode FROM member_master";
        } else {
            sql = query;
        }

        Connection conn = getConnection();
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        List<Member> members = new ArrayList<Member>();

        while (result.next()) {
            Member obj = new Member();
            obj.setMEMBERID(result.getString("member_id"));
            obj.setFIRSTNAME(result.getString("first_name"));
            obj.setMIDDLENAME(result.getString("middle_name"));
            obj.setLASTNAME(result.getString("last_name"));
            obj.setSPOUSENAME(result.getString("spouse_name"));
            obj.setADDRESS1(result.getString("address1"));
            obj.setADDRESS2(result.getString("address2"));
            obj.setCITY(result.getString("city"));
            obj.setSTATE(result.getString("state"));
            obj.setZIPCODE(result.getString("zipcode"));
            obj.setSATSANG_CATEGORY(result.getString("satsang_category"));
            obj.setMANDAL(result.getString("mandal"));
            obj.setRELATION(result.getString("relation"));
            obj.setZONE(result.getString("zone"));
            obj.setPRIMARYCELL(result.getString("primarycell"));
            obj.setPRIMARYHOME(result.getString("primaryhome"));
            obj.setEMAIL(result.getString("email"));
            obj.setGENDER(result.getString("gender"));
            obj.setISKARYAKAR(result.getString("iskaryakar"));
            obj.setISVOLUNTEER(result.getString("isvolunteer"));
            obj.setLANGUAGE(result.getString("language"));
            obj.setREMARKS(result.getString("remarks"));
            obj.setSUBZIPCODE(result.getString("subzipcode"));
            members.add(obj);
        }

        result.close();
        statement.close();
        conn.close();
        return members;
    }

    public static int insertAllMembers(List<Member> members) throws SQLException {
        int count = 0;

        String sql = "INSERT INTO member_master VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);

            final int batchSize = 1000;

            for (Member obj : members) {

                ps.setString(1, obj.getMEMBERID());
                ps.setString(2, obj.getFIRSTNAME());
                ps.setString(3, obj.getMIDDLENAME());
                ps.setString(4, obj.getLASTNAME());
                ps.setString(5, obj.getSPOUSENAME());
                ps.setString(6, obj.getADDRESS1());
                ps.setString(7, obj.getADDRESS2());
                ps.setString(8, obj.getCITY());
                ps.setString(9, obj.getSTATE());
                ps.setString(10, obj.getZIPCODE());
                ps.setString(11, obj.getSATSANG_CATEGORY());
                ps.setString(12, obj.getMANDAL());
                ps.setString(13, obj.getRELATION());
                ps.setString(14, obj.getZONE());
                ps.setString(15, obj.getPRIMARYCELL());
                ps.setString(16, obj.getPRIMARYHOME());
                ps.setString(17, obj.getEMAIL());
                ps.setString(18, obj.getGENDER());
                ps.setString(19, obj.getISKARYAKAR());
                ps.setString(20, obj.getISVOLUNTEER());
                ps.setString(21, obj.getLANGUAGE());
                ps.setString(22, obj.getREMARKS());
                ps.setString(23, obj.getSUBZIPCODE());

                ps.addBatch();

                if (++count % batchSize == 0) {
                    ps.executeBatch();
                }
            }
            ps.executeBatch(); // insert remaining records
            ps.close();
            connection.close();
        }
        
        return count;
    }
    
    
    public static Map<String,String> zipZoneGroups(String zoneName) throws SQLException{
    	String sql = "SELECT Zipcode, count(1) counts FROM richmond_baps.member_master where zone='"+(zoneName!=null ? zoneName: "West End")+"' group by zipcode order by counts desc";
    	HashMap<String, String> zipMap = new HashMap<String, String>();
    	
    	Connection conn = getConnection();
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
    	
        while (result.next()) {
        	zipMap.put(result.getString("zipcode"), result.getString("counts"));
        }
    	return zipMap;
    	
    }
    
    
    public static Zipcode getZipDetails(String zipcode) throws SQLException{
    	String sql = "SELECT  zip, city , state , latitude , longitude  FROM  zipmaster  where  zip ='"+zipcode+"'  limit 1";
    	
    	Connection conn = getConnection();
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        Zipcode zip = new Zipcode();
        while (result.next()) {
        	zip.setZipcode(result.getString("zip"));
        	zip.setCity(result.getString("city"));
        	zip.setState(result.getString("state"));
        	zip.setLat(result.getString("latitude"));
        	zip.setLongtitude(result.getString("longitude"));
        }
    	return zip;
    }
    
    
    
    public static int insertAllZip(List<Zipcode> zipcodes) throws SQLException {
        int count = 0;
        int recoNo = 1;
        String sql = "INSERT INTO zipdetails VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        System.out.println("Toal Zipcodes to insert: "+zipcodes.size());
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);

            final int batchSize = 1000;
            
            for (Zipcode obj : zipcodes) {
                ps.setString(1, recoNo+++"");
                ps.setString(2, obj.getZipcode());
                ps.setString(3, obj.getCity());
                ps.setString(4, obj.getState());
                ps.setString(5, obj.getLat());
                ps.setString(6, obj.getLongtitude());
                ps.addBatch();

                if (++count % batchSize == 0) {
                	System.out.println("Inserting Records from: "+(count-batchSize)+" to: "+count);
                    ps.executeBatch();
                }
            }
            ps.executeBatch(); // insert remaining records
            ps.close();
            connection.close();
        }
        
        System.out.println("Total Zip Records: "+recoNo+" Counts: "+count);
        
        return count;
    }

    public static int insertRecord(Member obj) throws SQLException {
        String sql = "INSERT INTO member_master VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection conn = getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setString(1, obj.getMEMBERID());
        statement.setString(2, obj.getFIRSTNAME());
        statement.setString(3, obj.getMIDDLENAME());
        statement.setString(4, obj.getLASTNAME());
        statement.setString(5, obj.getSPOUSENAME());
        statement.setString(6, obj.getADDRESS1());
        statement.setString(7, obj.getADDRESS2());
        statement.setString(8, obj.getCITY());
        statement.setString(9, obj.getSTATE());
        statement.setString(10, obj.getZIPCODE());
        statement.setString(11, obj.getSATSANG_CATEGORY());
        statement.setString(12, obj.getMANDAL());
        statement.setString(13, obj.getRELATION());
        statement.setString(14, obj.getZONE());
        statement.setString(15, obj.getPRIMARYCELL());
        statement.setString(16, obj.getPRIMARYHOME());
        statement.setString(17, obj.getEMAIL());
        statement.setString(18, obj.getGENDER());
        statement.setString(19, obj.getISKARYAKAR());
        statement.setString(20, obj.getISVOLUNTEER());
        statement.setString(21, obj.getLANGUAGE());
        statement.setString(22, obj.getREMARKS());
        statement.setString(23, obj.getSUBZIPCODE());

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new user was inserted successfully!");
        }

        statement.close();
        conn.close();
        return rowsInserted;
    }

}
