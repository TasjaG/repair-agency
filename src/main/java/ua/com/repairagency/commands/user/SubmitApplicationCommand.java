package ua.com.repairagency.commands.user;

import ua.com.repairagency.commands.interfaces.ICommand;
import ua.com.repairagency.dao.entities.AcceptedApplication;
import ua.com.repairagency.dao.entities.Application;
import ua.com.repairagency.dao.factory.DAOFactory;
import ua.com.repairagency.dao.interfaces.IAcceptedApplicationDAO;
import ua.com.repairagency.dao.interfaces.IApplicationDAO;
import ua.com.repairagency.services.ConfigurationManagerService;
import ua.com.repairagency.services.MessageManagerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static ua.com.repairagency.services.SubmitApplicationService.submitApplication;

// TODO
public class SubmitApplicationCommand implements ICommand {

    private static final String PARAM_NAME_USER_NAME = "user";
    private static final String PARAM_NAME_PRODUCT_NAME = "product_name";
    private static final String PARAM_NAME_PRODUCT_COMMENT = "product_comment";

    // TODO
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        String userName = request.getParameter(PARAM_NAME_USER_NAME);
        String productName = request.getParameter(PARAM_NAME_PRODUCT_NAME);
        String productComment = request.getParameter(PARAM_NAME_PRODUCT_COMMENT);

        request.setAttribute("user", userName);

        submitApplication(productName, productComment, userName);

        return ConfigurationManagerService.getInstance().getProperty(ConfigurationManagerService.MAIN_PAGE);
    }
}
