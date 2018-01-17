package ua.com.repairagency.services;

import ua.com.repairagency.dao.entities.AcceptedApplication;
import ua.com.repairagency.dao.entities.Application;
import ua.com.repairagency.dao.factory.DAOFactory;
import ua.com.repairagency.dao.interfaces.IAcceptedApplicationDAO;
import ua.com.repairagency.dao.interfaces.IApplicationDAO;

import java.sql.SQLException;

/** Service class for accepting/rejecting an application. */
public class ProcessApplicationService {

    /**
     * Accepts an application.
     *
     * @param id to-be-accepted application's id
     * @param price the cost of repairs
     */
    public static void acceptApplication(int id, double price) {
        IApplicationDAO applicationDAO = DAOFactory.getMySQLApplicationDAO();
        IAcceptedApplicationDAO acceptedApplicationDAO = DAOFactory.getMySQLAcceptedApplicationDAO();

        try {
            // changes status of application to accepted
            applicationDAO.acceptApplication(id);

            // create new accepted application using data of current application
            Application application = applicationDAO.getApplication(id);
            AcceptedApplication acceptedApplication = new AcceptedApplication(application.getProductName(),
                application.getProductComment(), price, application.getId(), application.getUserId());

            acceptedApplicationDAO.addAcceptedApplication(acceptedApplication);
        } catch (SQLException e) {
            // TODO Logger
        }
    }

    /**
     * Rejects an application.
     *
     * @param id to-be-rejected application's id
     * @param rejectionComment to-be-rejected application's reason it's being rejected
     */
    public static void rejectApplication(int id, String rejectionComment) {
        DAOFactory daoFactory = new DAOFactory();
        IApplicationDAO applicationDAO = daoFactory.getMySQLApplicationDAO();

        try {
            Application application = applicationDAO.getApplication(id);
            application.setComment(rejectionComment);
            applicationDAO.rejectApplication(application);
        } catch (SQLException e) {
            // TODO Logger
        }
    }
}
