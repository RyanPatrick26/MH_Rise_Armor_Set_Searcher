package com.ryanpatrick.mhrisearmorsetsearcher.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "gem_tbl")
public class Gem{
	@PrimaryKey
	private long id;
	@ColumnInfo(name = "gem_name")
	private String name;
	@ColumnInfo(name = "gem_level")
	private int gemLevel;
	@ColumnInfo(name = "skill_level")
	private int skillLevel;

	public Gem(String name, int gemLevel, int skillLevel) {
		this.name = name;
		this.gemLevel = gemLevel;
		this.skillLevel = skillLevel;
	}

	public String getName(){
		return name;
	}

	public int getGemLevel(){
		return gemLevel;
	}

	public int getSkillLevel(){
		return skillLevel;
	}
}
