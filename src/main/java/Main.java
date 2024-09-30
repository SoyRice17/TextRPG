import battlesystem.BattleSystem;
import config.Tribes;
import entity.Monster;
import entity.Player;
import world.GameWorld;
import world.Map;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        printBanner();
        
        System.out.println("\n🧙 캐릭터의 이름을 설정하세요:");
        String name = br.readLine();

        Tribes playerTribe = null;
        while (playerTribe == null) {
            System.out.println("\n🌍 종족을 선택하세요:");
            System.out.println("1. 인간 (Human)  2. 엘프 (Elf)  3. 드워프 (Dwarf)  4. 오크 (Orc)");
            System.out.print("선택 (1-4): ");
            String input = br.readLine().trim();

            playerTribe = switch (input) {
                case "1" -> Tribes.HUMAN;
                case "2" -> Tribes.ELF;
                case "3" -> Tribes.DWARF;
                case "4" -> Tribes.ORC;
                default -> {
                    System.out.println("\n❌ 올바르지 않은 선택입니다. 다시 선택해주세요.");
                    yield null;
                }
            };

            if (playerTribe == null) continue;
        }

        Player user = new Player(name, playerTribe);
        System.out.println("\n✨ 캐릭터 생성 완료!");
        user.showStatus();

        GameWorld gameWorld = new GameWorld();
        Map currentMap = null;

        while (true) {
            if (currentMap == null) {
                currentMap = selectMap(gameWorld);
            }

            Monster monster = currentMap.getRandomMonster();

            System.out.println("\n🗺️ " + currentMap.getName() + "에 있습니다.");
            System.out.println("👾 " + monster.getName() + "을(를) 만났습니다!");
            System.out.println("몬스터 정보: 체력 = " + monster.getMaxHp() + ", 공격력 = " + monster.getAttack());

            System.out.println("\n무엇을 하시겠습니까?");
            System.out.println("1. 싸우기  2. 도망가기  3. 스탯  4. 맵 이동");
            System.out.print("선택 (1-4): ");
            String choice = br.readLine().trim();

            switch (choice) {
                case "1" -> { // CHOICE_FIGHT
                    BattleSystem.battle(user, monster);
                    if (!user.isAlive()) {
                        System.out.println("\n게임 오버! " + user.getName() + "님이 쓰러졌습니다.");
                        return;
                    }
                }
                case "2" -> System.out.println("🏃 " + user.getName() + "님이 " + monster.getName() + "에게서 도망칩니다."); // CHOICE_RUN
                case "3" -> user.showStatus(); // CHOICE_SHOW_STATUS
                case "4" -> currentMap = selectMap(gameWorld); // CHOICE_CHANGE_MAP
                default -> System.out.println("\n❌ 올바르지 않은 선택입니다. 다시 선택해주세요.");
            }

            if (!user.isAlive()) {
                System.out.println("\n게임 오버! " + user.getName() + "님이 쓰러졌습니다.");
                return;
            }
        }
    }

    private static Map selectMap(GameWorld gameWorld) throws Exception {
        while (true) {
            System.out.println("\n🗺️ 어느 맵으로 가시겠습니까?");
            List<String> mapNames = gameWorld.getMapNames();
            for (int i = 0; i < mapNames.size(); i++) {
                System.out.println((i + 1) + ". " + mapNames.get(i));
            }
            System.out.print("선택 (1-" + mapNames.size() + "): ");
            String mapChoice = br.readLine().trim();
            
            try {
                int mapIndex = Integer.parseInt(mapChoice) - 1;
                if (mapIndex >= 0 && mapIndex < mapNames.size()) {
                    return gameWorld.getMap(mapNames.get(mapIndex));
                } else {
                    System.out.println("\n❌ 올바르지 않은 선택입니다. 다시 선택해주세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n❌ 올바르지 않은 입력입니다. 숫자를 입력해주세요.");
            }
        }
    }

    private static void printBanner() {
        System.out.println(
            " _    _        _                               _ \n" +
            "| |  | |      | |                             | |\n" +
            "| |  | |  ___ | |  ___   ___  _ __ ___    ___ | |\n" +
            "| |/\\| | / _ \\| | / __| / _ \\| '_ ` _ \\  / _ \\| |\n" +
            "\\  /\\  /|  __/| || (__ | (_)|| | | | | ||  __/|_|\n" +
            " \\/  \\/  \\___||_| \\___| \\___/|_| |_| |_| \\___|(_)\n" +
            "                                                  "
        );
    }
}
