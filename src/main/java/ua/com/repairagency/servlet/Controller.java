package ua.com.repairagency.servlet;

import org.apache.log4j.Logger;
import ua.com.repairagency.commands.interfaces.ICommand;
import ua.com.repairagency.services.ChangeLocalizationService;
import ua.com.repairagency.properties.ConfigurationManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

import static ua.com.repairagency.services.ChangeLocalizationService.setLocale;

@WebServlet (name="Controller", urlPatterns=("/Controller"))
public class Controller extends HttpServlet {

    private static final Logger log = Logger.getLogger(Controller.class);

    /** An object that contains the list of all Commands. */
    private final ControllerHelper controllerHelper = ControllerHelper.getInstance();

    public Controller() {
        super();
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = null;
        int maxIdle = 1800;  // 30 min

        HttpSession session = request.getSession(false);

        if (session == null) {
            session = request.getSession(true);
            session.setMaxInactiveInterval(maxIdle);
        }

        if (session.getAttribute("locale") == null) {
            setLocale(session, "EN");   // English is the most commonly used language
        }

        ICommand command = controllerHelper.getCommand(request);

        try{
            page = command.execute(request, response);
        }catch (ServletException ex){
            log.fatal("Problem executing command:", ex);
            request.setAttribute("error",
                    ChangeLocalizationService.getAttribute(session,"servletExceptionMessage"));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE);
        }catch(IOException ex){
            log.fatal("Problem executing command:", ex);
            request.setAttribute("error",
                    ChangeLocalizationService.getAttribute(session,""));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE);
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    /** Both doGet and doPost call the processRequest method. */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** Both doGet and doPost call the processRequest method. */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** Additional information about the Controller servlet. */
    @Override
    public String getServletInfo() {
        return "Controller servlet for the Repair Agency.";
    }
}
