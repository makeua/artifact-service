package org.example.service.hybrid.metadata;

import java.util.UUID;

public interface MetadataStorage extends AutoCloseable {

    @Override
    void close();

    default void store(String id, byte[] meta) throws MetadataStorageException {
        throw new UnsupportedOperationException();
    }

    default byte[] get(UUID id) throws MetadataStorageException {
        throw new UnsupportedOperationException();
    }
}
