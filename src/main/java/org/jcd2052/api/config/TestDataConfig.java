package org.jcd2052.api.config;

import org.aeonbits.owner.Config;

@Config.Sources({"file:src/test/resources/testdata.properties"})
@Config.LoadPolicy(Config.LoadType.MERGE)
public interface TestDataConfig extends Config {
    @Key("notExistPostId")
    int notExistPostId();

    @Key("getUserId")
    int getUserId();

    @Key("createUserId")
    int createUserId();

    @Key("randomStringLength")
    int randomStringLength();

    @Key("getPostId")
    int getPostId();

    @Key("expectedUserId")
    int expectedUserId();
}