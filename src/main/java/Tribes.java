
public enum Tribes {
    HUMAN, ELF, DWARF, ORC;

    public String getName() {
        return ConfigManager.getTribesConfig().getJSONObject(this.name()).getString("name");
    }

    public int getHpBonus() {
        return ConfigManager.getTribesConfig().getJSONObject(this.name()).getInt("hpBonus");
    }

    public int getAttackBonus() {
        return ConfigManager.getTribesConfig().getJSONObject(this.name()).getInt("attackBonus");
    }

    public int getDefenseBonus() {
        return ConfigManager.getTribesConfig().getJSONObject(this.name()).getInt("defenseBonus");
    }

    public int getHpRatio() {
        return ConfigManager.getTribesConfig().getJSONObject(this.name()).getInt("hpRatio");
    }

    public int getAttackRatio() {
        return ConfigManager.getTribesConfig().getJSONObject(this.name()).getInt("attackRatio");
    }

    public int getDefenseRatio() {
        return ConfigManager.getTribesConfig().getJSONObject(this.name()).getInt("defenseRatio");
    }

    public double getCriticalChanceRatio() {
        return ConfigManager.getTribesConfig().getJSONObject(this.name()).getDouble("criticalChanceRatio");
    }

    public double getCriticalDamageRatio() {
        return ConfigManager.getTribesConfig().getJSONObject(this.name()).getDouble("criticalDamageRatio");
    }

    @Override
    public String toString() {
        return getName();
    }
}