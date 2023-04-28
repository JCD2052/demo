package org.jcd2052.api.services;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.jcd2052.api.httpservice.HttpService;
import org.jcd2052.api.models.Post;
import org.jcd2052.api.endpoints.IEndpoint;
import org.jcd2052.api.endpoints.JsonPlaceholderEndpoint;

public class PostsService extends BaseJsonPlaceHolderService {
    @Step("Requesting an all posts.")
    public Response getAllPosts() {
        return HttpService.GET.getService()
                .sendRequestAndRecieveResponse(createBaseRequestSpecification());
    }

    @Step("Requesting a post with id {id}")
    public Response getPostById(int id) {
        return HttpService.GET.getService()
                .sendRequestAndRecieveResponse(createBaseRequestSpecification(String.valueOf(id)));
    }

    @Step("Requesting to create a post with data {post}")
    public Response createPost(Post post) {
        return HttpService.POST.getService()
                .sendRequestAndRecieveResponse(createBaseRequestSpecification().body(post));
    }

    @Override
    protected IEndpoint getServiceEndpoint() {
        return JsonPlaceholderEndpoint.POSTS;
    }
}
