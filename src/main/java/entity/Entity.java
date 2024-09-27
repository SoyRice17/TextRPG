package entity;

import battlesystem.AttackStrategy;

public interface Entity {
    String getName();
    int getMaxHp();
    int getCurrentHp();
    int getAttack();
    int attackDamage();
    double getCriticalChance();
    double getCriticalDamage();
    void takeDamage(int damage);
    boolean isAlive();
    void setAttackStrategy(AttackStrategy strategy);
    AttackStrategy getAttackStrategy();
}