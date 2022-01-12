package com.ryanpatrick.mhrisearmorsetsearcher.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "gem_tbl")
public class Gem{
	@PrimaryKey(autoGenerate = true)
	private long id;
	@ColumnInfo(name = "gem_name")
	private String gemName;
	@ColumnInfo(name = "skill_name")
	private String skillName;
	@ColumnInfo(name = "gem_level")
	private int gemLevel;

	public Gem(String gemName, String skillName, int gemLevel) {
		this.gemName = gemName;
		this.skillName = skillName;
		this.gemLevel = gemLevel;
	}

	//region getters and setters
	public long getId(){return id;}

	public void setId(long id) {
		this.id = id;
	}

	public String getGemName() {
		return gemName;
	}

	public void setGemName(String gemName) {
		this.gemName = gemName;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public int getGemLevel() {
		return gemLevel;
	}

	public void setGemLevel(int gemLevel) {
		this.gemLevel = gemLevel;
	}
	//endregion
}
