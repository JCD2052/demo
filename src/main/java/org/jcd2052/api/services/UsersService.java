package org.jcd2052.api.services;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.jcd2052.api.httpservice.HttpService;
import org.jcd2052.api.endpoints.IEndpoint;
import org.jcd2052.api.endpoints.JsonPlaceholderEndpoint;

public class UsersService extends BaseJsonPlaceHolderService {
    @Step("Requesting a users list.")
    public Response getAllUsers() {
        return HttpService.GET.getService().sendRequestAndRecieveResponse(
                super.createBaseRequestSpecification());
    }

    @Step("Requesting a user with id {id}")
    public Response getUserById(int id) {
        return HttpService.GET.getService().sendRequestAndRecieveResponse(
                super.createBaseRequestSpecification(String.valueOf(id)));
    }

    @Override
    protected IEndpoint getServiceEndpoint() {
        return JsonPlaceholderEndpoint.USERS;
    }
}
