/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baps.core;

import com.baps.model.Member;
import com.baps.model.Zipcode;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.Writer;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mac
 */
public class CSVOperations {

    private static final String OBJECT_LIST_SAMPLE = "./BAPS_Karyakars.csv";
    private static final String OBJECT_ZIP_LIST_SAMPLE = "./ZipCodes.csv";
    public static List<Member> getMembersFromCSV(String fileName) throws IOException {

        List<Member> members = new ArrayList<>();
        fileName = (fileName != null ? fileName : OBJECT_LIST_SAMPLE);

        try (Reader reader = Files.newBufferedReader(Paths.get(fileName))) {
            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Member.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            members = csvToBean.parse();

        }

        return members;
    }
    
    public static List<Zipcode> getZipCodesFromCSV(String fileName) throws IOException {

        List<Zipcode> zipcodes = new ArrayList<>();
        fileName = (fileName != null ? fileName : OBJECT_ZIP_LIST_SAMPLE);

        try (Reader reader = Files.newBufferedReader(Paths.get(fileName))) {
            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Zipcode.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            zipcodes = csvToBean.parse();

        }

        return zipcodes;
    }

    public static String generateCSVFile(List<Member> members, String filePath) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        String finalPath = (filePath != null ? filePath : OBJECT_LIST_SAMPLE);
        try (Writer writer = Files.newBufferedWriter(Paths.get(finalPath))) {
            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
            strategy.setType(Member.class);
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withOrderedResults(true)
                    .build();
            beanToCsv.write(members);
        }
        return finalPath;
    }
}
