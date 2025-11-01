package org.example.service.hybrid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.example.service.hybrid.file.BlobStorageImpl;
import org.example.service.hybrid.labels.LabelsStorageImpl;
import org.example.service.hybrid.metadata.MetadataStorageImpl;
import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

@RequiredArgsConstructor
public class LocalFileStoryFactoryImpl implements LocalFileStorageFactory {

    @NonNull
    private final Path root;

    @Override
    public LocalFileStorage getLocalFileStorage() throws LocalFileStorageException {
        var filesRoot = root.resolve("files");
        var rocksDbRoot = root.resolve("rocksdb");
        var luceneRoot = root.resolve("lucene_index");
        try {
            Files.createDirectories(filesRoot);
            Files.createDirectories(rocksDbRoot);
            Files.createDirectories(luceneRoot);
        } catch (IOException e) {
            throw new LocalFileStorageException("Failed to create folders", e);
        }

        RocksDB.loadLibrary();

        RocksDB rocksDB;
        try (var options = new Options().setCreateIfMissing(true)) {
            rocksDB = RocksDB.open(options, rocksDbRoot.toString());
        } catch (RocksDBException e) {
            throw new LocalFileStorageException("Failed to initialize RocksDB", e);
        }

        IndexWriter luceneIndexWriter;
        try {
            var dir = FSDirectory.open(luceneRoot);
            var config = new IndexWriterConfig(new StandardAnalyzer());
            luceneIndexWriter = new IndexWriter(dir, config);
        } catch (IOException e) {
            throw new LocalFileStorageException("Failed to initialize Lucene index", e);
        } finally {
            rocksDB.close();
        }
        return new LocalFileStorageImpl(new LabelsStorageImpl(luceneIndexWriter), new MetadataStorageImpl(), new BlobStorageImpl());
    }

    @Override
    public void close() {
        // nop
    }
}
