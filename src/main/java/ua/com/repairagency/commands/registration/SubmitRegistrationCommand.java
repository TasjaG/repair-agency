package ua.com.repairagency.commands.registration;

import ua.com.repairagency.commands.interfaces.ICommand;
import ua.com.repairagency.services.ConfigurationManagerService;
import ua.com.repairagency.services.MessageManagerService;
import ua.com.repairagency.services.SubmitRegistrationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SubmitRegistrationCommand implements ICommand {

    private static final String LOGIN = "login";    // TODO mandatory, should have uniqueness check
    private static final String PASSWORD_1 = "password1";   // TODO mandatory, should have uniqueness check
    private static final String PASSWORD_2 = "password2";
    private static final String FIRST_NAME = "firstName";   // TODO mandatory
    private static final String MIDDLE_NAME = "middleName";
    private static final String LAST_NAME = "lastName";     // TODO mandatory
    private static final String EMAIL = "email";            // TODO mandatory
    private static final String PHONE_NUMBER = "phoneNumber";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        ConfigurationManagerService config = ConfigurationManagerService.getInstance();

        String password1 = request.getParameter(PASSWORD_1);
        String password2 = request.getParameter(PASSWORD_2);

        // checks if two password inputs match
        if(password1.equals(password2)) {
            String login = request.getParameter(LOGIN);
            String password = password1;
            String firstName = request.getParameter(FIRST_NAME);
            String middleName = request.getParameter(MIDDLE_NAME);
            String lastName = request.getParameter(LAST_NAME);
            String email = request.getParameter(EMAIL);
            String phoneNumber = request.getParameter(PHONE_NUMBER);

            SubmitRegistrationService.registerUser(login, password, firstName, middleName, lastName, email, phoneNumber);

            // after registering, the user is redirected to the login page
            page = config.getProperty(ConfigurationManagerService.LOGIN_PAGE);
        } else {

            // TODO should be a popup instead
            request.setAttribute("error",
                    MessageManagerService.getInstance().getProperty(MessageManagerService.IO_EXCEPTION_MESSAGE));
            page = config.getProperty(ConfigurationManagerService.ERROR_PAGE);
        }
        return page;
    }
}
