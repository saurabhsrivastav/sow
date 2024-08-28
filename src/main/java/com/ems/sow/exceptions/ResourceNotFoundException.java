package com.ems.sow.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    /**
     * Resource Not Found Exception
     *
     * @param exc
     */

    public ResourceNotFoundException(String exc) {
        super(exc);
    }

    public ResourceNotFoundException() {
        super("Resource Not Found");
    }

}
