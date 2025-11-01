package org.example.controller;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.data.Artifact;
import org.example.data.Labels;
import org.example.service.ArtifactIdSupplier;
import org.example.service.ArtifactService;
import org.example.service.ArtifactServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ArtifactController {

    private final ArtifactIdSupplier artifactIdSupplier;
    private final ArtifactService artifactService;

    @PostMapping(value = "/upload", consumes = MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(ACCEPTED)
    public String upload(
        @RequestPart("labels") Map<String, String> labels,
        @RequestPart("file") MultipartFile file) throws IOException, ArtifactServiceException {

        var artifactId = artifactIdSupplier.get();

        artifactService.upload(Artifact.builder()
            .id(artifactId)
            .labels(Labels.builder()
                .keyValues(labels)
                .build())
            .buffer(file.getBytes())
            .build());

        return artifactId;
    }

}
