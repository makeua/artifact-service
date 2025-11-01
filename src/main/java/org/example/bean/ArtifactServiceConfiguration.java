package org.example.bean;

import java.util.UUID;
import org.example.service.ArtifactIdSupplier;
import org.example.service.ArtifactService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArtifactServiceConfiguration {

    @Bean
    public ArtifactService artifactService(
        @Qualifier("artifactServiceImpl") ArtifactService artifactService) {

        return artifactService;
    }

    @Bean
    public ArtifactIdSupplier artifactIdSupplier() {
        return () -> UUID.randomUUID().toString();
    }
}
