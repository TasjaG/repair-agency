package ua.com.repairagency.commands.login;

import ua.com.repairagency.commands.interfaces.ICommand;
import ua.com.repairagency.dao.entities.Comment;
import ua.com.repairagency.dao.factory.DAOFactory;
import ua.com.repairagency.dao.interfaces.ICommentDAO;
import ua.com.repairagency.dao.sql.MySQLCommentDAO;
import ua.com.repairagency.services.ConfigurationManagerService;
import ua.com.repairagency.services.MessageManagerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import ua.com.repairagency.services.LoginService;

// TODO select and set type of user - use response(?)
public class LoginCommand implements ICommand {

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String page = null;

        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);

        if (LoginService.authenticateUser(login, password)){
            
            // TODO add login attribute to login.jsp
            // request.setAttribute("user", login);

            int pageNum = 1;
            int total = 5;

            if(request.getParameter("page") != null)
                pageNum = Integer.parseInt(request.getParameter("page"));

            if(pageNum == 1){}
            else{
                pageNum = pageNum - 1;
                pageNum = pageNum * total + 1;
            }

            ICommentDAO commentDAO = DAOFactory.getMySQLCommentDAO();
            List<Comment> list = null;

            try {
                list = commentDAO.getCommments(pageNum, total);
            } catch (SQLException e) {
                // TODO Logger
            }

            // TODO add num of records method to DAO
            //int noOfRecords = commentDAO.getNoOfRecords();
            //int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / total);
            request.setAttribute("employeeList", list);
            //request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);

            page = ConfigurationManagerService.getInstance().getProperty(ConfigurationManagerService.MAIN_PAGE);
        } else {
                    request.setAttribute("error",
                    MessageManagerService.getInstance().getProperty(MessageManagerService.LOGIN_ERROR_MESSAGE));
            page = ConfigurationManagerService.getInstance().getProperty(ConfigurationManagerService.ERROR_PAGE);
        }
        return page;
    }
}
