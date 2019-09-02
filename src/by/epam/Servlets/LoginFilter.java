package by.epam.Servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {
    private ServletContext context;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String uri = request.getRequestURI();
        this.context.log("Requested Resource::"+uri);

        HttpSession session = request.getSession(false);

        if (session!=null){
            boolean authUser=(boolean)session.getAttribute("authuser");

            if (authUser){
                chain.doFilter(request, response);
            }
            else {
                response.sendRedirect("main.html");
            }
        }
        else {
            if (uri.endsWith("html")||uri.endsWith("/hello")||uri.endsWith("css")){
                chain.doFilter(request, response);
            }
            else {
                this.context.log("Unauthorized access request");
                response.sendRedirect("main.html");
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {
        this.context = config.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }
}
