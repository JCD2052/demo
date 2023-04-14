package steps;

import aquality.selenium.browser.AqualityServices;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.http.entity.ContentType;
import org.testng.Assert;

public class TestSteps {

    @Step("Check status code from response. Expected to receive {expectedStatusCode}")
    public static void assertStatusCodes(Response response, int expectedStatusCode) {
        int statusCodeFromResponse = response.getStatusCode();
        AqualityServices.getLogger().info("Status code from response: " + statusCodeFromResponse);
        Assert.assertEquals(statusCodeFromResponse, expectedStatusCode,
                "Status codes are not matched");
    }

    @Step("Check content type from response. Expected to receive {expectedContentType}")
    public static void assertContentTypes(Response response, String expectedContentType) {
        String contentTypeFromResponse = response.getContentType();
        AqualityServices.getLogger().info("ContentType from response: " + contentTypeFromResponse);
        Assert.assertEquals(expectedContentType.toLowerCase(), contentTypeFromResponse.toLowerCase(),
                "Content type is not correct.");
    }

    @Step("Check content type from response. Expected to receive {expectedContentType}")
    public static void assertContentTypes(Response response, ContentType expectedContentType) {
        assertContentTypes(response, expectedContentType.toString());
    }
}
