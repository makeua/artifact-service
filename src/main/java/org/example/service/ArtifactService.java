package org.example.service;

import java.util.Optional;
import java.util.Set;
import org.example.data.Artifact;
import org.example.data.Labels;

public interface ArtifactService {

    void upload(Artifact artifact) throws ArtifactServiceException;

    default Set<String> search(Labels labels) throws ArtifactServiceException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    default Optional<Artifact> get(String id) throws ArtifactServiceException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
