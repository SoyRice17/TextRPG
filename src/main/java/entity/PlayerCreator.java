package entity;

import config.Tribes;
import util.InputOutputManager;

public class PlayerCreator {
    public static Player createPlayer() throws Exception {
        String name = InputOutputManager.getUserInput("\n🧙 캐릭터의 이름을 설정하세요:");
        Tribes playerTribe = selectTribe();
        Player user = new Player(name, playerTribe);
        InputOutputManager.printMessage("\n✨ 캐릭터 생성 완료!");
        user.showStatus();

        return user;
    }
    public static Tribes selectTribe() throws Exception {
        Tribes playerTribe = null;
        while (playerTribe == null) {
            InputOutputManager.printMessage("\n🌍 종족을 선택하세요:");
            InputOutputManager.printMessage("1. 인간 (Human)  2. 엘프 (Elf)  3. 드워프 (Dwarf)  4. 오크 (Orc)");
            String playerTribeInput = InputOutputManager.getUserInput("선택 (1-4): ");
            
            playerTribe = switch (playerTribeInput) {
                case "1" -> Tribes.HUMAN;
                case "2" -> Tribes.ELF;
                case "3" -> Tribes.DWARF;
                case "4" -> Tribes.ORC;
                default -> {
                    InputOutputManager.printMessage("\n❌ 올바르지 않은 선택입니다. 다시 선택해주세요.");
                    yield null;
                }
            };
    
            if (playerTribe == null) continue;
        }
        return playerTribe;
    }
}
