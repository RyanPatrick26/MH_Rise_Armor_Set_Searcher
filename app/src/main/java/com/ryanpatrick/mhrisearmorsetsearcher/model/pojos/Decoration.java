package com.ryanpatrick.mhrisearmorsetsearcher.model.pojos;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "decoration_tbl")
public class Decoration {
	@PrimaryKey(autoGenerate = true)
	private long id;
	@ColumnInfo(name = "decoration_name")
	private String decorationName;
	@ColumnInfo(name = "skill_name")
	private String skillName;
	@ColumnInfo(name = "decoration_level")
	private int decorationLevel;

	public Decoration(String decorationName, String skillName, int decorationLevel) {
		this.decorationName = decorationName;
		this.skillName = skillName;
		this.decorationLevel = decorationLevel;
	}

	//region getters and setters
	public long getId(){return id;}

	public void setId(long id) {
		this.id = id;
	}

	public String getDecorationName() {
		return decorationName;
	}

	public void setDecorationName(String decorationName) {
		this.decorationName = decorationName;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public int getDecorationLevel() {
		return decorationLevel;
	}

	public void setDecorationLevel(int decorationLevel) {
		this.decorationLevel = decorationLevel;
	}
	//endregion
}
