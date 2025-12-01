package com.musicstore.entity;

public enum Role {
    ADMINISTRATOR("Administrator"),
    SALES_MANAGER("Sales Manager"),
    EDITOR("Editor"),
    ASSISTANT("Assistant"),
    SHIPPING_MANAGER("Shipping Manager");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}