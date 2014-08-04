package phu.quang.le.Dao;

import java.util.List;

/**
 * Skill Information
 * @author Phu Le
 *
 */
public class Skill {
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
}
