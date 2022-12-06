package project.team9;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MasterScheduleReaderTest {

	@BeforeEach
	void setUp() throws Exception {
		String fullCode = "IDC3O1-01";
		Course a = new Course("Interdisciplinary Studies", "IDC3O/IDP3O", "Interdisciplinary Studies", "11", "Open");
		Course b = new Course("The Arts", "ATC3M", "Dance", "11", "University/College Preparation");
		ArrayList<Course> curriculum = new ArrayList<Course>();
		curriculum.add(a);
		curriculum.add(b);
	}

	@Test
	void testMatchCourse() {
		String fullCode = "IDC3O1-01";
		String expected = a.toString();
		
		Course res = matchCourse(fullCode, curriculum);
		String result = res.toString();
		assertEquals(expected,result);
	}
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
