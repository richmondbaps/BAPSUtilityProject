/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baps.model;

import com.opencsv.bean.CsvBindByPosition;

/**
 *
 * @author mac
 */
public class Member {

    private String MEMBERID;
    private String FIRSTNAME;
    private String MIDDLENAME;
    private String LASTNAME;
    private String SPOUSENAME;
    private String ADDRESS1;
    private String ADDRESS2;
    private String CITY;
    private String STATE;
    private String ZIPCODE;
    private String SATSANG_CATEGORY;
    private String MANDAL;
    private String RELATION;
    private String ZONE;
    private String PRIMARYCELL;
    private String PRIMARYHOME;
    private String EMAIL;
    private String GENDER;
    private String ISKARYAKAR;
    private String ISVOLUNTEER;
    private String LANGUAGE;
    private String REMARKS;

    public String getMEMBERID() {
        return MEMBERID;
    }

    public void setMEMBERID(String MEMBERID) {
        this.MEMBERID = MEMBERID;
    }

    public String getFIRSTNAME() {
        return FIRSTNAME;
    }

    public void setFIRSTNAME(String FIRSTNAME) {
        this.FIRSTNAME = FIRSTNAME;
    }

    public String getMIDDLENAME() {
        return MIDDLENAME;
    }

    public void setMIDDLENAME(String MIDDLENAME) {
        this.MIDDLENAME = MIDDLENAME;
    }

    public String getLASTNAME() {
        return LASTNAME;
    }

    public void setLASTNAME(String LASTNAME) {
        this.LASTNAME = LASTNAME;
    }

    public String getSPOUSENAME() {
        return SPOUSENAME;
    }

    public void setSPOUSENAME(String SPOUSENAME) {
        this.SPOUSENAME = SPOUSENAME;
    }

    public String getADDRESS1() {
        return ADDRESS1;
    }

    public void setADDRESS1(String ADDRESS1) {
        this.ADDRESS1 = ADDRESS1;
    }

    public String getADDRESS2() {
        return ADDRESS2;
    }

    public void setADDRESS2(String ADDRESS2) {
        this.ADDRESS2 = ADDRESS2;
    }

    public String getCITY() {
        return CITY;
    }

    public void setCITY(String CITY) {
        this.CITY = CITY;
    }

    public String getSTATE() {
        return STATE;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public String getZIPCODE() {
        return ZIPCODE;
    }

    public void setZIPCODE(String ZIPCODE) {
        this.ZIPCODE = ZIPCODE;
    }

    public String getSATSANG_CATEGORY() {
        return SATSANG_CATEGORY;
    }

    public void setSATSANG_CATEGORY(String SATSANG_CATEGORY) {
        this.SATSANG_CATEGORY = SATSANG_CATEGORY;
    }

    public String getMANDAL() {
        return MANDAL;
    }

    public void setMANDAL(String MANDAL) {
        this.MANDAL = MANDAL;
    }

    public String getRELATION() {
        return RELATION;
    }

    public void setRELATION(String RELATION) {
        this.RELATION = RELATION;
    }

    public String getZONE() {
        return ZONE;
    }

    public void setZONE(String ZONE) {
        this.ZONE = ZONE;
    }

    public String getPRIMARYCELL() {
        return PRIMARYCELL;
    }

    public void setPRIMARYCELL(String PRIMARYCELL) {
        this.PRIMARYCELL = PRIMARYCELL;
    }

    public String getPRIMARYHOME() {
        return PRIMARYHOME;
    }

    public void setPRIMARYHOME(String PRIMARYHOME) {
        this.PRIMARYHOME = PRIMARYHOME;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getGENDER() {
        return GENDER;
    }

    public void setGENDER(String GENDER) {
        this.GENDER = GENDER;
    }

    public String getISKARYAKAR() {
        return ISKARYAKAR;
    }

    public void setISKARYAKAR(String ISKARYAKAR) {
        this.ISKARYAKAR = ISKARYAKAR;
    }

    public String getISVOLUNTEER() {
        return ISVOLUNTEER;
    }

    public void setISVOLUNTEER(String ISVOLUNTEER) {
        this.ISVOLUNTEER = ISVOLUNTEER;
    }

    public String getLANGUAGE() {
        return LANGUAGE;
    }

    public void setLANGUAGE(String LANGUAGE) {
        this.LANGUAGE = LANGUAGE;
    }

    public String getREMARKS() {
        return REMARKS;
    }

    public void setREMARKS(String REMARKS) {
        this.REMARKS = REMARKS;
    }

    @Override
    public String toString() {
        return "Member{" + "MEMBERID=" + MEMBERID + ", FIRSTNAME=" + FIRSTNAME + ", MIDDLENAME=" + MIDDLENAME + ", LASTNAME=" + LASTNAME + ", SPOUSENAME=" + SPOUSENAME + ", ADDRESS1=" + ADDRESS1 + ", ADDRESS2=" + ADDRESS2 + ", CITY=" + CITY + ", STATE=" + STATE + ", ZIPCODE=" + ZIPCODE + ", SATSANG_CATEGORY=" + SATSANG_CATEGORY + ", MANDAL=" + MANDAL + ", RELATION=" + RELATION + ", ZONE=" + ZONE + ", PRIMARYCELL=" + PRIMARYCELL + ", PRIMARYHOME=" + PRIMARYHOME + ", EMAIL=" + EMAIL + ", GENDER=" + GENDER + ", ISKARYAKAR=" + ISKARYAKAR + ", ISVOLUNTEER=" + ISVOLUNTEER + ", LANGUAGE=" + LANGUAGE + ", REMARKS=" + REMARKS + '}';
    }

    
}
