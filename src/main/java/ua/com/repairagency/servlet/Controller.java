package ua.com.repairagency.servlet;

import ua.com.repairagency.commands.interfaces.ICommand;
import ua.com.repairagency.services.ConfigurationManagerService;
import ua.com.repairagency.services.MessageManagerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet (name="Controller", urlPatterns=("/Controller"))
public class Controller extends HttpServlet {

    /** An object that contains the list of all Commands. */
    private final ControllerHelper controllerHelper = ControllerHelper.getInstance();

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

            request.setAttribute("error",
                    MessageManagerService.getInstance().getProperty(MessageManagerService.SERVLET_EXCEPTION_MESSAGE));
            page = ConfigurationManagerService.getInstance().getProperty(ConfigurationManagerService.ERROR_PAGE);
        }catch(IOException e){

            // TODO Logger
            request.setAttribute("error",
                    MessageManagerService.getInstance().getProperty(MessageManagerService.IO_EXCEPTION_MESSAGE));
            page = ConfigurationManagerService.getInstance().getProperty(ConfigurationManagerService.ERROR_PAGE);
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controller servlet.";
    }
}
