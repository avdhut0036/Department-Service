package com.app.springboot.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Endpoint(id = "features")
public class FeatureEndpoint {

    private Map<String, Feature> featureMap =
            new ConcurrentHashMap<>();

    public FeatureEndpoint() {
        featureMap.put("DEPARTMENT_SERVICE",
                Feature.builder()
                .isEnabled(true).description("Department CRUD with Extra Endpoints added").build());

        featureMap.put("USER_SERVICE",
                Feature.builder()
                        .isEnabled(false)
                        .description("User realted CRUD + user fetched with department").build());

    }

    @ReadOperation
    public Map<String, Feature> allFeatures() {
        return featureMap;
    }

    @ReadOperation
    public Feature featuredDetailsByFeatureName(@Selector String featureName) {
        return featureMap.get(featureName);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    private static class Feature {
        private boolean isEnabled;

        private String description;
    }

}
