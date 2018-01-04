package ua.com.repairagency.commands.user;

import ua.com.repairagency.commands.interfaces.ICommand;
import ua.com.repairagency.dao.entities.AcceptedApplication;
import ua.com.repairagency.dao.entities.Application;
import ua.com.repairagency.dao.factory.DAOFactory;
import ua.com.repairagency.dao.interfaces.IAcceptedApplicationDAO;
import ua.com.repairagency.dao.interfaces.IApplicationDAO;
import ua.com.repairagency.properties.ConfigurationManager;
import ua.com.repairagency.properties.MessageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

// TODO
public class SubmitApplicationCommand implements ICommand {

    // TODO
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String page = null;

        DAOFactory daoFactory = new DAOFactory();
        IApplicationDAO applicationDAO = daoFactory.getMySQLApplicationDAO();
        IAcceptedApplicationDAO acceptedApplicationDAO = daoFactory.getMySQLAcceptedApplicationDAO();

        try {
            // changes status of application to accepted
            int id = Integer.valueOf(request.getParameter("id"));
            applicationDAO.acceptApplication(id);

            // create new accepted application
            Application application = applicationDAO.getApplication(id);
            double price = Double.valueOf(request.getParameter("price"));
            AcceptedApplication acceptedApplication = new AcceptedApplication(application.getProductName(),
                    application.getProductComment(), price, application.getId(), application.getUserId());

            acceptedApplicationDAO.addAcceptedApplication(acceptedApplication);

            // TODO different page
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE);
        } catch (SQLException ex) {

            // TODO Logger
            System.out.println(ex);

            // TODO change to SQL_EXCEPTION_MESSAGE
            request.setAttribute("errorMessage",
                    MessageManager.getInstance().getProperty(MessageManager.IO_EXCEPTION_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE);
        }

        return page;
    }
}
