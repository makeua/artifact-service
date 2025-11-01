package org.example.service.hybrid;

import static org.apache.commons.io.IOUtils.closeQuietly;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.example.service.hybrid.file.BlobStorage;
import org.example.service.hybrid.file.FileStorageException;
import org.example.service.hybrid.labels.LabelsStorage;
import org.example.service.hybrid.labels.LabelsStorageException;
import org.example.service.hybrid.metadata.MetadataStorage;
import org.example.service.hybrid.metadata.MetadataStorageException;

@RequiredArgsConstructor
class LocalFileStorageImpl implements LocalFileStorage, AutoCloseable {

    @NonNull
    private final LabelsStorage labelsStorage;
    @NonNull
    private final MetadataStorage metadataStorage;
    @NonNull
    private final BlobStorage blobStorage;

    @Override
    public void store(StoreRequest storeRequest) {
        try {
            var id = storeRequest.getId();
            var labels = storeRequest.getLabels();
            var buffer = storeRequest.getBuffer();

            labelsStorage.store(id, labels);
//            metadataStorage.store(id, null);
//            blobStorage.store(id, buffer);
        } catch (LabelsStorageException e) {

        } catch (MetadataStorageException e) {

        } catch (FileStorageException e) {

        }
    }

    @Override
    public void close() {
        closeQuietly(
            labelsStorage::close,
            metadataStorage::close,
            blobStorage::close);
    }
}
