package ua.com.repairagency.commands.manager;

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

// TODO
public class ApproveApplicationCommand implements ICommand {

    // TODO
    @Override
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
            page = ConfigurationManagerService.getInstance().getProperty(ConfigurationManagerService.MAIN_PAGE);
        } catch (SQLException ex) {

            // TODO Logger
            System.out.println(ex);

            // TODO change to SQL_EXCEPTION_MESSAGE
            request.setAttribute("errorMessage",
                    MessageManagerService.getInstance().getProperty(MessageManagerService.IO_EXCEPTION_MESSAGE));
            page = ConfigurationManagerService.getInstance().getProperty(ConfigurationManagerService.ERROR_PAGE);
        }

        return page;
    }
}
