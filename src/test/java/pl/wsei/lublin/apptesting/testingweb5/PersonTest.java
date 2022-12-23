package pl.wsei.lublin.apptesting.testingweb5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    @Test
    public void test_person_default_constructor() {
        Person testClass = new Person();

        testClass.setlName("Maria");
        assertEquals("Maria", testClass.getmName());

        testClass.setlName("Jan");
        assertEquals("Jan", testClass.getfName());

        testClass.setlName("Rokita");
        assertEquals("Rokita", testClass.getlName());
        


    }

}