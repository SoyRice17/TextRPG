import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;

public class GameWorld {
    private List<Map> maps;

    public GameWorld() {
        maps = new ArrayList<>();
        initializeMaps();
    }

    private void initializeMaps() {
        JSONObject mapsConfig = ConfigManager.getMapsConfig();
        for (String mapKey : mapsConfig.keySet()) {
            JSONObject mapConfig = mapsConfig.getJSONObject(mapKey);
            Map map = new Map(mapConfig.getString("name"));
            
            JSONArray monsters = mapConfig.getJSONArray("monsters");
            for (int i = 0; i < monsters.length(); i++) {
                JSONObject monsterConfig = monsters.getJSONObject(i);
                Monster monster = new Monster(
                    monsterConfig.getString("name"),
                    monsterConfig.getInt("hp"),
                    monsterConfig.getInt("attack"),
                    monsterConfig.getInt("expValue")
                );
                map.addMonsterSpawn(monster, monsterConfig.getDouble("spawnWeight"));
            }
            maps.add(map);
        }
    }

    public Map getRandomMap() {
        return maps.get((int) (Math.random() * maps.size()));
    }
}