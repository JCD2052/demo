package org.jcd2052.api.utils;

import aquality.selenium.core.logging.Logger;
import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtils {
    private RandomUtils() {

    }

    public static String createRandomString(int length) {
        String randomString = RandomStringUtils.random(length, true, true);
        Logger.getInstance().info("Generated random string: " + randomString);
        return randomString;
    }
}
