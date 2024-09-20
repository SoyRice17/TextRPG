public interface Entity {
    String getName();
    int getMaxHp();
    int getCurrentHp();
    int getAttack();
    int attackDamage();
    void takeDamage(int damage);
    boolean isAlive();
}