public class Player {
    private String name;
    private Tribes tribe;
    private int level;
    private int baseHp;
    private int baseAttack;
    private int baseDefense;
    private int exp;
    private int expToNextLevel;


    public Player(String name,Tribes tribe) {
        this.name = name;
        this.tribe = tribe;
        this.level = 1;
        this.baseHp = 100;
        this.baseAttack = 5;
        this.baseDefense = 1;
        this.exp = 0;
        this.expToNextLevel = 100;
    }
    public void gainExp (int amount) {
        this.exp += amount;
        if (this.exp >= this.expToNextLevel) {
            levelUp();
        }
    }
    private void levelUp() {
        while (this.exp >= this.expToNextLevel) {
            this.level++;
            this.baseHp += tribe.getHpRatio();
            this.baseAttack += tribe.getAttackRatio();
            this.baseDefense += tribe.getDefenseRatio();
            this.exp -= this.expToNextLevel;
            this.expToNextLevel = this.level * 100;  // 레벨에 따라 필요 경험치 증가
            System.out.println(name + "이(가) 레벨 " + level + "로 올랐습니다!");
        }
    }
    public String getName() {
        return name;
    }
    public Tribes getTribe() {
        return tribe;
    }
    public int getLevel() {
        return level;
    }
    public int getHp() {
        return baseHp + tribe.getHpBonus();
    }
    public  int getAttack() {
        return baseAttack + tribe.getAttackBonus();
    }
    public int getDefense() {
        return baseDefense + tribe.getDefenseBonus();
    }
    public int getExp() {
        return exp;
    }
    public int getExpToNextLevel() {
        return expToNextLevel;
    }
    public void showStatus() {
        System.out.println("이름: " + name);
        System.out.println("종족: " + tribe.getName());
        System.out.println("레벨: " + level);
        System.out.println("경험치: " + exp + "/" + expToNextLevel);
        System.out.println("체력: " + getHp());
        System.out.println("공격력: " + getAttack());
        System.out.println("방어력: " + getDefense());
    }
}
