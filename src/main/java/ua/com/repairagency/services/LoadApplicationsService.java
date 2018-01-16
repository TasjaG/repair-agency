package ua.com.repairagency.services;

import ua.com.repairagency.dao.entities.Application;
import ua.com.repairagency.dao.factory.DAOFactory;
import ua.com.repairagency.dao.interfaces.IApplicationDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class LoadApplicationsService {

    public static void loadApplications(HttpServletRequest request) {
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

        IApplicationDAO applicationDAO = DAOFactory.getMySQLApplicationDAO();
        List<Application> list = null;
        int numOfRecords = 0;

        try {
            numOfRecords = applicationDAO.numberOfRecords();
        } catch (SQLException e) {
            // TODO logger
        }

        try {

            // list = commentDAO.getComments(start, total);
            list = applicationDAO.getApplications(1, numOfRecords);

        } catch (SQLException e) {
            // TODO Logger
        }

        int numOfPages = (int) Math.ceil(numOfRecords * 1.0 / total);
        request.setAttribute("applicationsList", list);
        request.setAttribute("numOfPages", numOfPages);
        request.setAttribute("pageNum", pageNum);
    }
}
