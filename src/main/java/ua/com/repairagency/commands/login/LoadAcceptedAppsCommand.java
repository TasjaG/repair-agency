package ua.com.repairagency.commands.login;

import ua.com.repairagency.commands.interfaces.ICommand;
import ua.com.repairagency.services.ConfigurationManagerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.com.repairagency.services.LoadAcceptedAppsService.loadAcceptedApps;

public class LoadAcceptedAppsCommand implements ICommand {

    private static final String PARAM_NAME_USER_NAME = "user";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String userName = request.getParameter(PARAM_NAME_USER_NAME);
        request.setAttribute("user", userName);

        loadAcceptedApps(request);

        return ConfigurationManagerService.getInstance().getProperty(ConfigurationManagerService.COMMENTS_PAGE);
    }
}
