package ua.com.repairagency.commands.login;

import ua.com.repairagency.commands.interfaces.ICommand;
import ua.com.repairagency.services.ConfigurationManagerService;
import ua.com.repairagency.services.MessageManagerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import ua.com.repairagency.services.LoginService;

// TODO select and set type of user - use response(?)
public class LoginCommand implements ICommand {

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String page = null;

        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);

        if (LoginService.authenticateUser(login, password)){
            
            // TODO add login attribute to login.jsp
            // request.setAttribute("user", login);
            page = ConfigurationManagerService.getInstance().getProperty(ConfigurationManagerService.MAIN_PAGE);
        } else {
                    request.setAttribute("error",
                    MessageManagerService.getInstance().getProperty(MessageManagerService.LOGIN_ERROR_MESSAGE));
            page = ConfigurationManagerService.getInstance().getProperty(ConfigurationManagerService.ERROR_PAGE);
        }
        return page;
    }
}
