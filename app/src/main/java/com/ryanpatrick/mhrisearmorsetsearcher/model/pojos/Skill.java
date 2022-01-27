package com.ryanpatrick.mhrisearmorsetsearcher.model.pojos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "skills_tbl")
public class Skill {
	@PrimaryKey(autoGenerate = true)
	private long id;
	@ColumnInfo(name = "skill_name")
	private String skillName;
	@ColumnInfo(name = "max_level")
	private int skillMaxLevel;
	@ColumnInfo(name="description")
	private String description;
	@Ignore
	private int skillLevel;

	@Ignore
	public Skill(){	}

	public Skill(@NonNull String skillName, int skillLevel) {
		this.skillName = skillName;
		this.skillLevel = skillLevel;
	}

	public Skill(String skillName, int skillMaxLevel, String description) {
		this.skillName = skillName;
		this.skillMaxLevel = skillMaxLevel;
		this.description = description;
	}

	//region getters and setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName( String skillName) {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	//endregion
}
