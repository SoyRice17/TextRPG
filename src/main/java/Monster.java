public class Monster {
    private String name;
    private int maxHp;
    private int currentHp;
    private int attack;
    private int expValue;

    public Monster(String name, int hp, int attack, int expValue) {
        this.name = name;
        this.maxHp = hp;
        this.currentHp = hp;
        this.attack = attack;
        this.expValue = expValue;
    }

    public void takeDamage(int damage) {
        this.currentHp = Math.max(0, this.currentHp - damage);
        System.out.println(name + " - 💔 " + damage);
    }

    public boolean isAlive() {
        return this.currentHp > 0;
    }

    // 새로운 메소드: 몬스터 상태 초기화
    public Monster clone() {
        return new Monster(this.name, this.maxHp, this.attack, this.expValue);
    }

    // Getter 메소드들
    public String getName() { return name; }
    public int getMaxHp() { return maxHp; }
    public int getCurrentHp() { return currentHp; }
    public int getAttack() { return attack; }
    public int getExpValue() { return expValue; }
}