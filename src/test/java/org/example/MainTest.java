package org.example;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MainTest {
    MainTest() {
    }
    @Test
    void testWriteString(){
        String json = "{\"lastName\":\"1256\",\"country\":\"U158SA\",\"age\":h}";
        boolean result = Main.writeString(json);
        Assert.assertTrue(result);
    }
    @Test
    void compareParserCSVtest () throws IOException, CsvException {
        String [][] Entries1 = {{"1","John","Smith","USA","25"},{"2","Inav","Petrov","RU","23"}};
        
        CSVReader reader1 = new CSVReader(new FileReader("data.csv"));
        List<String[]> Entries2 = reader1.readAll();

        for (int i = 0; i < Entries2.size(); i++) {
            assertArrayEquals(Entries2.get(i), Entries1 [i]);
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