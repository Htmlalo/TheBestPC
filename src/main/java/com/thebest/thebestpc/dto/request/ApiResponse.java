package com.thebest.thebestpc.dto.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiResponse<T> {

    ApiMessage status;
    T data;


    public static <T> ApiResponse<T> ok(T data) {
        final ApiMessage status = new ApiMessage(HttpStatus.OK.value());
        return ApiResponse.<T>builder()
                .status(status)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> fail() {
        final ApiMessage status = new ApiMessage(HttpStatus.BAD_REQUEST.value());
        return ApiResponse.<T>builder()
                .status(status)
                .build();
    }

    public static <T> ApiResponse<T> fail(int code, String message) {
        final ApiMessage status = new ApiMessage(code, message);
        return ApiResponse.<T>builder()
                .status(status)
                .build();
    }

}
