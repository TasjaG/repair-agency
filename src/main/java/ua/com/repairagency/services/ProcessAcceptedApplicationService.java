package ua.com.repairagency.services;

import org.apache.log4j.Logger;
import ua.com.repairagency.dao.factory.DAOFactory;
import ua.com.repairagency.dao.interfaces.IAcceptedApplicationDAO;

import java.sql.SQLException;

/** Service class for completing an order. */
public class ProcessAcceptedApplicationService {

    private static final Logger log = Logger.getLogger(ProcessAcceptedApplicationService.class);

    /**
     * Completes an order.
     *
     * @param id    to-be-completed accepted application's id
     */
    public static void completeOrder(int id) {
        DAOFactory daoFactory = new DAOFactory();
        IAcceptedApplicationDAO acceptedApplicationDAO = daoFactory.getMySQLAcceptedApplicationDAO();

        try {
            acceptedApplicationDAO.completeAcceptedApplication(id);
        } catch (SQLException ex) {
            log.error("Problem completing order:", ex);
        }
    }
}