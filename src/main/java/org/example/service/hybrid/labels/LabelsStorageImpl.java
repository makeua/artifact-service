package org.example.service.hybrid.labels;

import static org.apache.commons.io.IOUtils.closeQuietly;
import static org.apache.lucene.document.Field.Store.NO;
import static org.apache.lucene.document.Field.Store.YES;

import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;

@RequiredArgsConstructor
public class LabelsStorageImpl implements LabelsStorage {

    private final IndexWriter indexWriter;

    @Override
    public void close() {
        closeQuietly(indexWriter);
    }

    @Override
    public void store(String id, Map<String, String> labels) throws LabelsStorageException {
        try {
            Document doc = new Document();
            doc.add(new StringField("id:", id, YES));
            for (var label : labels.entrySet()) {
                var key = label.getKey();
                var value = label.getValue();
                doc.add(new StringField(key, value, NO));
            }
            indexWriter.addDocument(doc);
            indexWriter.commit();
        } catch (IOException e) {
            throw new LabelsStorageException("Failed to store labels", e);
        }
    }
}
