package project.team9;

public class TeacherTest{

Teacher teacher1;
Teacher teacher2;
Teacher teacher3;
Teacher teacher4;
Teacher teacher5;
Teacher teacher6;
Teacher teacher7;
Teacher teacher8;

	@BeforeEach
	public void setUp() {
		teacher1 = new CourseTest("Kendal Wilton");
		teacher2 = new CourseTest("Cordell Warrick");
		teacher3 = new CourseTest("Rudolph Boyce");
		teacher4 = new CourseTest("Michael Karson");
		teacher5 = new CourseTest("Kairo Fitz");
		teacher6 = new CourseTest("Chauncey Nik");
		teacher7 = new CourseTest("Marshall Joss");
		teacher8 = new CourseTest("Vance Mervyn");
	}
	
		@AfterEach
	public void tearDown() {
		teacher1.clearRating();
		teacher2.clearRating();
		teacher3.clearRating();
		teacher4.clearRating();
		teacher5.clearRating();
		teacher6.clearRating();
		teacher7.clearRating();
		teacher8.clearRating();
	}
	
	@Test
	public void testTeacherConstructor() {
		String expectedName = "Kendal Wilton";
		String nameResult = teacher1.getName();
		assertEquals(expectedName, nameResult);
	}


}