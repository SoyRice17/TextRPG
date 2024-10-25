package config;

import org.json.JSONObject;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ConfigManager {
    private static ConfigManager instance;
    private JSONObject playerConfig;
    private JSONObject tribesConfig;
    private JSONObject mapsConfig;
    private JSONObject jobsConfig;
    private ConfigManager() {
        playerConfig = loadConfig("player.json");
        tribesConfig = loadConfig("tribes.json");
        mapsConfig = loadConfig("maps.json");
        jobsConfig = loadConfig("jobs.json");
    }

    private JSONObject loadConfig(String fileName) {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("config/" + fileName);
            if (is == null) {
                throw new IllegalArgumentException(fileName + " not found in classpath");
            }
            String content = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            return new JSONObject(content);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }


    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    public JSONObject getPlayerConfig() {
        return playerConfig;
    }

    public JSONObject getTribesConfig() {
        return tribesConfig;
    }

    public JSONObject getMapsConfig() {
        return mapsConfig;
    }

    public JSONObject getJobsConfig() {
        return jobsConfig;
    }
}
