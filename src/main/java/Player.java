public class Player {
    private String name;
    private Tribes tribe;
    private int level;
    private int maxHp;
    private int currentHp;
    private int attack;
    private int defense;
    private int exp;
    private int expToNextLevel;

    public Player(String name, Tribes tribe) {
        this.name = name;
        this.tribe = tribe;
        this.level = 1;
        this.maxHp = 100 + tribe.getHpBonus();
        this.currentHp = this.maxHp;
        this.attack = 10 + tribe.getAttackBonus();
        this.defense = 5 + tribe.getDefenseBonus();
        this.exp = 0;
        this.expToNextLevel = 100;
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
        this.maxHp += 10 + tribe.getHpRatio();
        this.attack += 2 + tribe.getAttackRatio();
        this.defense += 1 + tribe.getDefenseRatio();
        this.currentHp = this.maxHp;  // 레벨업 시 체력 회복
        this.exp -= this.expToNextLevel;
        this.expToNextLevel = this.level * 100;
        System.out.println(name + "이(가) 레벨 " + level + "로 올랐습니다!");
    }

    public void takeDamage(int damage) {
        int actualDamage = Math.max(1, damage - this.defense);
        this.currentHp = Math.max(0, this.currentHp - actualDamage);
        System.out.println(name + "이(가) " + actualDamage + "의 피해를 입었습니다.");
    }

    public boolean isAlive() {
        return this.currentHp > 0;
    }

    // Getter 메소드들
    public String getName() { return name; }
    public int getLevel() { return level; }
    public int getMaxHp() { return maxHp; }
    public int getCurrentHp() { return currentHp; }
    public int getAttack() { return attack; }
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
    }
}
