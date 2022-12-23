package pl.wsei.lublin.apptesting.testingweb5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransformServiceTest {

    private TransformService testClass = new TransformService();

    @Test
    public void test_toDomain() {
        Person person = new Person();
        person.setCompanyName("KGHM");
        person.setfName("Jan");
        person.setlName("Rokita");
        person.setmName("Maria");
        person.setPersonId(1);
        User user = testClass.toUserDomain(person);

        assertNotNull(user);
        assertEquals("KGHM", user.getCompanyName());
        assertEquals("Jan", user.getFirstName());
        assertEquals("Rokita", user.getLastName());
        assertEquals(1, user.getUserId().intValue());
    }

    @Test
    public void test_toEnity() {
        User user = new User();

        user.setCompanyName("KGHM");
        user.setFirstName("Jan");
        user.setLastName("Rokita");
        user.setUserId(Integer.valueOf(1));

        Person person = testClass.toUserEntity(user);

        assertNotNull(user);
        assertEquals("KGHM", person.getCompanyName());
        assertEquals("Jan", person.getfName());
        assertEquals("Rokita", person.getlName());
        assertEquals(1, person.getPersonId());
    }

}