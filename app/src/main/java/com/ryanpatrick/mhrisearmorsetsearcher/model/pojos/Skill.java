package com.ryanpatrick.mhrisearmorsetsearcher.model.pojos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "skills_tbl")
public class Skill {
	@PrimaryKey
	@NonNull
	@ColumnInfo(name = "skill_name")
	private String skillName;
	@ColumnInfo(name = "max_level")
	private int skillMaxLevel;
	@ColumnInfo(name="description")
	private String description;
	@ColumnInfo(name = "on_charm")
	boolean onCharm;
	@Ignore
	private int skillLevel;

	@Ignore
	public Skill(){
		skillName = "";
	}

	@Ignore
	public Skill(@NonNull String skillName, int skillLevel) {
		this.skillName = skillName;
		this.skillLevel = skillLevel;
	}

	public Skill(@NonNull String skillName, int skillMaxLevel, String description, boolean onCharm) {
		this.skillName = skillName;
		this.skillMaxLevel = skillMaxLevel;
		this.description = description;
		this.onCharm = onCharm;
	}

	//region getters and setters
	@NonNull
	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(@NonNull String skillName) {
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

	public boolean isOnCharm() {
		return onCharm;
	}

	public void setOnCharm(boolean onCharm) {
		this.onCharm = onCharm;
	}
	//endregion
}
