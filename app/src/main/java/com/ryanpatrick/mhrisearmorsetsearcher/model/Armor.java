package com.ryanpatrick.mhrisearmorsetsearcher.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.ryanpatrick.mhrisearmorsetsearcher.util.enums.ArmorType;
import com.ryanpatrick.mhrisearmorsetsearcher.util.enums.Gender;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "armor_tbl")
public class Armor{
	@PrimaryKey(autoGenerate = true)
	private int armorId;
	@ColumnInfo(name = "armor_name")
	private String name;
	@ColumnInfo(name = "rarity")
	private int rarity;
	@ColumnInfo(name = "gender")
	private Gender gender;
	@ColumnInfo(name = "armor_type")
	private ArmorType type;
	@ColumnInfo(name = "defense")
	private int defense;
	@ColumnInfo(name = "fire_res")
	private int fireRes;
	@ColumnInfo(name = "water_res")
	private int waterRes;
	@ColumnInfo(name = "ice_res")
	private int iceRes;
	@ColumnInfo(name = "thunder_res")
	private int thunderRes;
	@ColumnInfo(name = "dragon_res")
	private int dragonRes;
	@ColumnInfo(name = "skills")
	private ArrayList<Skill> skills;
	@ColumnInfo(name = "slots")
	private ArrayList<Slot> slots;

	public Armor(String name, int rarity, Gender gender, ArmorType type,
				 int defense, int fireRes, int waterRes, int iceRes, int thunderRes, int dragonRes,
				 ArrayList<Skill> skills, ArrayList<Slot> slots) {
		this.gender = gender;
		this.thunderRes = thunderRes;
		this.type = type;
		this.skills = skills;
		this.slots = slots;
		this.fireRes = fireRes;
		this.defense = defense;
		this.waterRes = waterRes;
		this.name = name;
		this.iceRes = iceRes;
		this.dragonRes = dragonRes;
		this.rarity = rarity;
	}

	//region getters and setters

	public int getArmorId() {
		return armorId;
	}

	public void setArmorId(int armorId) {
		this.armorId = armorId;
	}

	public Gender getGender(){
		return gender;
	}

	public int getThunderRes(){
		return thunderRes;
	}

	public ArmorType getType(){
		return type;
	}

	public ArrayList<Skill> getSkills(){
		return skills;
	}

	public ArrayList<Slot> getSlots(){
		return slots;
	}

	public int getFireRes(){
		return fireRes;
	}

	public int getDefense(){
		return defense;
	}

	public int getWaterRes(){
		return waterRes;
	}

	public String getName(){
		return name;
	}

	public int getIceRes(){
		return iceRes;
	}

	public int getId(){
		return armorId;
	}

	public int getDragonRes(){
		return dragonRes;
	}

	public int getRarity(){
		return rarity;
	}


	//endregion
}
