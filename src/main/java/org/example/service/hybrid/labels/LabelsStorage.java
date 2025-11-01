package org.example.service.hybrid.labels;

import java.util.Map;
import java.util.Set;

public interface LabelsStorage extends AutoCloseable {

    @Override
    void close();

    void store(String id, Map<String, String> labels) throws LabelsStorageException;

    default Set<String> search(Map<String, String> labels) throws LabelsStorageException {
        throw new UnsupportedOperationException();
    }
}
