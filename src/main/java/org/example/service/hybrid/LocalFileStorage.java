package org.example.service.hybrid;

import java.util.Map;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;

public interface LocalFileStorage extends AutoCloseable {

    @Override
    void close();

    void store(StoreRequest storeRequest) throws LocalFileStorageException;

    @Getter
    @Builder
    class StoreRequest {

        @NonNull
        private final String id;
        @Singular
        private final Map<String, String> labels;
        @NonNull
        private final byte[] buffer;
    }
}
