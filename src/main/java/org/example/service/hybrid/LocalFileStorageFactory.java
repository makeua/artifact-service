package org.example.service.hybrid;

public interface LocalFileStorageFactory extends AutoCloseable {

    LocalFileStorage getLocalFileStorage() throws LocalFileStorageException;

    @Override
    void close();
}
