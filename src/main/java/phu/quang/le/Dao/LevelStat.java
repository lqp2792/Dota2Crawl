package phu.quang.le.Dao;

/**
 * Stat of hero at which level
 * @author Phu Le
 *
 */
public class LevelStat {
	private int level;
	private int hitPoints;
	private int mana;
	private int startDamage;
	private int endDamage;
	private double armor;
	private double attackSpeed;
	
	public double getArmor() {
		return armor;
	}
	
	public void setArmor(double armor) {
		this.armor = armor;
	}
	
	public double getAttackSpeed() {
		return attackSpeed;
	}
	
	public void setAttackSpeed(double attackSpeed) {
		this.attackSpeed = attackSpeed;
	}
	
	public int getEndDamage() {
		return endDamage;
	}
	
	public void setEndDamage(int endDamage) {
		this.endDamage = endDamage;
	}
	
	public int getHitPoints() {
		return hitPoints;
	}
	
	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getMana() {
		return mana;
	}
	
	public void setMana(int mana) {
		this.mana = mana;
	}
	
	public int getStartDamage() {
		return startDamage;
	}
	
	public void setStartDamage(int startDamage) {
		this.startDamage = startDamage;
	}
	
}
