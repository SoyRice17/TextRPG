package config;
import java.util.List;
import util.InputOutputManager;

public enum Jobs {
    BEGINNER, WARRIOR, ARCHER, MAGE, PALADIN, DRUID, BERSERKER, HAMMERER;

    private static List<Jobs> getAvailableJobs (Tribes tribe) {
        return switch (tribe) {
            case HUMAN -> List.of(WARRIOR, ARCHER, MAGE, PALADIN);
            case ELF -> List.of(WARRIOR, ARCHER, MAGE, DRUID);
            case DWARF -> List.of(WARRIOR, ARCHER, MAGE, HAMMERER);
            case ORC -> List.of(WARRIOR, ARCHER, MAGE, HAMMERER);
        };
    }

    public static Jobs selectJob(Tribes tribe) {
        List<Jobs> availableJobs = getAvailableJobs(tribe);
        while (true) {
            InputOutputManager.printMessage("선택할 수 있는 직업: " + availableJobs.toString());
            try {
                String choice = InputOutputManager.getUserInput("직업 번호를 선택하세요: ");
                int index = Integer.parseInt(choice) - 1;
                if (index >= 0 && index < availableJobs.size()) {
                    return availableJobs.get(index);
                } else {
                    InputOutputManager.printMessage("잘못된 번호입니다. 1부터 " + availableJobs.size() + " 사이의 숫자를 입력해주세요.");
                }
            } catch (NumberFormatException e) {
                InputOutputManager.printMessage("잘못된 입력입니다. 숫자를 입력해주세요.");
            } catch (Exception e) {
                InputOutputManager.printMessage("오류가 발생했습니다: " + e.getMessage());
            }
        }
    }

    public String getName() {
        return ConfigManager.getInstance().getJobsConfig().getJSONObject(this.name()).getString("name");
    }

    public int getHpRatio() {
        return ConfigManager.getInstance().getJobsConfig().getJSONObject(this.name()).getInt("hpRatio");
    }
}
