package ua.com.repairagency.commands.login;

import ua.com.repairagency.commands.interfaces.ICommand;
import ua.com.repairagency.properties.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/** Class for the register command. */
public class RegisterCommand implements ICommand {

    /** Redirects to the registration page. */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String page = null;

        ConfigurationManager config = ConfigurationManager.getInstance();
        HttpSession session = request.getSession(false);

        // if no session exists, user is redirected to login page
        if (session != null) {
            page = config.getProperty(ConfigurationManager.REGISTER_PAGE);
        } else {
            page = config.getProperty(ConfigurationManager.LOGIN_PAGE);
        }

        return page;
    }
}
