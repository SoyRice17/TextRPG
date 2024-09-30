package battlesystem;

import java.util.Random;
import entity.Entity;

public class RageAttackStrategy implements AttackStrategy {
    private static final Random random = new Random();
    private static final double ATTACK_BOOST = 1.5;
    private static final double CRITICAL_CHANCE_BOOST = 1.2;
    private static final double CRITICAL_DAMAGE_BOOST = 1.2;

    @Override
    public int calculateDamage(Entity attacker) {
        double damage = attacker.getAttack() * ATTACK_BOOST;
        boolean isCritical = random.nextDouble() < (attacker.getCriticalChance() * CRITICAL_CHANCE_BOOST);
        if (isCritical) {
            damage *= (attacker.getCriticalDamage() * CRITICAL_DAMAGE_BOOST);
            System.out.println(attacker.getName() + "의 분노한 크리티컬 히트! (" + 
                String.format("%.1f", attacker.getCriticalDamage() * CRITICAL_DAMAGE_BOOST * 100) + "% 데미지)");
        }
        return (int) Math.round(damage);
    }
}