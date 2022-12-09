package project.team9;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ConfigFileReaderTest {
	ConfigFileReader config;
	
	@Test
	void testConfigReadString() {
		config = new ConfigFileReader();
		String result = config.configRead("MASTER_SCHEDULE");
		String expected = "src/inputs/MasterSchedule.xlsx";
		assertEquals(expected,result);
	}
	
	@Test
	void testConfigReadNumbers() {
		config = new ConfigFileReader();
		String result = config.configRead("MAX_TERM_TALLEYS");
		String expected = "4";
		assertEquals(expected,result);
	}


}

