package org.example;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void testParseCSV() throws FileNotFoundException {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";

        List<Employee> expectedEmployees = new ArrayList<>();
        expectedEmployees.add(new Employee(1, "John", "Smith", "USA", 25));
        expectedEmployees.add(new Employee(2, "Ivan", "Petrov", "RU", 23));

        List<Employee> employees = Main.parseCSV(columnMapping, fileName);

        assertArrayEquals(expectedEmployees.toArray(), employees.toArray());

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

        assertEquals(exectedJson, actualJson);
    }
}