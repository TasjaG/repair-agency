package ua.com.repairagency.commands.missing;

import ua.com.repairagency.commands.interfaces.ICommand;
import ua.com.repairagency.services.ConfigurationManagerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** Stand-in command class for when a non-existent command is passed to the controller servlet. */
public class MissingCommand implements ICommand {

    /** Redirects to the login page. */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        return ConfigurationManagerService.getInstance().getProperty(ConfigurationManagerService.LOGIN_PAGE);
    }
}
