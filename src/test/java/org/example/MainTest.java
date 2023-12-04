package org.example;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    void testMainMethod() {
        String[] args = {};
        boolean success = true;

        try {
            Main.main(args);
        } catch (Exception e) {
            success = false;
        }

        assertTrue(success);
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