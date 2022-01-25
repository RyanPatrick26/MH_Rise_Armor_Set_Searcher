package com.ryanpatrick.mhrisearmorsetsearcher.model.pojos;

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
	@ColumnInfo(name="description")
	private int descriptionResourceId;
	@Ignore
	private int skillLevel;

	public Skill(String skillName, int skillLevel) {
		this.skillName = skillName;
		this.skillLevel = skillLevel;
	}

	public Skill(String skillName, int skillMaxLevel, int descriptionResourceId) {
		this.skillName = skillName;
		this.skillMaxLevel = skillMaxLevel;
		this.descriptionResourceId = descriptionResourceId;
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

	public int getDescriptionResourceId() {
		return descriptionResourceId;
	}

	public void setDescriptionResourceId(int descriptionResourceId) {
		this.descriptionResourceId = descriptionResourceId;
	}

	//endregion
}
