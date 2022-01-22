package com.ryanpatrick.mhrisearmorsetsearcher.model.pojos;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "decoration_tbl")
public class Decoration {
	@PrimaryKey(autoGenerate = true)
	private long id;
	@ColumnInfo(name = "decoration_name")
	private int decorationResourceId;
	@ColumnInfo(name = "skill_name")
	private int skillName;
	@ColumnInfo(name = "decoration_level")
	private int decorationLevel;

	public Decoration(int decorationResourceId, int skillName, int decorationLevel) {
		this.decorationResourceId = decorationResourceId;
		this.skillName = skillName;
		this.decorationLevel = decorationLevel;
	}

	//region getters and setters
	public long getId(){return id;}

	public void setId(long id) {
		this.id = id;
	}

	public int getDecorationResourceId() {
		return decorationResourceId;
	}

	public void setDecorationResourceId(int decorationResourceId) {
		this.decorationResourceId = decorationResourceId;
	}

	public int getSkillName() {
		return skillName;
	}

	public void setSkillName(int skillName) {
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
