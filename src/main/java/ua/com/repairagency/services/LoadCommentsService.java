package ua.com.repairagency.services;

import ua.com.repairagency.dao.entities.Comment;
import ua.com.repairagency.dao.factory.DAOFactory;
import ua.com.repairagency.dao.interfaces.ICommentDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class LoadCommentsService {

    public static void loadComments(HttpServletRequest request) {
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

        ICommentDAO commentDAO = DAOFactory.getMySQLCommentDAO();
        List<Comment> list = null;
        int numOfRecords = 0;

        try {
            list = commentDAO.getComments(start, total);
        } catch (SQLException e) {
            // TODO Logger
        }

        try {
            numOfRecords = commentDAO.numberOfRecords();
        } catch (SQLException e) {
            // TODO logger
        }

        int numOfPages = (int) Math.ceil(numOfRecords * 1.0 / total);
        request.setAttribute("commentList", list);
        request.setAttribute("numOfPages", numOfPages);
        request.setAttribute("pageNum", pageNum);
    }
}