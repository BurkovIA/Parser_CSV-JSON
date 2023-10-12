package org.example;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

import java.util.List;


public class Main {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.addAll(List.of("1,John,Smith,USA,25\n",
                "2,Inav,Petrov,RU,23"));

        try (FileWriter fileWriter = new FileWriter("data.csv", true)) {
            for (String line : list) {
                fileWriter.write(line);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";
        List<Employee> employees = parseCSV(columnMapping, fileName);
        String json = listToJson(employees);

        writeString(json);
        parseCSV(columnMapping, fileName);
    }
    private static String listToJson(List<Employee> employees) {
        Type listType = new TypeToken<List<Employee>>() {
        }.getType();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String json = gson.toJson(employees,listType);

        return json;
    }
    private static List<Employee> parseCSV(String[] columnMapping, String fileName) {
        CsvToBean<Employee> csvToBean = null;
        List<Employee> employees = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(fileName);
            CSVReader reader = new CSVReader(fileReader);
            ColumnPositionMappingStrategy<Employee> Strategy = new ColumnPositionMappingStrategy<>();
            Strategy.setType(Employee.class);
            Strategy.setColumnMapping(columnMapping);

            csvToBean = new CsvToBeanBuilder<Employee>(reader)
                    .withMappingStrategy(Strategy)
                    .build();
            employees = csvToBean.parse();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }
    private static void writeString(String json) {
        File fileJson = new File("data.json");
        try (FileWriter fileWriter = new FileWriter(fileJson, true)) {
            fileWriter.write(json);
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}








