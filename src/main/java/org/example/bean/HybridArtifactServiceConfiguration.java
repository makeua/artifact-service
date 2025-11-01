package org.example.bean;

import java.nio.file.Path;
import org.example.service.ArtifactService;
import org.example.service.hybrid.HybridArtifactService;
import org.example.service.hybrid.LocalFileStorageFactory;
import org.example.service.hybrid.LocalFileStoryFactoryImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(prefix = "config", name = "mode", havingValue = "hybrid", matchIfMissing = true)
@Configuration
public class HybridArtifactServiceConfiguration {

    @Bean("artifactServiceImpl")
    public ArtifactService artifactServiceImpl(
        LocalFileStorageFactory localFileStorageFactory) {

        return new HybridArtifactService(localFileStorageFactory);
    }

    @Bean
    public LocalFileStorageFactory localFileStorageFactory(
        @Value("${config.hybrid.root-dir:./data}") Path root) {

        return new LocalFileStoryFactoryImpl(root);
    }
}
