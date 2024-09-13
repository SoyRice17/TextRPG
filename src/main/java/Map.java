import java.util.ArrayList;
import java.util.List;

public class Map {
    private String name;
    private List<MonsterSpawn> monsterSpawns;

    public Map(String name) {
        this.name = name;
        this.monsterSpawns = new ArrayList<>();
    }

    public void addMonsterSpawn(Monster monster, double weight) {
        monsterSpawns.add(new MonsterSpawn(monster, weight));
    }

    public Monster getRandomMonster() {
        double totalWeight = monsterSpawns.stream().mapToDouble(ms -> ms.weight).sum();
        double random = Math.random() * totalWeight;
        double cumulativeWeight = 0;
        for (MonsterSpawn ms : monsterSpawns) {
            cumulativeWeight += ms.weight;
            if (random < cumulativeWeight) {
                return ms.monster;
            }
        }
        return null; // 이 경우는 발생하지 않아야 합니다.
    }

    public String getName() {
        return this.name;
    }

    private static class MonsterSpawn {
        Monster monster;
        double weight;

        MonsterSpawn(Monster monster, double weight) {
            this.monster = monster;
            this.weight = weight;
        }
    }
}
