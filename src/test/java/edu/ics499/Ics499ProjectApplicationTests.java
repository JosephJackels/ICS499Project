package edu.ics499;

import static org.junit.Assert.*;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.*;

import edu.ics499.model.*;
import edu.ics499.model.widgets.*;

@SpringBootTest
class Ics499ProjectApplicationTests {

	@Test
	void contextLoads() {
	    testDashboard();
	    testWidget();
	    testUser();
	}

    public void testDashboard() {
        Dashboard dashboard = new Dashboard();
        dashboard.setDashboardID(null);
        assertEquals(dashboard.getDashboardID(), null);
    }

    public void testWidget() {
        Widget widget = new Widget();
        widget.setWidgetID(null);
        assertEquals(widget.getWidgetID(), null);
    }

    public void testUser() {
        User user = new User();
        user.setUsername("user12345");
        assertEquals(user.getUsername(), "user12345");
    }
}
