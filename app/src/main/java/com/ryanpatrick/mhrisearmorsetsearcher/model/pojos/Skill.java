package com.ryanpatrick.mhrisearmorsetsearcher.model.pojos;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "skills_tbl")
public class Skill {
	@PrimaryKey(autoGenerate = true)
	private long skillId;
	@ColumnInfo(name = "skill_name")
	private String skillName;
	@ColumnInfo(name = "max_level")
	private int skillMaxLevel;
	@Ignore
	private int skillLevel;

	public Skill(String skillName, int skillMaxLevel) {
		this.skillName = skillName;
		this.skillMaxLevel = skillMaxLevel;
	}

	public Skill(String skillName, int skillMaxLevel, int skillLevel) {
		this.skillName = skillName;
		this.skillMaxLevel = skillMaxLevel;
		this.skillLevel = skillLevel;
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

	public int getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(int skillLevel) {
		this.skillLevel = skillLevel;
	}
	//endregion
}
