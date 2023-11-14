package com.encryption.exception;

import java.io.Serial;

public class EncryptionException extends Exception{
    private ExceptionCode appErrorCode;

    @Serial
    private static final long serialVersionUID = 1L;

    public EncryptionException(String message) {
        super(message);
        this.appErrorCode = ExceptionCode.UNKNOWN;
    }

    public EncryptionException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public EncryptionException(ExceptionCode appErrorCode, String message) {
        super(message);
        this.appErrorCode = appErrorCode;
    }

    public EncryptionException(ExceptionCode appErrorCode, String message, Throwable throwable) {
        super(message, throwable);
        this.appErrorCode = appErrorCode;
    }

    public ExceptionCode getAppErrorCode() {
        return appErrorCode;
    }

}
