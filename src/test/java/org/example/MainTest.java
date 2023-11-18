package org.example;


import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class MainTest {
    @Test
    void testWriteString(){
        String json = "{\"lastName\":\"1256\",\"country\":\"U158SA\",\"age\":h}";
        boolean result = Main.writeString(json);
        Assert.assertTrue(result);
    }

    @Test
    void parseCSVtest(){
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "test_data.csv";
        try (FileWriter fileWriter = new FileWriter(fileName, true)) {
            fileWriter.write("1,John,Smith,USA,25\n");
            fileWriter.write("2,Inav,Petrov,RU,23");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        List<Employee> employees = Main.parseCSV(columnMapping, fileName);
        assertEquals(2, employees.size());
    }
    @Test
    void testListToJson() {
        List<Employee> employee = List.of(
                new Employee(1, "John", "Smith", "USA", 25),
                new Employee(2, "Ivan", "Petrov", "RU", 23)
        );
        Type listType = new TypeToken<List<Employee>>() {
        }.getType();
        String exectedJson = new GsonBuilder().create().toJson(employee, listType);

        String actualJson = Main.listToJson(employee);

        Assertions.assertEquals(exectedJson, actualJson);
    }

}