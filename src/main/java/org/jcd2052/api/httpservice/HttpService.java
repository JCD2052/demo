package org.jcd2052.api.httpservice;

public enum HttpService {
    GET(new GetService()),
    POST(new PostService());

    private final BaseService service;

    HttpService(BaseService service) {
        this.service = service;
    }

    public BaseService getService() {
        return service;
    }
}
