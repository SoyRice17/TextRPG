import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

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

            switch (input) {
                case "1": playerTribe = Tribes.HUMAN; break;
                case "2": playerTribe = Tribes.ELF; break;
                case "3": playerTribe = Tribes.DWARF; break;
                case "4": playerTribe = Tribes.ORC; break;
                default:
                    System.out.println("\n❌ 올바르지 않은 선택입니다. 다시 선택해주세요.");
            }
        }

        Player user = new Player(name, playerTribe);
        System.out.println("\n✨ 캐릭터 생성 완료!");
        user.showStatus();

        GameWorld gameWorld = new GameWorld();

        while (true) {
            Map currentMap = gameWorld.getRandomMap();
            Monster monster = currentMap.getRandomMonster();

            System.out.println("\n🗺️ " + currentMap.getName() + "에 들어왔습니다.");
            System.out.println("👾 " + monster.getName() + "을(를) 만났습니다!");
            System.out.println("몬스터 정보: 체력 = " + monster.getMaxHp() + ", 공격력 = " + monster.getAttack());

            System.out.println("\n무엇을 하시겠습니까?");
            System.out.println("1. 싸우기  2. 도망가기  3. 게임 종료");
            System.out.print("선택 (1-3): ");
            String choice = br.readLine().trim();

            switch (choice) {
                case "1":
                    System.out.println("🗡️ " + user.getName() + "님이 용감하게 " + monster.getName() + "와(과) 싸웁니다!");
                    while (user.isAlive() && monster.isAlive()) {
                        // 플레이어 공격
                        int playerDamage = user.getAttack();
                        monster.takeDamage(playerDamage);
                        //System.out.println(user.getName() + "님이 " + playerDamage + "의 피해를 입혔습니다.");
                        
                        if (!monster.isAlive()) {
                            System.out.println(monster.getName() + "을(를) 물리쳤습니다!");
                            user.gainExp(monster.getExpValue());
                            break;
                        }
                        
                        // 몬스터 공격
                        int monsterDamage = monster.getAttack();
                        user.takeDamage(monsterDamage);
                        //System.out.println(monster.getName() + "이(가) " + monsterDamage + "의 피해를 입혔습니다.");
                        
                        if (!user.isAlive()) {
                            System.out.println(user.getName() + "님이 쓰러졌습니다. 게임 오버!");
                            return;
                        }
                        
                        // 현재 상태 출력
                        System.out.println("\n현재 상태:");
                        System.out.println(user.getName() + ": HP " + user.getCurrentHp() + "/" + user.getMaxHp());
                        System.out.println(monster.getName() + ": HP " + monster.getCurrentHp() + "/" + monster.getMaxHp());
                    }
                    break;
                case "2":
                    System.out.println("🏃 " + user.getName() + "님이 " + monster.getName() + "에게서 도망칩니다.");
                    break;
                case "3":
                    System.out.println("🎮 " + user.getName() + "님, 게임을 즐겨주셔서 감사합니다!");
                    return;
                default:
                    System.out.println("\n❌ 올바르지 않은 선택입니다. 다시 선택해주세요.");
            }
        }
    }

    private static void printBanner() {
        System.out.println("================================");
        System.out.println("       환영합니다, 모험가여!      ");
        System.out.println("    당신의 영웅을 만들어보세요    ");
        System.out.println("================================");
    }
}
