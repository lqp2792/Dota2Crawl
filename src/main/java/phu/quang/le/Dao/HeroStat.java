package phu.quang.le.Dao;

/**
 * Hero stat information
 * @author Phu Le
 *
 */
public class HeroStat {
	private double beginStrength;
	private double beginAgility;
	private double beginIntel;
	private double strPerLevel;
	private double agiPerLevel;
	private double intPerLevel;
	
	private int moveSpeed;
	private double turnRate;
	private int daySightRange;
	private int nightSightRange;
	//String type, because melee and range
	private String attackRange;
	//String type, because melee and range
	private String missileSpeed;
	private double baseAttackTime;
	private double attackPoint;
	private double attackBackswing;
	private double castPoint;
	private double castBackswing;
	
	public double getStrPerLevel() {
		return strPerLevel;
	}
	public void setStrPerLevel(double strPerLevel) {
		this.strPerLevel = strPerLevel;
	}
	public double getBeginIntel() {
		return beginIntel;
	}
	public void setBeginIntel(double beginIntel) {
		this.beginIntel = beginIntel;
	}
	public double getBeginAgility() {
		return beginAgility;
	}
	public void setBeginAgility(double beginAgility) {
		this.beginAgility = beginAgility;
	}
	public double getBeginStrength() {
		return beginStrength;
	}
	public void setBeginStrength(double beginStrength) {
		this.beginStrength = beginStrength;
	}
	public double getIntPerLevel() {
		return intPerLevel;
	}
	public void setIntPerLevel(double intPerLevel) {
		this.intPerLevel = intPerLevel;
	}
	public double getAgiPerLevel() {
		return agiPerLevel;
	}
	public void setAgiPerLevel(double agiPerLevel) {
		this.agiPerLevel = agiPerLevel;
	}
	public int getMoveSpeed() {
		return moveSpeed;
	}
	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}
	public double getTurnRate() {
		return turnRate;
	}
	public void setTurnRate(double turnRate) {
		this.turnRate = turnRate;
	}
	public int getDaySightRange() {
		return daySightRange;
	}
	public void setDaySightRange(int daySightRange) {
		this.daySightRange = daySightRange;
	}
	public int getNightSightRange() {
		return nightSightRange;
	}
	public void setNightSightRange(int nightSightRange) {
		this.nightSightRange = nightSightRange;
	}
	public String getAttackRange() {
		return attackRange;
	}
	public void setAttackRange(String attackRange) {
		this.attackRange = attackRange;
	}
	public String getMissileSpeed() {
		return missileSpeed;
	}
	public void setMissileSpeed(String missileSpeed) {
		this.missileSpeed = missileSpeed;
	}
	public double getBaseAttackTime() {
		return baseAttackTime;
	}
	public void setBaseAttackTime(double baseAttackTime) {
		this.baseAttackTime = baseAttackTime;
	}
	public double getAttackPoint() {
		return attackPoint;
	}
	public void setAttackPoint(double attackPoint) {
		this.attackPoint = attackPoint;
	}
	public double getAttackBackswing() {
		return attackBackswing;
	}
	public void setAttackBackswing(double attackBackswing) {
		this.attackBackswing = attackBackswing;
	}
	public double getCastPoint() {
		return castPoint;
	}
	public void setCastPoint(double castPoint) {
		this.castPoint = castPoint;
	}
	public double getCastBackswing() {
		return castBackswing;
	}
	public void setCastBackswing(double castBackswing) {
		this.castBackswing = castBackswing;
	}
}
