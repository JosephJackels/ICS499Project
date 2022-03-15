package edu.ics499.controllers;

import static org.assertj.core.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.context.SpringBootTest.*;
import org.springframework.test.context.junit.jupiter.*;

import edu.ics499.model.*;
import edu.ics499.model.widgets.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

public class Ics499ProjectControllerTests {

    @Autowired
    DashboardController dashboardController;
    @Autowired
    UserController userController;
    @Autowired
    WidgetController widgetController;

    @Test
    public void testDashboardController() {
        List<Dashboard> aDashboardList;
        Dashboard aDashboard = new Dashboard();
        aDashboard.setDashboardID(12345L);
        dashboardController.create(aDashboard);
        aDashboardList = dashboardController.list();

        assertThat(aDashboard != null);
        assertThat(aDashboard.getDashboardID() == 12345L);
        assertThat(aDashboardList.contains(aDashboard));
    }

    @Test
    public void testUserContoller() {
        User aUser = new User();
        aUser.setUserID(24680L);
        List<User> aUserList;
        userController.create(aUser);
        aUserList = userController.list();

        assertThat(aUser != null);
        assertThat(aUser.getUserID() == 24680L);
        assertThat(aUserList.contains(aUser));
    }

    @Test
    public void testWidgetContoller() {
        List<Widget> aWidgetList;
        Widget aWidget = new Widget();
        aWidget.setWidgetID(35791L);
        aWidgetList = widgetController.list();

        assertThat(aWidget != null);
        assertThat(aWidget.getWidgetID() == 35791L);
        assertThat(aWidgetList.contains(aWidget));
    }
}
