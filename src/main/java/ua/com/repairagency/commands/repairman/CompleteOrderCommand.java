package ua.com.repairagency.commands.repairman;

import ua.com.repairagency.commands.interfaces.ICommand;
import ua.com.repairagency.dao.factory.DAOFactory;
import ua.com.repairagency.dao.interfaces.IAcceptedApplicationDAO;
import ua.com.repairagency.services.ConfigurationManagerService;
import ua.com.repairagency.services.MessageManagerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

// TODO
public class CompleteOrderCommand implements ICommand {

    // TODO
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String page = null;

        DAOFactory daoFactory = new DAOFactory();
        IAcceptedApplicationDAO acceptedApplicationDAO = daoFactory.getMySQLAcceptedApplicationDAO();

        try {
            int id = Integer.valueOf(request.getParameter("id"));
            acceptedApplicationDAO.completeAcceptedApplication(id);

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
