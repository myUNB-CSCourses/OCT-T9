package project.team9;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class TeacherTest{

RegularTeacher teacher1;
RegularTeacher teacher2;
RegularTeacher teacher3;
RegularTeacher teacher4;
RegularTeacher teacher5;
RegularTeacher teacher6;

Course course1;
Course course2;
Course course3;
Course course4;


Schedule schedule1;
Schedule schedule2;
Schedule schedule3;
Schedule schedule4;
Schedule schedule5;
Schedule schedule6;

	@BeforeEach
	public void setUp() {
		
		course1 = new Course("Computer Science", "CS1083", "Computer Technology", "11", "University preparation");
		course2 = new Course("Software Engineering", "CS2043", "Computer Technology", "15", "Workplace Preparation");
		course3 = new Course("Technological Education", "TEJ3M", "Computer Engineering Technology", "10", "University/College Preparation");
		course4 = new Course("Science", "SNC4E", "Science", "12", "Workplace Preparation");
		
		schedule1 = new Schedule(course1, course2, course3, course4);
		
		teacher1 = new RegularTeacher("Kendal Wilton", schedule1);
		
	}
	
	@AfterEach
	public void tearDown() {
		
	}
	
	@Test
	public void testTeacherConstructor() {
		String expectedName = "Kendal Wilton";
		String nameResult = teacher1.getName();
		assertEquals(expectedName, nameResult);
		
		int expectedCode = 100;
		int resultCode = teacher1.getId();
		assertEquals(expectedCode, resultCode);
		
	}


}