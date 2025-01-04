package com.example.backend_or_lab2.api.wrappers;
import java.time.LocalDateTime;

public class ApiResponse<T> {
    private String status;
    private String message;
    private T response;
    private LocalDateTime timestamp;

    public ApiResponse(String status, String message, T response) {
        this.status = status;
        this.message = message;
        this.response = response;
        this.timestamp = LocalDateTime.now();
    }

    // Getteri i setteri
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
