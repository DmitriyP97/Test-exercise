package config;

import org.aeonbits.owner.ConfigCache;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;

import static com.codeborne.selenide.Configuration.*;

public class ConfigurationManager {

    private ConfigurationManager() {
    }

    public static Configuration configuration() {
        return ConfigCache.getOrCreate(Configuration.class);
    }

    public static void setDebugSettings() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", new HashMap<String, Object>() {{
            put("enableVNC", true);
            put("sessionTimeout", "10m");
        }});
        browserCapabilities = capabilities;
        pageLoadTimeout = 600_000;
        headless = false;

    }
}