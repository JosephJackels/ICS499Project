package edu.ics499;

import static org.junit.Assert.*;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.*;

import edu.ics499.model.*;

@SpringBootTest
class Ics499ProjectApplicationTests {

	@Test
	void contextLoads() {
	}

    public static void testDashboard() {
        Dashboard dashboard = new Dashboard();
        dashboard.setDashboardID(null);
        assertEquals(dashboard.getDashboardID(), null);
    }
}
