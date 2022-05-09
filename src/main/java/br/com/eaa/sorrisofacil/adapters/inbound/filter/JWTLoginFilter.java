package br.com.eaa.sorrisofacil.adapters.inbound.filter;

import br.com.eaa.sorrisofacil.application.port.login.LoginServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JWTLoginFilter implements Filter {
    @Autowired
    private LoginServicePort port;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if(req.getRequestURI().equals("/login")){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            String authentication = req.getHeader("Authorization");
            if(authentication != null && !authentication.isEmpty()){
                if(port.validToken(authentication)){
                    if(port.getUser(authentication) != null) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    }else{
                        ((HttpServletResponse) servletResponse).setStatus(401);
                        ((HttpServletResponse) servletResponse).setContentType("application/json");
                        servletResponse.getOutputStream().write("{\"error\": \"Validation Error\"}".getBytes());
                    }
                }else{
                    ((HttpServletResponse) servletResponse).setStatus(401);
                    ((HttpServletResponse) servletResponse).setContentType("application/json");
                    servletResponse.getOutputStream().write("{\"error\": \"Validation Error\"}".getBytes());
                }
                return;
            }
            ((HttpServletResponse) servletResponse).setStatus(401);
            ((HttpServletResponse) servletResponse).setContentType("application/json");
            servletResponse.getOutputStream().write("{\"error\": \"Unauthorized\"}".getBytes());
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
