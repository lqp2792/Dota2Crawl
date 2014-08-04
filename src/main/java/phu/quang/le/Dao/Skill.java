package phu.quang.le.Dao;

import java.util.List;

/**
 * Skill Information
 * 
 * @author Phu Le
 *
 */
public class Skill {

	private String target;
	private String affect;
	private String damageType;
	// mana was printed as form xx/xx/xx/xx
	private String mana;
	private double cooldown;
	private String skillBio;
	private List<StringBuffer> notes;

	public List<StringBuffer> getNotes () {

		return notes;
	}

	public void setNotes (List<StringBuffer> notes) {

		this.notes = notes;
	}

	public String getSkillBio () {

		return skillBio;
	}

	public void setSkillBio (String skillBio) {

		this.skillBio = skillBio;
	}

	public double getCooldown () {

		return cooldown;
	}

	public void setCooldown (double cooldown) {

		this.cooldown = cooldown;
	}

	public String getMana () {

		return mana;
	}

	public void setMana (String mana) {

		this.mana = mana;
	}

	public String getDamageType () {

		return damageType;
	}

	public void setDamageType (String damageType) {

		this.damageType = damageType;
	}

	public String getAffect () {

		return affect;
	}

	public void setAffect (String affect) {

		this.affect = affect;
	}

	public String getTarget () {

		return target;
	}

	public void setTarget (String target) {

		this.target = target;
	}
}
