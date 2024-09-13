public enum Tribes {
    HUMAN("인간", 0, 0, 0, 3, 3, 3),
    ELF("엘프", -1, 2, 1, 2, 3, 1),
    DWARF("드워프", 2, -1, 1, 2, 2, 2),
    ORC("오크", 1, 1, -1, 3, 1, 2);

    private final String name;
    private final int hpBonus;
    private final int attackBonus;
    private final int defenseBonus;
    private final int hpRatio;
    private final int attackRatio;
    private final int defenseRatio;

    Tribes(String name, int hpBonus, int attackBonus, int defenseBonus, int hpRatio, int attackRatio, int defenseRatio) {
        this.name = name;
        this.hpBonus = hpBonus;
        this.attackBonus = attackBonus;
        this.defenseBonus = defenseBonus;
        this.hpRatio = hpRatio;
        this.attackRatio = attackRatio;
        this.defenseRatio = defenseRatio;
    }

    public String getName() {
        return name;
    }

    public int getHpBonus() {
        return hpBonus;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }
    public int getHpRatio() {
        return hpRatio;
    }
    public int getAttackRatio () {
        return attackRatio;
    }
    public int getDefenseRatio() {
        return defenseRatio;
    }

    @Override
    public String toString() {
        return name;
    }
}