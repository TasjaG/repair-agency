package ua.com.repairagency.commands.login;

import ua.com.repairagency.commands.interfaces.ICommand;
import ua.com.repairagency.services.ConfigurationManagerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/** Class for the logout command. */
public class LogoutCommand implements ICommand {

    /** Terminates user's session. */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String page = null;

        ConfigurationManagerService config = ConfigurationManagerService.getInstance();
        HttpSession session = request.getSession(false);

        // session is only terminated if one exists
        if (session != null) {
            session.invalidate();
        }

        // user is redirected to login page whether session existed or not
        page = config.getProperty(ConfigurationManagerService.LOGIN_PAGE);

        return page;
    }
}
