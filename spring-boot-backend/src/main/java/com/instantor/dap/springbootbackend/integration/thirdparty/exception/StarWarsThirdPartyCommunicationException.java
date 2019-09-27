package com.instantor.dap.springbootbackend.integration.thirdparty.exception;

/**
 * A non-checked exception to notify errors from the integration
 * This exception is intended to be thrown when directly communicating with the third party
 */
public class StarWarsThirdPartyCommunicationException extends RuntimeException {

    private static final long serialVersionUID = 1;

    public StarWarsThirdPartyCommunicationException(String s) {
        super(s);
    }
}
