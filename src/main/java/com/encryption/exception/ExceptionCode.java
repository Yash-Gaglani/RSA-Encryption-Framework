package com.encryption.exception;

public enum ExceptionCode {

    UNKNOWN, ERROR, WARNING, UNPROCESSABLE_ENTITY, NOT_FOUND,
    INVALID_DATE, INVALID_MOBILE_NUMBER, INVALID_EMAIL_ID, INVALID_VERIFICATION_CHANNEL,

    DUPLICATE_ACCOUNT, FORBIDDEN, UNAUTHORIZED, BAD_REQUEST, INVALID_REQUEST_PARAM,

    VERIFICATION_FAILED_INVALID_CODE, ACCOUNT_NOT_FOUND, DUPLICATE_PROPOSAL_STATUS, DUPLICATE_PROPERTY, DUPLICATE_USER, DUPLICATE_BROKER, NO_FILE_CHOSEN, EMPTY_FILE, UNSUPPORTED_FILE_TYPE,
    AWS_S3_REQUEST_REJECTED, AWS_S3_CONNECTION_ERROR, INVALID_CREDENTIALS, CONFLICT;
}
