package bddtests.stepdefenitions;

import bddtests.utils.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.jcd2052.api.models.Photo;
import org.jcd2052.api.services.AlbumsService;
import org.jcd2052.api.utils.ObjectConvertor;
import org.testng.Assert;

import java.util.Arrays;

import static steps.TestSteps.assertContentTypes;
import static steps.TestSteps.assertStatusCodes;

public class StepDefinitions {
    private final ScenarioContext context = new ScenarioContext();
    private final AlbumsService albumsService = new AlbumsService();

    @When("I perform GET response to albums service I receive and save a {string}")
    public void performGetRequestToAlbumServiceAndSaveResponse(String contextName) {
        context.setContext(contextName, albumsService.getAllAlbums());
    }

    @Then("I check that a {string} status code is '{int}'")
    public void checkResponseStatusCode(String contextName, int expectedStatusCode) {
        Response response = context.getContext(contextName);
        assertStatusCodes(response, expectedStatusCode);
    }

    @And("I check that a {string} content type is {string}")
    public void checkResponseContentType(String contextName, String contentType) {
        Response response = context.getContext(contextName);
        assertContentTypes(response, contentType);
    }

    @And("I check that a {string} size is '{int}'")
    public void checkResponseBodySize(String contextName, int expectedBodySize) {
        Response response = context.getContext(contextName);
        int bodySize = ObjectConvertor.convertResponse(response, Object[].class).length;
        Assert.assertEquals(bodySize, expectedBodySize,
                "Body size from response doesn't match with expected.");
    }

    @When("I perform GET response to photos service with album id '{int}' I receive and save a {string}")
    public void getPhotosFromAlbumByAlbumId(int albumId, String contextName) {
        context.setContext(contextName, albumsService.getPhotosOfAlbumByAlbumId(albumId));
    }

    @And("I check that a {string} all photos objects have the same album id '{int}'")
    public void checkThatAllPhotosHaveSameAlbumId(String contextName, int albumId) {
        Response response = context.getContext(contextName);
        Assert.assertTrue(Arrays.stream(ObjectConvertor.convertResponse(response, Photo[].class))
                        .allMatch(photo -> photo.getAlbumId() == albumId),
                "Album id is not matched in all photos.");
    }
}
