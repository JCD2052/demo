package org.jcd2052.api.services;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.jcd2052.api.endpoints.IEndpoint;
import org.jcd2052.api.endpoints.JsonPlaceholderEndpoint;
import org.jcd2052.api.httpservice.HttpService;

public class AlbumsService extends BaseJsonPlaceHolderService {

    @Step("Requesting an albums list.")
    public Response getAllAlbums() {
        return HttpService.GET.getService().sendRequestAndRecieveResponse(
                super.createBaseRequestSpecification());
    }

    @Step("Requesting an album with id {id}.")
    public Response getPhotosOfAlbumByAlbumId(int albumId) {
        String path = String.format("/%d/photos", albumId);
        return HttpService.GET.getService().sendRequestAndRecieveResponse(
                super.createBaseRequestSpecification(path));
    }

    @Override
    protected IEndpoint getServiceEndpoint() {
        return JsonPlaceholderEndpoint.ALBUMS;
    }
}
