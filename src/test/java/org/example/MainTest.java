package org.example;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


class MainTest {
    MainTest() throws IOException, CsvException {
    }
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
    void compareParserCSVwithTest () throws IOException, CsvException {
        CSVReader reader1 = new CSVReader(new FileReader("data.csv"));
        List<String[]> Entries1 = reader1.readAll();

        CSVReader reader2 = new CSVReader(new FileReader("test_data.csv"));
        List<String[]> Entries2 = reader2.readAll();

        for (int i = 0; i < Entries1.size(); i++) {
            assertArrayEquals(Entries1.get(i), Entries2.get(i));
        }
    }
    @Test
    void testListToJson() {

        List<Employee> expectedEmployees = List.of(
                new Employee(1, "John", "Smith", "USA", 25),
                new Employee(2, "Ivan", "Petrov", "RU", 23)
        );

        String exectedJson = "[{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Smith\",\"country\":\"USA\",\"age\":25}," +
                "{\"id\":2,\"firstName\":\"Ivan\",\"lastName\":\"Petrov\",\"country\":\"RU\",\"age\":23}]";

        String actualJson = Main.listToJson(expectedEmployees);

        Assertions.assertEquals(exectedJson, actualJson);
    }
}