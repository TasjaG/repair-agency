package ua.com.repairagency.commands.interfaces;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// TODO all the Commands
public interface ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response)
                            throws ServletException, IOException;
}
