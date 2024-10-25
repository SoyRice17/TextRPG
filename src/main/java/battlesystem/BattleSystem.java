package battlesystem;

import entity.Monster;
import entity.Player;
import util.InputOutputManager;

public class BattleSystem {
    private static final double RAGE_THRESHOLD = 0.3;

    public static void battle(Player player, Monster monster) {
        InputOutputManager.printMessage("\n🗡️ " + player.getName() + "님이 용감하게 " + monster.getName() + "와(과) 싸웁니다!");
        int turnCount = 0;
        int criticalHitCount = 0;

        while (player.isAlive() && monster.isAlive()) {
            turnCount++;

            // 플레이어 공격
            int playerDamage = player.attackDamage();
            if (playerDamage > player.getAttack()) {
                criticalHitCount++;
                InputOutputManager.printMessage(
                    turnCount + "턴: " + player.getName() + "님의 크리티컬 히트! " + 
                        playerDamage + "의 피해를 입혔습니다!");
            }
            monster.takeDamage(playerDamage);

            if (!monster.isAlive()) {
                InputOutputManager.printMessage(monster.getName() + "을(를) 물리쳤습니다!");
                break;
            }

            // 몬스터 공격
            int monsterDamage = monster.attackDamage();
            player.takeDamage(monsterDamage);

            if (!player.isAlive()) {
                InputOutputManager.printMessage(player.getName() + "님이 쓰러졌습니다!");
                break;
            }

            // 플레이어의 HP가 30% 이하로 떨어지면 분노 모드 발동
            if (player.getCurrentHp() < player.getMaxHp() * RAGE_THRESHOLD) {
                player.setAttackStrategy(new RageAttackStrategy());
                InputOutputManager.printMessage(player.getName() + "이(가) 분노 모드에 돌입했습니다!");
            }
        }

        // 전투가 끝나면 기본 전략으로 복귀
        player.setAttackStrategy(new NormalAttackStrategy());

        // 전투 결과 출력
        if (!monster.isAlive()) {
            InputOutputManager.printMessage("\n승리! " + turnCount + "턴 만에 " + monster.getName() + "을(를) 물리쳤습니다!");
            InputOutputManager.printMessage("크리티컬 히트 횟수: " + criticalHitCount);
            player.gainExp(monster.getExpValue());
        } else {
            InputOutputManager.printMessage("\n패배... " + turnCount + "턴 만에 " + player.getName() + "님이 쓰러졌습니다.");
            InputOutputManager.printMessage("크리티컬 히트 횟수: " + criticalHitCount);
        }
    }
}
