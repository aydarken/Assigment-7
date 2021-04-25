package servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

@WebFilter(filterName = "LogFilter")
public class LogFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        Enumeration<String> params = req.getParameterNames();

        System.out.println("Logging Date: " + new Date());
        System.out.println("Servlet path: " + request.getServletPath() );
        System.out.println("Call from : " + request.getRemoteAddr());
        while(params.hasMoreElements()){
            String name = params.nextElement();
            String value = req.getParameter(name);
            System.out.println("Request Parameter: "+name+"="+value);
        }

        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                System.out.println("Cookie: "+cookie.getName()+"="+cookie.getValue());
            }
        }
        System.out.println();
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}