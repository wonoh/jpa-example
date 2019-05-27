package com.wonoh.spring.jpa.common;


import java.util.List;

public class ErrorResponse {

    private String message;
    private String code;
    private int status;
    private List<FieldError> errors;

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }

    public List<FieldError> getErrors() {
        return errors;
    }

    private ErrorResponse(){};

    public ErrorResponse(String message, String code, int status, List<FieldError> errors) {
        this.message = message;
        this.code = code;
        this.status = status;
        this.errors = errors;
    }

    public static class FieldError{


        private String field;
        private String value;
        private String reason;

        private FieldError(){};

        public String getField() {
            return field;
        }

        public String getValue() {
            return value;
        }

        public String getReason() {
            return reason;
        }

        public FieldError(String field, String value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }
    }

}
