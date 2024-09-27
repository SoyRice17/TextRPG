package battlesystem;

import java.util.Random;
import entity.Entity;

public class NormalAttackStrategy implements AttackStrategy {
    private static final Random random = new Random();

    @Override
    public int calculateDamage(Entity attacker) {
        double damage = attacker.getAttack();
        boolean isCritical = random.nextDouble() < attacker.getCriticalChance();
        if (isCritical) {
            damage *= attacker.getCriticalDamage();
            System.out.println(attacker.getName() + "의 크리티컬 히트! (" + String.format("%.1f", attacker.getCriticalDamage() * 100) + "% 데미지)");
        }
        return (int) Math.round(damage);
    }
}