package com.pmu.course.response;

public class CourseResponse {
    private boolean success;
    private String message;

    // Constructeur,


    public CourseResponse() {
    }

    public CourseResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }


    // getters et setters

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //toString
    @Override
    public String toString() {
        return "CourseResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}