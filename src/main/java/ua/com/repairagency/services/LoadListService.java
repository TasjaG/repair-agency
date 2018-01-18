package ua.com.repairagency.services;

import ua.com.repairagency.dao.entities.AcceptedApplication;
import ua.com.repairagency.dao.entities.Application;
import ua.com.repairagency.dao.entities.Comment;
import ua.com.repairagency.dao.factory.DAOFactory;
import ua.com.repairagency.dao.interfaces.IAcceptedApplicationDAO;
import ua.com.repairagency.dao.interfaces.IApplicationDAO;
import ua.com.repairagency.dao.interfaces.ICommentDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

// TODO add pagination
/** Service class for loading lists of entities from the database. */
public class LoadListService {

    /** Loads comments. */
    public static void loadComments(HttpServletRequest request) {
        int pageNum = 1;
        int total = 5;
        int start = 1;

        if(request.getParameter("pageNum") != null)
            pageNum = Integer.parseInt(request.getParameter("pageNum"));

        if(pageNum == 1){}
        else{
            start = pageNum - 1;
            start = (pageNum - 1) * total + 1;
        }
        ICommentDAO commentDAO = DAOFactory.getMySQLCommentDAO();
        List<Comment> list = null;
        int numOfRecords = 0;

        try {
            numOfRecords = commentDAO.numberOfRecords();

            // TODO list = commentDAO.getComments(start, total);
            list = commentDAO.getComments(1, numOfRecords);
        } catch (SQLException e) {
            // TODO Logger
        }

        int numOfPages = (int) Math.ceil(numOfRecords * 1.0 / total);
        request.setAttribute("commentList", list);
        request.setAttribute("numOfPages", numOfPages);
        request.setAttribute("pageNum", pageNum);
    }

    /** Loads applications. */
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

            // TODO list = commentDAO.getComments(start, total);
            list = applicationDAO.getApplications(1, numOfRecords);
        } catch (SQLException e) {
            // TODO Logger
        }

        int numOfPages = (int) Math.ceil(numOfRecords * 1.0 / total);
        request.setAttribute("applicationsList", list);
        request.setAttribute("numOfPages", numOfPages);
        request.setAttribute("pageNum", pageNum);
    }

    /** Loads accepted applications. */
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

            // TODO list = commentDAO.getComments(start, total);
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