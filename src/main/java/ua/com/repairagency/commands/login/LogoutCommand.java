package ua.com.repairagency.commands.login;

import ua.com.repairagency.commands.interfaces.ICommand;
import ua.com.repairagency.properties.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ua.com.repairagency.services.ChangeLocalizationService.setLocale;

/** Class for the logout command. */
public class LogoutCommand implements ICommand {

    /** Terminates user's session. */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String page = null;

        ConfigurationManager config = ConfigurationManager.getInstance();
        HttpSession session = request.getSession(false);

        // session is only terminated if one exists
        if (session != null) {
            session.invalidate();
            int maxIdle = 1800;  // 30 min

            session = request.getSession(true);
            session.setMaxInactiveInterval(maxIdle);
            setLocale(session, "EN");   // English is the most commonly used language
        }

        // user is redirected to login page whether session existed or not
        page = config.getProperty(ConfigurationManager.LOGIN_PAGE);

        return page;
    }
}
