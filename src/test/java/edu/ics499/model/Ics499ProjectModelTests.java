package edu.ics499.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.context.SpringBootTest.*;
import org.springframework.test.context.junit.jupiter.*;

import edu.ics499.model.widgets.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

public class Ics499ProjectModelTests {

    @Autowired
    Dashboard aDashboard;
    @Autowired
    User aUser;
    @Autowired
    Widget aWidget;

    @Test
    public void testDashboard() {
        aDashboard = new Dashboard();
        aDashboard.setDashboardID(54321L);
        Long aDashboardID = aDashboard.getDashboardID();

        assertThat(aDashboard != null);
        assertThat(aDashboardID == 54321L);
    }

    @Test
    public void testUser() {
        aUser = new User();
        aUser.setUserID(86420L);
        Long aUserID = aUser.getUserID();

        assertThat(aUser != null);
        assertThat(aUserID == 86420L);
    }

    @Test
    public void testWidget() {
        aWidget = new Widget();
        aWidget.setWidgetID(97531L);
        Long aWidgetID = aWidget.getWidgetID();

        assertThat(aWidget != null);
        assertThat(aWidgetID == 97531L);
    }
}
