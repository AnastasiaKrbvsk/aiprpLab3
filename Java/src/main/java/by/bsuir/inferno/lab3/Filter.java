package by.bsuir.inferno.lab3;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", filterName = "Filter")
public class Filter implements javax.servlet.Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.setCharacterEncoding("UTF-8");
        String requestURI = request.getRequestURI();
        String dispatchDestination;
        String contextPath = servletRequest.getServletContext().getContextPath();

        if (requestURI.equals(contextPath + "/")) {
            dispatchDestination = "/app";
        } else if (requestURI.equals(contextPath + "/get_image")) {
            dispatchDestination = "/image_servlet";
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        request.getRequestDispatcher(dispatchDestination).forward(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
