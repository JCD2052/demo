package org.jcd2052.api.entitygenerator;

import aquality.selenium.core.logging.Logger;
import org.jcd2052.api.config.PropertyConfig;
import org.jcd2052.api.config.TestDataConfig;
import org.jcd2052.api.models.Post;
import org.jcd2052.api.utils.RandomUtils;

public class PostGenerator {
    private static final TestDataConfig testDataConfig = PropertyConfig.TEST_DATA_CONFIG.getConfig();

    private PostGenerator() {

    }

    public static Post generatePost() {
        Post post = new Post(testDataConfig.createUserId(),
                RandomUtils.createRandomString(testDataConfig.randomStringLength()),
                RandomUtils.createRandomString(testDataConfig.randomStringLength()));
        Logger.getInstance().info("Generated post: " + post);
        return post;
    }
}