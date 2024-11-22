package com.example.reelquill.enums;

public enum NotificationType {
    FRIEND_REQUEST(0),
    MESSAGE(1),
    COMMENT(2);

    private final int code;

    NotificationType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static NotificationType fromCode(int code) {
        for (NotificationType type : NotificationType.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}

