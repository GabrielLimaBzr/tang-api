package com.solides.tangerino.blog.model.enums;

public enum PostStatus {

    DRAFT("draft"),

    PUBLISHED("published");

    private String status;

    PostStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
