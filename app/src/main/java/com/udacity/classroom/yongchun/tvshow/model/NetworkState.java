package com.udacity.classroom.yongchun.tvshow.model;

public class NetworkState {

    public enum Status {
        RUNNING,
        SUCCESS,
        FAILED
    }

    private final Status status;
    private final String message;

    public static final NetworkState LOADED =
            new NetworkState(Status.SUCCESS, "Success");
    public static final NetworkState LOADING =
            new NetworkState(Status.RUNNING, "Running");

    public NetworkState(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
