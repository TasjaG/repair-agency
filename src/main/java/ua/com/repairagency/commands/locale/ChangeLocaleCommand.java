package ua.com.repairagency.commands.locale;

import ua.com.repairagency.commands.interfaces.ICommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLocaleCommand implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        return null;
    }
}
