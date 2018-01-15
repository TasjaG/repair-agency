package ua.com.repairagency.commands.login;

import ua.com.repairagency.commands.interfaces.ICommand;
import ua.com.repairagency.services.ConfigurationManagerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.com.repairagency.services.LoadCommentsService.loadComments;

public class LoadCommentsCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String page = null;

        String login = request.getParameter("user");
        request.setAttribute("user", login);

        loadComments(request);

        page = ConfigurationManagerService.getInstance().getProperty(ConfigurationManagerService.COMMENTS_PAGE);
        return page;
    }
}
