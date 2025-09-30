package com.bytemage4.notification.exception;

import java.time.Instant;

public record ApiError(Instant timestamp, String message, String path) {
}
