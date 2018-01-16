package ua.com.repairagency.services;

import ua.com.repairagency.dao.entities.AcceptedApplication;
import ua.com.repairagency.dao.factory.DAOFactory;
import ua.com.repairagency.dao.interfaces.IAcceptedApplicationDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class LoadAcceptedAppsService {

    public static void loadAcceptedApps(HttpServletRequest request) {
        int pageNum = 1;
        int total = 5;
        int start = 1;

        if(request.getParameter("pageNum") != null)
            pageNum = Integer.parseInt(request.getParameter("pageNum"));

        if(pageNum == 1){}
        else{
            start = pageNum - 1;
            start = start * total + 1;
        }

        IAcceptedApplicationDAO acceptedApplicationDAO = DAOFactory.getMySQLAcceptedApplicationDAO();
        List<AcceptedApplication> list = null;
        int numOfRecords = 0;

        try {
            numOfRecords = acceptedApplicationDAO.numberOfRecords();
        } catch (SQLException e) {
            // TODO logger
        }

        try {

            // list = commentDAO.getComments(start, total);
            list = acceptedApplicationDAO.getAcceptedApplications(1, numOfRecords);

        } catch (SQLException e) {
            // TODO Logger
        }

        int numOfPages = (int) Math.ceil(numOfRecords * 1.0 / total);
        request.setAttribute("acceptedAppsList", list);
        request.setAttribute("numOfPages", numOfPages);
        request.setAttribute("pageNum", pageNum);
    }
}
