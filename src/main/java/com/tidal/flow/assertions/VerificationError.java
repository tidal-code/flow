package com.tidal.flow.assertions;

public class VerificationError extends RuntimeException {
    public VerificationError(String message) {
        super(message);
    }

    public VerificationError(Throwable cause) {
        super(cause);
    }

    public VerificationError(String message, Throwable cause) {
        super(message, cause);
    }
}
