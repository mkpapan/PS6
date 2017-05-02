package base;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.UUID;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import domain.PersonDomainModel;
import util.LocalDateAdapter;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class Person_Test {
private static PersonDomainModel person1;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		Date dDate = null;
		try {
			dDate = new SimpleDateFormat("yyyy-MM-dd").parse("1998-04-28");

		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		person1 = new PersonDomainModel();
		person1.setFirstName("mary");
		person1.setLastName("papandrea");
		person1.setBirthday("1998-04-28");
		person1.setCity("west hartford");
		person1.setPostalCode(06107);
		person1.setStreet("39 stoner drive");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		PersonDomainModel per;	
		PersonDAL.deletePerson(person1.getPersonID());
		per = PersonDAL.getPerson(person1.getPersonID());
		assertNull("This dude is not in",per);		
	}
	
	@Test
	public void AddPersonTest()
	{		
		PersonDomainModel per;		
		person1 = PersonDAL.getPerson(person1.getPersonID());		
		assertNull("nope not here dude");		
		PersonDAL.addPerson(person1);	
		
		per = PersonDAL.getPerson(person1.getPersonID());
		assertNotNull("This dude is now in");
	}
	
	@Test
	public void BUpdatePersonTest()
	{		
		PersonDomainModel per = null;
		final String C_LASTNAME = "Smith";
		
		person1 = PersonDAL.getPerson(per.getPersonID());		
		assertNull("This person should not be in the database:",person1);		
		PersonDAL.addPerson(person1);	
		
		person1.setLastName(C_LASTNAME);
		PersonDAL.updatePerson(person1);
		
		person1 = PersonDAL.getPerson(person1.getPersonID());

		assertTrue("Name is the same my dude",person1.getLastName() == C_LASTNAME);
	}

	@Test
	public void DeletePersonTest()
	{		
		PersonDomainModel per;		
		person1 = PersonDAL.getPerson(person1.getPersonID());		
		assertNull("not there dude");	
		
		PersonDAL.addPerson(person1);			
		person1 = PersonDAL.getPerson(person1.getPersonID());
		assertNotNull("should be there dude");
		
		PersonDAL.deletePerson(person1.getPersonID());
		person1 = PersonDAL.getPerson(person1.getPersonID());		
		assertNull("shouldnt be there anymore dude");	
		
	}
	
	
}