package org.jcd2052.api.services;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.jcd2052.api.config.AppConfig;
import org.jcd2052.api.config.PropertyConfig;
import org.jcd2052.api.endpoints.IEndpoint;

public abstract class BaseJsonPlaceHolderService {
    private final AppConfig appConfig = PropertyConfig.APP_CONFIG.getConfig();

    protected RequestSpecification createBaseRequestSpecification(String... paths) {
        String path = getServiceEndpoint().getEndpoint() + String.join("/", paths);
        return new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
                .setBaseUri(appConfig.baseUrl())
                .setBasePath(path)
                .setContentType(ContentType.JSON)
                .build();
    }

    protected abstract IEndpoint getServiceEndpoint();
}