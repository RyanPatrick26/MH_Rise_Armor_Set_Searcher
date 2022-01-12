package com.ryanpatrick.mhrisearmorsetsearcher.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "skills_tbl")
public class Skill {
	@PrimaryKey(autoGenerate = true)
	private long skillId;
	@ColumnInfo(name = "skill_name")
	private String skillName;
	@ColumnInfo(name = "max_level")
	private int skillMaxLevel;

	public Skill(String skillName, int skillMaxLevel) {
		this.skillName = skillName;
		this.skillMaxLevel = skillMaxLevel;
	}

	//region getters and setters
	public long getSkillId() {
		return skillId;
	}

	public void setSkillId(long skillId) {
		this.skillId = skillId;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public int getSkillMaxLevel() {
		return skillMaxLevel;
	}

	public void setSkillMaxLevel(int skillMaxLevel) {
		this.skillMaxLevel = skillMaxLevel;
	}
	//endregion
}
