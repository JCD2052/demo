package org.jcd2052.api.endpoints;

public enum JsonPlaceholderEndpoint implements IEndpoint {
    POSTS("/posts/"),
    USERS("/users/"),
    ALBUMS("/albums");

    private final String endpoint;

    JsonPlaceholderEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public String getEndpoint() {
        return endpoint;
    }
}