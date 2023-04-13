package org.jcd2052.api.httpservice;

import io.restassured.http.Method;

class GetService extends BaseService {
    @Override
    protected Method getMethod() {
        return Method.GET;
    }
}
