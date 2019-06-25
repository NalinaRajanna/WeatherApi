package com.example.weatherapp.exception;

import java.util.Optional;

public class RestClientApplicationException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Optional<String> responseBody;

    public RestClientApplicationException(String message, Throwable cause) {
        super(message, cause);
        responseBody = Optional.ofNullable(null);
    }

    public RestClientApplicationException(String message, 
            Optional<String> responseBody, Throwable throwable) {
        super(message, throwable);
        this.responseBody = responseBody;
    }


    public Optional<String> getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(Optional<String> responseBody) {
        this.responseBody = responseBody;
    }

}
