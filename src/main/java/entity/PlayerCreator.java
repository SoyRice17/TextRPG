package entity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import config.Tribes;

public class PlayerCreator {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static Player createPlayer() throws Exception {
        System.out.println("\n🧙 캐릭터의 이름을 설정하세요:");
        String name = br.readLine();
        Tribes playerTribe = selectTribe();
        Player user = new Player(name, playerTribe);
        System.out.println("\n✨ 캐릭터 생성 완료!");
        user.showStatus();

        return user;
    }
    public static Tribes selectTribe() throws Exception {
        Tribes playerTribe = null;
        while (playerTribe == null) {
            System.out.println("\n🌍 종족을 선택하세요:");
            System.out.println("1. 인간 (Human)  2. 엘프 (Elf)  3. 드워프 (Dwarf)  4. 오크 (Orc)");
            System.out.print("선택 (1-4): ");
            String playerTribeInput = br.readLine().trim();
            
            playerTribe = switch (playerTribeInput) {
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
        return playerTribe;
    }
}
