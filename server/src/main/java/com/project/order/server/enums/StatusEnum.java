package com.project.order.server.enums;

public enum StatusEnum {
    NORMAL(1), INVALID(0);

    private int status;

    StatusEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
