package ua.com.repairagency.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/** Filter that makes sure the encoding is set to "UTF-8". */
@WebFilter( urlPatterns = { "/*" },
            initParams  = { @WebInitParam( name = "encoding", value = "UTF-8", description = "Encoding Param") } )
public class EncodingFilter implements Filter {

    private String encoding;

    /** Initializes filter config. */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
    }

    /** Filters requests and responses, changing encoding if needed. */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                            throws IOException, ServletException {
        String requestEncoding = request.getCharacterEncoding();
        if (encoding != null && !encoding.equalsIgnoreCase(requestEncoding)) {
            request.setCharacterEncoding(encoding);
            response.setCharacterEncoding(encoding);
        }
        chain.doFilter(request, response);
    }

    /** Sets encoding to null. */
    @Override
    public void destroy() {
        encoding = null;
    }
}
