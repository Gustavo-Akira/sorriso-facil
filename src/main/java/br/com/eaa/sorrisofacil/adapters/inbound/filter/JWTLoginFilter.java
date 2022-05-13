package br.com.eaa.sorrisofacil.adapters.inbound.filter;

import br.com.eaa.sorrisofacil.adapters.dto.exception.ExceptionResponseDTO;
import br.com.eaa.sorrisofacil.application.port.login.LoginServicePort;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
public class JWTLoginFilter implements Filter {
    @Autowired
    private LoginServicePort port;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if(req.getRequestURI().equals("/login") || req.getRequestURI().contains("api-docs") || req.getRequestURI().contains("swagger")){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            String authentication = req.getHeader("Authorization");
            if(authentication != null && !authentication.isEmpty()){
                if(port.validToken(authentication)){
                    if(port.getUser(authentication) != null) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    }else{
                        ((HttpServletResponse) servletResponse).setStatus(401);
                        ((HttpServletResponse) servletResponse).setContentType("application/json");
                        ExceptionResponseDTO dto = new ExceptionResponseDTO();
                        dto.setError("validation error");
                        dto.setStatus(401);
                        dto.setPath("filter");
                        servletResponse.getOutputStream().write(mapper.writeValueAsString(dto).getBytes(StandardCharsets.UTF_8));
                    }
                }else{
                    ((HttpServletResponse) servletResponse).setStatus(401);
                    ((HttpServletResponse) servletResponse).setContentType("application/json");
                    ExceptionResponseDTO dto = new ExceptionResponseDTO();
                    dto.setError("validation error");
                    dto.setStatus(401);
                    dto.setPath("filter");
                    servletResponse.getOutputStream().write(mapper.writeValueAsString(dto).getBytes(StandardCharsets.UTF_8));
                }
                return;
            }
            ((HttpServletResponse) servletResponse).setStatus(401);
            ((HttpServletResponse) servletResponse).setContentType("application/json");
            ExceptionResponseDTO dto = new ExceptionResponseDTO();
            dto.setError("validation error");
            dto.setStatus(401);
            dto.setPath("filter");
            servletResponse.getOutputStream().write(mapper.writeValueAsString(dto).getBytes(StandardCharsets.UTF_8));
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
