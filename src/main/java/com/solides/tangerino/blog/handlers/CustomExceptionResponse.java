package com.solides.tangerino.blog.handlers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomExceptionResponse {
    private String timestamp;
    private int status;
    private String message;

    public CustomExceptionResponse(String message, int status){
        this.timestamp = LocalDateTime.now().toString();
        this.message = message;
        this.status = status;
    }

    public String toJSON() {
        return  "{"
                +"\"timestamp\":\"" + this.timestamp + "\","
                +"\"status\":\"" + this.status + "\","
                +"\"message\":\"" + this.message + "\""
                +"}";
    }
}
