package project.team9;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class MasterScheduleReaderTest {
	MasterScheduleReader object;
	String fullCode;
	Course a;
	Course b;
	ArrayList<Course> curriculum;


	@Test
	void testMatchCourse() {
		Course a = new Course("Interdisciplinary Studies", "IDC3O/IDP3O", "Interdisciplinary Studies", "11", "Open");
		Course b = new Course("The Arts", "ATC3M", "Dance", "11", "University/College Preparation");
		object = new MasterScheduleReader();
		fullCode = "IDC3O1-01";
		ArrayList<Course> curriculum = new ArrayList<Course>();
		curriculum.add(a);
		curriculum.add(b);
		
		
		
		String expected = a.toString();
		Course res = object.matchCourse(fullCode, curriculum);
		System.out.println(expected);
		String result = res.toString();
		assertEquals(expected,result);
	}


}