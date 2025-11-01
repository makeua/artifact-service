package org.example.service.hybrid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.data.Artifact;
import org.example.service.ArtifactService;
import org.example.service.ArtifactServiceException;
import org.example.service.hybrid.LocalFileStorage.StoreRequest;

@Slf4j
@RequiredArgsConstructor
public class HybridArtifactService implements ArtifactService {

    private final LocalFileStorageFactory localFileStorageFactory;

    @Override
    public void upload(Artifact artifact) throws ArtifactServiceException {
        try (var localFileStorage = localFileStorageFactory.getLocalFileStorage()) {
            localFileStorage.store(StoreRequest.builder()
                .id(artifact.getId())
                .labels(artifact.getLabels().getKeyValues())
                .buffer(artifact.getBuffer())
                .build());
        } catch (LocalFileStorageException e) {
            throw new ArtifactServiceException("Unable to store artifact", e);
        }
    }
}
