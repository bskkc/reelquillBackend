package com.example.reelquill.enums;

public enum NotificationStatus {
    PENDING(0),   // 0 for Pending
    READ(1),      // 1 for Read
    ARCHIVED(2);  // 2 for Archived

    private final int code;

    NotificationStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static NotificationStatus fromCode(int code) {
        for (NotificationStatus status : NotificationStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}



