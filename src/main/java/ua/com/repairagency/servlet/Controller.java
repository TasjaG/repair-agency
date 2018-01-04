package ua.com.repairagency.servlet;

import ua.com.repairagency.commands.interfaces.ICommand;
import ua.com.repairagency.properties.ConfigurationManager;
import ua.com.repairagency.properties.MessageManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    /** An object that contains the list of all Commands. */
    private ControllerHelper controllerHelper = ControllerHelper.getInstance();

    public Controller() {
        super();
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = null;

        try{
            ICommand command = controllerHelper.getCommand(request);
            page = command.execute(request, response);
        }catch (ServletException e){

            // TODO Logger

            request.setAttribute("errorMessage",
                    MessageManager.getInstance().getProperty(MessageManager.SERVLET_EXCEPTION_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE);
        }catch(IOException e){

            // TODO Logger
            request.setAttribute("errorMessage",
                    MessageManager.getInstance().getProperty(MessageManager.IO_EXCEPTION_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE);
        }

        // response.sendRedirect(page);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controller servlet.";
    }
}
