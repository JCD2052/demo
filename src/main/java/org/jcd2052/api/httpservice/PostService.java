package org.jcd2052.api.httpservice;

import io.restassured.http.Method;

class PostService extends BaseService{
    @Override
    protected Method getMethod() {
        return Method.POST;
    }
}
