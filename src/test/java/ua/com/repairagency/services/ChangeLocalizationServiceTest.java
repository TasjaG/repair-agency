package ua.com.repairagency.services;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static ua.com.repairagency.services.ChangeLocalizationService.setLocale;

// TODO
/** Checking if locale changes. */
public class ChangeLocalizationServiceTest {
/*
    private HttpSession session;

    @Before
    public void setUp() throws Exception {
        session = new HttpSession();

        if (session == null) {
            session = request.getSession(true);
            session.setMaxInactiveInterval(maxIdle);
        }

        if (session.getAttribute("locale") == null) {
            setLocale(session, "EN");   // English is the most commonly used language
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void setLocaleTest() {
        setLocale(null, null);
    }

    @Test
    public void getAttributeTest() {
    }*/
}