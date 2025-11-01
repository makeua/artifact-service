package org.example.service.hybrid.labels;

public class LabelsStorageException extends RuntimeException {

    public LabelsStorageException() {
    }

    public LabelsStorageException(String message) {
        super(message);
    }

    public LabelsStorageException(String message, Throwable cause) {
        super(message, cause);
    }

    public LabelsStorageException(Throwable cause) {
        super(cause);
    }
}
