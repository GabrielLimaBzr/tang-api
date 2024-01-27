package com.solides.tangerino.blog.config.security.exception;

import com.solides.tangerino.blog.handlers.CustomExceptionResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ResponseErrorService {

    public void createResponse(HttpServletResponse response, int status, String message) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(status);
        response.getWriter().print(new CustomExceptionResponse(message, status).toJSON());
    }
}
