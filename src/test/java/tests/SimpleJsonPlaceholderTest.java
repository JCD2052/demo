package tests;

import aquality.selenium.browser.AqualityServices;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.apache.http.entity.ContentType;
import org.jcd2052.api.config.PropertyConfig;
import org.jcd2052.api.config.TestDataConfig;
import org.jcd2052.api.models.Post;
import org.jcd2052.api.models.User;
import org.jcd2052.api.services.PostsService;
import org.jcd2052.api.services.UsersService;
import org.jcd2052.api.entitygenerator.PostGenerator;
import org.jcd2052.api.utils.ObjectConvertor;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.jcd2052.api.utils.ObjectConvertor.convertResponse;
import static steps.TestSteps.assertContentTypes;
import static steps.TestSteps.assertStatusCodes;

@Feature("Simple JsonPlaceholder API Service Test")
public class SimpleJsonPlaceholderTest {
    private static final String TEST_USER_JSON = "src/test/resources/testUser.json";
    private static final String EMPTY_JSON = "{}";
    private static final User TEST_USER = ObjectConvertor.getEntityFromFile(
            new File(TEST_USER_JSON), User.class);
    private final TestDataConfig testDataConfig = PropertyConfig.TEST_DATA_CONFIG.getConfig();
    private final PostsService postsService = new PostsService();
    private final UsersService usersService = new UsersService();

    @Test(description = "Get all posts and check whether sorting is correct.")
    public void getAllPostsAndCheckSortingTest() {
        Response response = postsService.getAllPosts();
        assertStatusCodes(response, HttpURLConnection.HTTP_OK);
        assertContentTypes(response, ContentType.APPLICATION_JSON);
        List<Post> postsList = Arrays.asList(convertResponse(response, Post[].class));
        List<Post> sortedPosts = postsList.stream()
                .sorted(Comparator.comparing(Post::getId))
                .collect(Collectors.toList());
        Assert.assertEquals(postsList, sortedPosts, "Sorting is not correct.");
    }

    @Test(description = "Get a certain post and check its data.")
    public void getPostByIdTest() {
        int postNumber = testDataConfig.getPostId();
        Response response = postsService.getPostById(postNumber);
        assertStatusCodes(response, HttpURLConnection.HTTP_OK);
        assertContentTypes(response, ContentType.APPLICATION_JSON);
        Post post = convertResponse(response, Post.class);
        Assert.assertFalse(post.getBody().isEmpty(),
                "Post body is empty.");
        Assert.assertFalse(post.getTitle().isEmpty(),
                "Post title is empty.");
        Assert.assertEquals(post.getId(), postNumber,
                "Post ID doesn't match with post number.");
        int expectedId = testDataConfig.expectedUserId();
        Assert.assertEquals(post.getUserId(), expectedId,
                "Post ID doesn't match with expected ID.");
    }

    @Test(description = "Get a not exist post and check if body is empty.")
    public void getNonExistentPostTest() {
        Response response = postsService.getPostById(testDataConfig.notExistPostId());
        assertStatusCodes(response, HttpURLConnection.HTTP_NOT_FOUND);
        Assert.assertEquals(response.asString(), EMPTY_JSON,
                "Response body is not empty.");
    }

    @Test(description = "create a new post with random generated data.")
    public void createNewPostTest() {
        Post generatedPostData = PostGenerator.generatePost();
        Response response = postsService.createPost(generatedPostData);
        assertStatusCodes(response, HttpURLConnection.HTTP_CREATED);
        Post createdPostData = convertResponse(response, Post.class);
        Assert.assertFalse(createdPostData.getId() <= 0,
                "Created post id is not correct.");
        Assert.assertEquals(createdPostData.getBody(), generatedPostData.getBody(),
                "Random generated body doesn't match with received body.");
        Assert.assertEquals(createdPostData.getTitle(), generatedPostData.getTitle(),
                "Random generated title doesn't match with received title.");
        Assert.assertEquals(createdPostData.getUserId(), generatedPostData.getUserId(),
                "Random generated userID doesn't match with received userID.");
    }

    @Test(description = "Get a certain user from all users and match with an expected one.")
    public void getCertainUserFromUsersListTest() {
        int userIdToGet = testDataConfig.getUserId();
        Response response = usersService.getAllUsers();
        assertStatusCodes(response, HttpURLConnection.HTTP_OK);
        assertContentTypes(response, ContentType.APPLICATION_JSON);
        User userWithExpectedUserId = Arrays.stream(usersService.getAllUsers().as(User[].class))
                .filter(user -> user.getId() == userIdToGet)
                .findFirst()
                .orElseThrow(() -> {
                    String message = "Couldn't find user with id: " + userIdToGet;
                    AqualityServices.getLogger().error(message);
                    return new IllegalArgumentException(message);
                });
        Assert.assertEquals(userWithExpectedUserId, TEST_USER,
                "Received user data doesn't match with expected user data.");
    }

    @Test(description = "Get a certain user and match with expected one.")
    public void getUserByIdTest() {
        Response response = usersService.getUserById(testDataConfig.getUserId());
        assertStatusCodes(response, HttpURLConnection.HTTP_OK);
        Assert.assertEquals(convertResponse(response, User.class), TEST_USER,
                "Received user data doesn't match with expected user data.");
    }
}
