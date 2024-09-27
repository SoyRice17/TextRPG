package world;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import entity.Monster;
import org.json.JSONObject;
import org.json.JSONArray;

public class GameWorld {
    private HashMap<String, Map> maps;

    public GameWorld() {
        maps = new HashMap<>();
        initializeMaps();
    }

    private void initializeMaps() {
        JSONObject mapsConfig = ConfigManager.getInstance().getMapsConfig();
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
            maps.put(mapKey, map);
        }
    }

    public List<String> getMapNames() {
        return new ArrayList<>(maps.keySet());
    }

    public Map getMap(String mapName) {
        return maps.get(mapName);
    }
}