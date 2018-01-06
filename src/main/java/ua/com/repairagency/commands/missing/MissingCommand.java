package ua.com.repairagency.commands.missing;

import ua.com.repairagency.commands.interfaces.ICommand;
import ua.com.repairagency.services.ConfigurationManagerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MissingCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        return ConfigurationManagerService.getInstance().getProperty(ConfigurationManagerService.LOGIN_PAGE);
    }
}
