package org.example.service.hybrid.file;

import java.util.Optional;

public interface BlobStorage extends AutoCloseable {

    @Override
    void close();

    default void store(String id, byte[] buffer) throws FileStorageException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    default Optional<byte[]> get(String id) throws FileStorageException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
