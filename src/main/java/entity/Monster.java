package entity;

import battlesystem.AttackStrategy;
import battlesystem.NormalAttackStrategy;
import util.InputOutputManager;

public class Monster implements Entity {
    private String name;
    private int maxHp;
    private int currentHp;
    private int attack;
    private int expValue;
    private double criticalChance;
    private double criticalDamage;
    private AttackStrategy attackStrategy;

    private static final double DEFAULT_CRITICAL_CHANCE = 0.07;
    private static final double DEFAULT_CRITICAL_DAMAGE = 1.3;

    public Monster(String name, int hp, int attack, int expValue) {
        this.name = name;
        this.maxHp = hp;
        this.currentHp = hp;
        this.attack = attack;
        this.expValue = expValue;
        this.criticalChance = DEFAULT_CRITICAL_CHANCE;
        this.criticalDamage = DEFAULT_CRITICAL_DAMAGE;
        this.attackStrategy = new NormalAttackStrategy();
    }
    @Override
    public int attackDamage() {
        return attackStrategy.calculateDamage(this);
    }

    @Override
    public void setAttackStrategy(AttackStrategy strategy) {
        this.attackStrategy = strategy;
    }

    @Override
    public AttackStrategy getAttackStrategy() {
        return this.attackStrategy;
    }

    @Override
    public void takeDamage(int damage) {
        this.currentHp = Math.max(0, this.currentHp - damage);
        InputOutputManager.printMessage(name + " - 💔 " + damage);
    }

    @Override
    public boolean isAlive() {
        return this.currentHp > 0;
    }

    // 새로운 메소드: 몬스터 상태 초기화
    public Monster clone() {
        return new Monster(this.name, this.maxHp, this.attack, this.expValue);
    }

    // Getter 메소드들
    @Override
    public String getName() { return name; }
    @Override
    public int getMaxHp() { return maxHp; }
    @Override
    public int getCurrentHp() { return currentHp; }
    @Override
    public int getAttack() { return attack; }
    @Override
    public double getCriticalChance() { return criticalChance; }
    @Override
    public double getCriticalDamage() { return criticalDamage; }
    public int getExpValue() { return expValue; }
}