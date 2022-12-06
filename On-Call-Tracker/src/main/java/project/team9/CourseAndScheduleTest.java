package project.team9;

public class CourseAndScheduleTest {
	
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
	
	RegularTeacher regularTeacher1;
	RegularTeacher regularTeacher2;
	RegularTeacher regularTeacher3;
	
	@BeforeEach
	public void setUp() {
		course1 = new Course("259611", "Kendal Wilton", "HC10");
		course2 = new Course("389250", "Cordell Warrick", "T307");
		course3 = new Course("877683", "Rudolph Boyce", "IT416");
		course4 = new Course("531665", "Michael Karson", "IT417");
		course5 = new Course("451411", "Kairo Fitz", "IT418");
		course6 = new Course("686561", "Chauncey Nik", "IT419");
		course7 = new Course("065905", "Marshall Joss", "IT430");
		course8 = new Course("183418", "Vance Mervyn", "IT440");
		
		schedule1 = new Schedule(course1, course2, course3, course4);
		schedule2 = new Schedule(course5, course6, course7, course8);
		schedule3 = new Schedule(course4, course6, course5, course1);
		
		regularTeacher1 = new RegularTeacher("Nic Maegan", schedule1);
		regularTeacher2 = new RegularTeacher("Guy Pamelia", schedule2);
		regularTeacher3 = new RegularTeacher("Les Inigo", schedule3);
	}
	
	@AfterEach
	public void tearDown() {
		course1.clearRating();
		course2.clearRating();
		course3.clearRating();
		course4.clearRating();
		course5.clearRating();
		course6.clearRating();
		course7.clearRating();
		course8.clearRating();
		
		schedule1.clearRating();
		schedule2.clearRating();
		schedule3.clearRating();
		
		regularTeacher1.clearRating();
		regularTeacher2.clearRating();
		regularTeacher3.clearRating();
	}
	
	@Test
	public void testCourseConstructor() {
		String expectedCode = "259611";
		String expectedName = "Kendal Wilton";
		String expectedRoom = "HC10";
		String codeResult = course1.getCode();
		String nameResult = course1.getName();
		String roomResult = course1.getRoom();
		assertEquals(expectedCode, codeResult);
		assertEquals(expectedName, nameResult);
		assertEquals(expectedRoom, roomResult);
	}
	
	@Test
	public void testRegularTeacherConstructor() {
		String expectedName = "Nic Maegan";
		String nameResult = regularTeacher1.getName();
		assertEquals(expectedName, nameResult);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
