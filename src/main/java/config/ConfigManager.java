package config;

import org.json.JSONObject;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ConfigManager {
    private static ConfigManager instance;
    private JSONObject config;

    private ConfigManager() {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("config/config.json");
            if (is == null) {
                throw new IllegalArgumentException("config.json not found in classpath");
            }
            String content = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            config = new JSONObject(content);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    public JSONObject getPlayerConfig() {
        return config.getJSONObject("player");
    }

    public JSONObject getTribesConfig() {
        return config.getJSONObject("tribes");
    }

    public JSONObject getMapsConfig() {
        return config.getJSONObject("maps");
    }
}
