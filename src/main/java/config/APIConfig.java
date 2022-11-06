package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class APIConfig {

    public String url;
    public String serviceRoute;
    public String apiKey;
    public String headlinesResource;
    public String everythingResource;
    public String sourcesResource;
    Properties properties;
    private final String FILE_PATH = System.getProperty("user.dir") + File.separator + "src"
            + File.separator + "main" + File.separator + "resources" + File.separator + "config.properties";

    public APIConfig() {
        loadProperties();

        if (properties != null) {
            this.url = properties.getProperty("url");
            this.serviceRoute = properties.getProperty("service_route");
            this.apiKey = properties.getProperty("api_key");
            this.headlinesResource = properties.getProperty("headlines");
            this.everythingResource = properties.getProperty("everything");
            this.sourcesResource = properties.getProperty("sources");
        }
    }

    private void loadProperties() {
        try (FileInputStream fis = new FileInputStream(FILE_PATH)){
            properties = new Properties();
            properties.load(fis);

        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

    }

}
