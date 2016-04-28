package com.dk.digitalstore.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class DigitalStoreException extends WebApplicationException {

    private ErrorCode errorCode;

    public DigitalStoreException(ErrorCode errorCode, String msg) {
        super(Response
                        .status(Response.Status.BAD_REQUEST)
                        .header("Pragma", "no-cache, no-store")
                        .header("Cache-Control", "no-cache, no-store")
                        .header("Expires", "0")
                        .entity(msg)
                        .build()
        );
        this.errorCode = errorCode;
    }
}
