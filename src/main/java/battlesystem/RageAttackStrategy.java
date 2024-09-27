package battlesystem;

import java.util.Random;
import entity.Entity;

public class RageAttackStrategy implements AttackStrategy {
    private static final Random random = new Random();

    @Override
    public int calculateDamage(Entity attacker) {
        double damage = attacker.getAttack() * 1.5; // 50% 증가된 기본 공격력
        boolean isCritical = random.nextDouble() < (attacker.getCriticalChance() * 1.2); // 20% 증가된 크리티컬 확률
        if (isCritical) {
            damage *= (attacker.getCriticalDamage() * 1.2); // 20% 증가된 크리티컬 데미지
            System.out.println(attacker.getName() + "의 분노한 크리티컬 히트! (" + String.format("%.1f", attacker.getCriticalDamage() * 120) + "% 데미지)");
        }
        return (int) Math.round(damage);
    }
}