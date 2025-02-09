package com.thebest.thebestpc.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiMessage {
    int code;
    String message;
    final LocalDateTime timestamp = LocalDateTime.now();

    public ApiMessage(int code) {
        this.code = code;
    }

    public ApiMessage(String message) {
        this.message = message;
    }

    public ApiMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
