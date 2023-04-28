package org.jcd2052.api.config;

import org.aeonbits.owner.Config;

@Config.Sources({"file:src/main/resources/appconfig.properties"})
@Config.LoadPolicy(Config.LoadType.MERGE)
public interface AppConfig extends Config {
    @Key("baseUrl")
    String baseUrl();
}