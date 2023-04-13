package org.jcd2052.api.httpservice;

import aquality.selenium.core.logging.Logger;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public abstract class BaseService {
    public Response sendRequestAndRecieveResponse(RequestSpecification requestSpecification) {
        QueryableRequestSpecification requestInfo = SpecificationQuerier.query(requestSpecification);
        String message = "Performing a %s request to %s. Headers: %s.";
        Logger.getInstance().info(String.format(message, getMethod().toString(),
                requestInfo.getURI(), requestInfo.getHeaders().toString()));
        long startTime = System.nanoTime();
        Response response = given(requestSpecification).request(getMethod());
        long endTime = System.nanoTime();
        double timeResult = (double) TimeUnit.NANOSECONDS.toMillis(endTime - startTime) / 1000;
        Logger.getInstance().info(String.format("Request has been performed for %f seconds.",
                timeResult));
        return response;
    }

    protected abstract Method getMethod();
}
