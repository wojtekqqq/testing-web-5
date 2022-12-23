package pl.wsei.lublin.apptesting.testingweb5;

import org.junit.Before;
import org.junit.Test;
//import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MockUserServiceImplTest {

    private static final String JAN = "Jan";
    private static final String TESTOWA_FIRMA = "KGHM";

    private Optional<Person> person = Optional.of(new Person());

    @Mock
    private PersonRepository personDao;

    @InjectMocks
    private UserServiceImpl testClass;

    @Mock
    private TransformService transformer;

    private User user = new User();

    @Test
    public void fidnById_found() {
        doReturn(person).when(personDao).findById(Integer.valueOf(1));
        doReturn(user).when(transformer).toUserDomain(person.get());

        User user = testClass.findById(Integer.valueOf(1));
        assertEquals(JAN, user.getFirstName());
    }

    @Test(expected = UserNotFoundException.class)
    public void fidnById_not_found() {
        doReturn(null).when(personDao).findById(Integer.valueOf(1));
        testClass.findById(Integer.valueOf(1));

    }

    @Test
    public void searchbyCompanyName_found() {
        List<Person> persons = new ArrayList<>();
        persons.add(person.get());
        doReturn(persons).when(personDao).findByCompany(TESTOWA_FIRMA);
        doReturn(user).when(transformer).toUserDomain(person.get());

        List<User> users = testClass.searchByCompanyName(TESTOWA_FIRMA);
        assertEquals(1, users.size());
        assertEquals(JAN, users.get(0).getFirstName());
    }

    @Test
    public void searchByCompanyName_not_found() {
        List<Person> persons = new ArrayList<>();
        doReturn(persons).when(personDao).findByCompany(TESTOWA_FIRMA);
        List<User> users = testClass.searchByCompanyName(TESTOWA_FIRMA);
        assertTrue(users.isEmpty());
    }

    @Test
    public void deleteById_is_done_by_dao_deleted() {
        doNothing().when(personDao).deleteById(any(Integer.class));

        testClass.deleteById(Integer.valueOf(1));
        verify(personDao, times(1)).deleteById(Integer.valueOf(1));
    }

    @Test(expected = Exception.class)
    public void mock_db_exception() { doThrow(new Exception("bad db")).when(personDao).deleteById(any(Integer.class));}

    @Before
    public void setup() {
        person.get().setfName(JAN);
        user.setFirstName(JAN);
    }


}
