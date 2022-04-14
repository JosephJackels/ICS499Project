package edu.ics499;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.*;

import edu.ics499.model.*;
import edu.ics499.model.widgets.*;

@SpringBootTest
class Ics499ProjectApplicationTests {

	@Test
	void contextLoads() throws IOException, InterruptedException {
	    testDashboard();
	    testWidget();
	    testUser();
	}

	/**
	public void testPayload() throws IOException {
        CurrentWeatherPayload payload = WeatherWidgetServiceImp.requestCurrentWeather("london");
        assertEquals(payload.getName(), "London");
        assertEquals(payload.getSys_country(), "GB");
        assertEquals(payload.getCod(), "200");
        assertEquals(payload.getId(), "2643743");
        assertEquals(payload.getTimezone(), "0");
        assertTrue(payload.getClouds_all() != null);
	}
    */

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
