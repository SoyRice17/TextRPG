import org.json.JSONObject;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ConfigManager {
    private static JSONObject config;

    static {
        try {
            InputStream is = ConfigManager.class.getClassLoader().getResourceAsStream("config.json");
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

    public static JSONObject getPlayerConfig() {
        return config.getJSONObject("player");
    }

    public static JSONObject getTribesConfig() {
        return config.getJSONObject("tribes");
    }

    public static JSONObject getMapsConfig() {
        return config.getJSONObject("maps");
    }
}
