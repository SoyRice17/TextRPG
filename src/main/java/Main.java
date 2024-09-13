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
    }



    
    private static void printBanner() {
        System.out.println("================================");
        System.out.println("       환영합니다, 모험가여!      ");
        System.out.println("    당신의 영웅을 만들어보세요    ");
        System.out.println("================================");
    }
}
