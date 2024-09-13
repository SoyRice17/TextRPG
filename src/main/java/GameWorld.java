
import java.util.ArrayList;
import java.util.List;

public class GameWorld {
    private List<Map> maps;

    public GameWorld() {
        maps = new ArrayList<>();
        initializeMaps();
    }

    private void initializeMaps() {
        Map forest = new Map("숲");
        forest.addMonsterSpawn(new Monster("늑대", 50, 10), 0.5);
        forest.addMonsterSpawn(new Monster("고블린", 30, 5), 0.3);
        forest.addMonsterSpawn(new Monster("트롤", 100, 20), 0.2);
        maps.add(forest);

        Map cave = new Map("동굴");
        cave.addMonsterSpawn(new Monster("박쥐", 20, 5), 0.4);
        cave.addMonsterSpawn(new Monster("거미", 40, 8), 0.4);
        cave.addMonsterSpawn(new Monster("동굴 트롤", 120, 25), 0.2);
        maps.add(cave);

        Map mountain = new Map("산");
        mountain.addMonsterSpawn(new Monster("독수리", 60, 15), 0.3);
        mountain.addMonsterSpawn(new Monster("산양", 40, 10), 0.4);
        mountain.addMonsterSpawn(new Monster("용", 200, 50), 0.1);
        maps.add(mountain);
    }

    public Map getRandomMap() {
        return maps.get((int) (Math.random() * maps.size()));
    }
}