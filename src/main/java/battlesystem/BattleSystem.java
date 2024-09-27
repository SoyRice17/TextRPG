package battlesystem;

import entity.Monster;
import entity.Player;

public class BattleSystem {
    public static void battle(Player player, Monster monster) {
        System.out.println("\n🗡️ " + player.getName() + "님이 용감하게 " + monster.getName() + "와(과) 싸웁니다!");
        int turnCount = 0;
        int criticalHitCount = 0;

        while (player.isAlive() && monster.isAlive()) {
            turnCount++;

            // 플레이어 공격
            int playerDamage = player.attackDamage();
            if (playerDamage > player.getAttack()) {
                criticalHitCount++;
                System.out.println(
                    turnCount + "턴: " + player.getName() + "님의 크리티컬 히트! " + 
                        playerDamage + "의 피해를 입혔습니다!");
            }
            monster.takeDamage(playerDamage);

            if (!monster.isAlive()) {
                System.out.println(monster.getName() + "을(를) 물리쳤습니다!");
                break;
            }

            // 몬스터 공격
            int monsterDamage = monster.attackDamage();
            player.takeDamage(monsterDamage);

            if (!player.isAlive()) {
                System.out.println(player.getName() + "님이 쓰러졌습니다!");
                break;
            }
        }

        // 전투 결과 출력
        if (!monster.isAlive()) {
            System.out.println("\n승리! " + turnCount + "턴 만에 " + monster.getName() + "을(를) 물리쳤습니다!");
            System.out.println("크리티컬 히트 횟수: " + criticalHitCount);
            player.gainExp(monster.getExpValue());
        } else {
            System.out.println("\n패배... " + turnCount + "턴 만에 " + player.getName() + "님이 쓰러졌습니다.");
            System.out.println("크리티컬 히트 횟수: " + criticalHitCount);
        }
    }
}
