package entity;

import config.ConfigManager;
import config.Tribes;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import battlesystem.AttackStrategy;
import battlesystem.NormalAttackStrategy;

public class Player implements Entity {
    private static final int EXP_PER_LEVEL = 100;

    private String name;
    private Tribes tribe;
    private int level;
    private int maxHp;
    private int currentHp;
    private int attack;
    private int defense;
    private int exp;
    private int expToNextLevel;
    private double criticalChance;  // 크리티컬 확률
    private double criticalDamage;  // 크리티컬 피해량 배율
    private AttackStrategy attackStrategy;

    public Player(String name, @NotNull Tribes tribe) {
        this.name = name;
        this.tribe = tribe;
        this.level = 1;
        
        JSONObject playerConfig = ConfigManager.getInstance().getPlayerConfig();
        
        this.maxHp = playerConfig.getInt("initialHp") + tribe.getHpBonus();
        this.currentHp = this.maxHp;
        this.attack = playerConfig.getInt("initialAttack") + tribe.getAttackBonus();
        this.defense = playerConfig.getInt("initialDefense") + tribe.getDefenseBonus();
        this.exp = 0;
        this.expToNextLevel = playerConfig.getInt("initialExpToNextLevel");
        this.criticalChance = playerConfig.getDouble("initialCriticalChance");
        this.criticalDamage = playerConfig.getDouble("initialCriticalDamage");
        this.attackStrategy = new NormalAttackStrategy();
    }

    public void gainExp(int amount) {
        this.exp += amount;
        System.out.println(name + "이(가) " + amount + "의 경험치를 획득했습니다.");
        while (this.exp >= this.expToNextLevel) {
            levelUp();
        }
    }

    private void levelUp() {
        this.level++;
        this.maxHp += tribe.getHpRatio();
        this.attack += tribe.getAttackRatio();
        this.defense += tribe.getDefenseRatio();
        this.currentHp = this.maxHp;  // 레벨업 시 체력 회복
        this.exp -= this.expToNextLevel;
        this.expToNextLevel = this.level * EXP_PER_LEVEL;
        this.criticalChance += tribe.getCriticalChanceRatio();
        this.criticalDamage += tribe.getCriticalDamageRatio();
        System.out.println(name + "이(가) 레벨 " + level + "로 올랐습니다!");
    }

    @Override
    public int attackDamage() {
        return attackStrategy.calculateDamage(this);
    }

    @Override
    public void takeDamage(int damage) {
        int actualDamage = Math.max(1, damage - this.defense);
        this.currentHp = Math.max(0, this.currentHp - actualDamage);
        System.out.println(name + " - 💔 " + actualDamage);
    }

    @Override
    public boolean isAlive() {
        return this.currentHp > 0;
    }

    @Override
    public void setAttackStrategy(AttackStrategy strategy) {
        this.attackStrategy = strategy;
    }

    @Override
    public AttackStrategy getAttackStrategy() {
        return this.attackStrategy;
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
    public int getLevel() { return level; }
    public int getDefense() { return defense; }
    public int getExp() { return exp; }
    public int getExpToNextLevel() { return expToNextLevel; }

    public void showStatus() {
        System.out.println("이름: " + name);
        System.out.println("종족: " + tribe.getName());
        System.out.println("레벨: " + level);
        System.out.println("경험치: " + exp + "/" + expToNextLevel);
        System.out.println("체력: " + currentHp + "/" + maxHp);
        System.out.println("공격력: " + attack);
        System.out.println("방어력: " + defense);
        System.out.println("크리티컬 확률: " + String.format("%.1f%%", criticalChance * 100));
        System.out.println("크리티컬 피해량: " + String.format("%.1f%%", criticalDamage * 100));
    }
}
