package project.team9;

import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SelectClasses;


@Suite
@SelectClasses ({TeacherTest.class, CourseTest.class, MasterScheduleReaderTest.class, ScheduleTest.class, ConfigFileReaderTest.class})
class AllTests {

}
