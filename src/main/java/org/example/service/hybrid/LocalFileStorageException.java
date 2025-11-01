package org.example.service.hybrid;

public class LocalFileStorageException extends Exception {

    public LocalFileStorageException() {
    }

    public LocalFileStorageException(String message) {
        super(message);
    }

    public LocalFileStorageException(String message, Throwable cause) {
        super(message, cause);
    }

    public LocalFileStorageException(Throwable cause) {
        super(cause);
    }
}
