package ua.com.repairagency.commands.login;

import ua.com.repairagency.commands.interfaces.ICommand;
import ua.com.repairagency.properties.ConfigurationManager;
import ua.com.repairagency.properties.MessageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

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

        if (checkLogin(login, password)){
            //request.setAttribute("user", login);
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE);
        } else {
            request.setAttribute("errorMessage",
                    MessageManager.getInstance().getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE);
        }
        return page;
    }

    /** Checks whether the user with the specified login and password exists. */
    private boolean checkLogin(String login, String password) {

        try{

            // use connection pool instead
            String driver = ConfigurationManager.getInstance()
                    .getProperty(ConfigurationManager.DRIVER);
            Class.forName(driver);
            Connection conn = null;

            try {
                String url = ConfigurationManager.getInstance()
                        .getProperty(ConfigurationManager.URL);
                conn = DriverManager.getConnection(url);
                PreparedStatement st = null;

                try {
                    st = conn.prepareStatement("SELECT * FROM users WHERE user_login=? AND user_password=?");
                    st.setString(1, login);
                    st.setString(2, password);
                    ResultSet rs = null;

                    try {
                        rs = st.executeQuery();

                        // does such a user exist?
                        // TODO add check of user type
                        return rs.next();
                    } finally {

                        if (rs != null)
                            rs.close();
                    }
                } finally {
                    if (st != null)
                        st.close();
                }
            } finally {
                if (conn != null)
                    conn.close();
            }
        }catch (SQLException ex){

            // TODO Logger
            return false;
        } catch (ClassNotFoundException ex){

            // TODO Logger
            return false;
        }
    }
}
