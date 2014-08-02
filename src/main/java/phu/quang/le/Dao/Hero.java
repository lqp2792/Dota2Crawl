package phu.quang.le.Dao;

import java.util.List;

/**
 * Hero information
 * @author Phu Le
 * 
 */
public class Hero {
	private String name;
	private String linkName;
	private String imgUrl;
	private String bio;
	private String voice;
	private HeroStat heroStat;
	private List<Role> roles;
	private List<Skill> skills;
	private List<LevelStat> levelStat;
	
	public String getImgUrl() {
		return imgUrl;
	}
	
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public String getLinkName() {
		return linkName;
	}
	
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getVoice() {
		return voice;
	}

	public void setVoice(String voice) {
		this.voice = voice;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public HeroStat getHeroStat() {
		return heroStat;
	}

	public void setHeroStat(HeroStat heroStat) {
		this.heroStat = heroStat;
	}

	public List<LevelStat> getLevelStat() {
		return levelStat;
	}

	public void setLevelStat(List<LevelStat> levelStat) {
		this.levelStat = levelStat;
	}
}
