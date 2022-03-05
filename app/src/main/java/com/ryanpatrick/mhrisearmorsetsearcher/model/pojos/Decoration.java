package com.ryanpatrick.mhrisearmorsetsearcher.model.pojos;

import androidx.annotation.NonNull;
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


	/**
	 * Returns a string representation of the object. In general, the
	 * {@code toString} method returns a string that
	 * "textually represents" this object. The result should
	 * be a concise but informative representation that is easy for a
	 * person to read.
	 * It is recommended that all subclasses override this method.
	 * <p>
	 * The {@code toString} method for class {@code Object}
	 * returns a string consisting of the name of the class of which the
	 * object is an instance, the at-sign character `{@code @}', and
	 * the unsigned hexadecimal representation of the hash code of the
	 * object. In other words, this method returns a string equal to the
	 * value of:
	 * <blockquote>
	 * <pre>
	 * getClass().getName() + '@' + Integer.toHexString(hashCode())
	 * </pre></blockquote>
	 *
	 * @return a string representation of the object.
	 */
	@NonNull
	@Override
	public String toString() {
		return decorationName;
	}
}
