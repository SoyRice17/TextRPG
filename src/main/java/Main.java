import battlesystem.BattleSystem;
import config.Tribes;
import entity.Monster;
import entity.Player;
import world.GameWorld;
import world.Map;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
/*
1. 싱글톤 패턴 적용: 해결
ConfigManager와 world.GameWorld 클래스에 싱글톤 패턴을 적용하면 좋겠어요. 이렇게 하면 불필요한 객체 생성을 막을 수 있죠.
예외 처리 개선:
현재는 예외가 발생하면 그냥 프로그램이 종료돼요. 사용자에게 적절한 메시지를 보여주고 게임을 계속할 수 있게 하는 게 어떨까요?
인터페이스 도입: 해결
Monster와 entity.Player 클래스에 공통적인 메서드가 있어요. Character라는 인터페이스를 만들어 이를 구현하게 하면 코드 재사용성이 높아질 거예요.
전투 로직 분리: 해결
현재 Main 클래스에 있는 전투 로직을 별도의 battlesystem.BattleSystem 클래스로 분리하면 좋겠어요. 이렇게 하면 코드 가독성이 높아지고 유지보수가 쉬워질 거예요.
로깅 시스템 도입: 미룸
System.out.println() 대신 로깅 라이브러리를 사용하면 좋겠어요. 이렇게 하면 디버깅이 쉬워지고, 나중에 로그 레벨을 조정하기 쉬워져요.
상수 사용:
매직 넘버(예: 100, 1.5 등)를 상수로 정의하면 좋겠어요. 이렇게 하면 코드의 의미가 더 명확해지고 유지보수가 쉬워져요.
테스트 코드 작성:
단위 테스트를 작성하면 좋겠어요. 이렇게 하면 버그를 빨리 잡을 수 있고, 코드 변경 시 안정성을 보장할 수 있어요.
국제화(i18n) 지원:
현재는 한국어로만 되어 있는데, 다국어 지원을 위한 구조를 만들면 좋겠어요.
디자인 패턴 적용:
팩토리 패턴이나 전략 패턴 등을 적용하면 코드의 유연성이 높아질 거예요.
성능 최적화:
현재는 큰 문제가 없지만, 게임이 커지면 성능 최적화가 필요할 수 있어요. 프로파일링을 통해 병목 지점을 찾아 개선하면 좋겠어요.
 */
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
