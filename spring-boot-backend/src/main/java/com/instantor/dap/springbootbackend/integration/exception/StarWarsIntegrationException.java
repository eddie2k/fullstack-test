package com.instantor.dap.springbootbackend.integration.exception;

import com.instantor.dap.springbootbackend.integration.thirdparty.exception.StarWarsThirdPartyCommunicationException;

/**
 * A non-checked exception to notify errors from the integration.
 * This exception is intended to be used at the application level, and NOT from a low-level 3rd party implementation
 */
public class StarWarsIntegrationException extends RuntimeException {

    private static final long serialVersionUID = 1;

    public StarWarsIntegrationException(StarWarsThirdPartyCommunicationException e) {
        super(e);
    }
}
