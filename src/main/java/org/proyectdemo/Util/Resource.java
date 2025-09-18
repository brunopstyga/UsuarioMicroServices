package org.proyectdemo.Util;

public class Resource<T> {
    private Status status;
    private T data;
    private String message;

    public enum Status {
        SUCCESS, ERROR, LOADING
    }

    private Resource(Status status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String message, T data) {
        return new Resource<>(Status.ERROR, data, message);
    }

    public static <T> Resource<T> loading(T data) {
        return new Resource<>(Status.LOADING, data, null);
    }

    // Getters
    public Status getStatus() {
        return status;
    }
    public T getData() {
        return data;
    }
    public String getMessage() {
        return message;
    }

}


