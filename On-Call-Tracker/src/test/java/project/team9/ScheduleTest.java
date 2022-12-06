package project.team9;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScheduleTest {

	Course course1;
	Course course2;
	Course course3;
	Course course4;
	Course course5;
	Course course6;
	Course course7;
	Course course8;
	
	Schedule schedule1;
	Schedule schedule2;
	Schedule schedule3;
	
	@BeforeEach
	public void setUp() {
		course1 = new Course("Software Engineering", "CS2043", "Computer Technology", "15", "Workplace Preparation");
		course2 = new Course("Health and Physical Education", "PPZ3C", "Health", "16", "Open");
		course3 = new Course("First Nations, Metis, and Inuit Studies", "NBV3C", "World Views and Aspirations of First Nations, Métis, and Inuit Communities in Canada", "15", "College preparation");
		course4 = new Course("Technological Education", "TWJ4E", "Custom Woodworking", "17", "Workplace Preparation");
		course5 = new Course("English", "ENG3E", "English", "15", "College preparation");
		course6 = new Course("Canadian and World Studies", "CHV2O", "Politics (Civics)", "18", "Open");
		course7 = new Course("The Arts", "ASM3M", "Media Arts", "15", "University/College Preparation");
		course8 = new Course("French as a Second Language", "FEF1D", "Extended French", "20", "Academic");
		
		schedule1 = new Schedule(course1, course2, course3, course4);
		schedule2 = new Schedule(course5, course6, course7, course8);
		schedule3 = new Schedule(course4, course6, course5, course1);
		
	}
	
	@AfterEach
	public void tearDown() {
		
	}
	
	@Test
	public void testScheduleConstructor() {
		String expectedPeriod1Course = "CS2043";
		String expectedPeriod2Course = "PPZ3C";
		String expectedPeriod3Course = "NBV3C";

		String period1CourseResult = schedule1.getPeriod1().getCode();
		String period2CourseResult = schedule1.getPeriod2().getCode();
		String period3CourseResult = schedule1.getPeriod3().getCode();
		assertEquals(expectedPeriod1Course, period1CourseResult);
		assertEquals(expectedPeriod2Course, period2CourseResult);
		assertEquals(expectedPeriod3Course, period3CourseResult);
	}

}
