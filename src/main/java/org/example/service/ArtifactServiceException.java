package org.example.service;

public class ArtifactServiceException extends Exception {

    public ArtifactServiceException() {
    }

    public ArtifactServiceException(String message) {
        super(message);
    }

    public ArtifactServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArtifactServiceException(Throwable cause) {
        super(cause);
    }
}
