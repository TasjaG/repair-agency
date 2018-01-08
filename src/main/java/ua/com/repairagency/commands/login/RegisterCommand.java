package ua.com.repairagency.commands.login;

import ua.com.repairagency.commands.interfaces.ICommand;
import ua.com.repairagency.services.ConfigurationManagerService;
import ua.com.repairagency.services.LoginService;
import ua.com.repairagency.services.MessageManagerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// TODO
public class RegisterCommand implements ICommand {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String FIRST_NAME = "firstName";
    private static final String MIDDLE_NAME = "middleName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL = "email";
    private static final String PHONE_NUMBER = "phoneNumber";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String page = null;
        ConfigurationManagerService config = ConfigurationManagerService.getInstance();

        // only allows to register if link is accessed from login page
        if (request.getRequestURL().equals(config.getProperty(ConfigurationManagerService.LOGIN_PAGE))){
            page = config.getProperty(ConfigurationManagerService.REGISTER_PAGE);
        } else {

            // TODO Logger
            request.setAttribute("error",
                    MessageManagerService.getInstance().getProperty(MessageManagerService.ILLEGAL_ACCESS_ERROR_MESSAGE));
            page = config.getProperty(ConfigurationManagerService.ERROR_PAGE);
        }
        return page;
    }
}
