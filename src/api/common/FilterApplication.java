package api.common;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by root on 27/09/15.
 */
@Provider
public class FilterApplication implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        res.setHeader("Access-Control-Allow-Origin", "*");
        res.addHeader("Access-Control-Allow-Headers", "Authorization, Origin, X-Requested-With, Content-Type, Accept");
        res.addHeader("AAccess-Control-Expose-Headers", "Location, Content-Disposition");
        res.addHeader("Access-Control-Allow-Methods", "POST, PUT, GET, DELETE, HEAD, OPTIONS");

        filterChain.doFilter(servletRequest, res);
    }

    @Override
    public void destroy() {

    }
}
