package battlesystem;

import entity.Entity;

public interface AttackStrategy {
    int calculateDamage(Entity attacker);
}
