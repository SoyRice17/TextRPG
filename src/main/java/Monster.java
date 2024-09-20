public class Monster implements Entity {
    private String name;
    private int maxHp;
    private int currentHp;
    private int attack;
    private int expValue;
    private double criticalChance;
    private double criticalDamage;

    public Monster(String name, int hp, int attack, int expValue) {
        this.name = name;
        this.maxHp = hp;
        this.currentHp = hp;
        this.attack = attack;
        this.expValue = expValue;
        this.criticalChance = 0.07;
        this.criticalDamage = 1.3;

    }
    @Override
    public int attackDamage () {
        double damage = this.attack;
        boolean isCritical = Math.random() < this.criticalChance;
        if (isCritical) {
            damage *= this.criticalDamage;
            System.out.println(name + "의 크리티컬 히트! (" + String.format("%.1f", this.criticalDamage * 100) + "% 데미지)");
        }
        return (int) Math.round(damage);
    }

    @Override
    public void takeDamage(int damage) {
        this.currentHp = Math.max(0, this.currentHp - damage);
        System.out.println(name + " - 💔 " + damage);
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