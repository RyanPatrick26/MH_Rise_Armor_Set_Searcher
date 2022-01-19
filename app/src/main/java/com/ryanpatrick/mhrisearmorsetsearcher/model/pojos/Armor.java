package com.ryanpatrick.mhrisearmorsetsearcher.model.pojos;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.ryanpatrick.mhrisearmorsetsearcher.util.enums.ArmorType;
import com.ryanpatrick.mhrisearmorsetsearcher.util.enums.Gender;

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
	@ColumnInfo(name = "base_defense")
	private int baseDefense;
	@ColumnInfo(name = "max_defense")
	private int maxDefense;
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
	private Skill[] skills;
	@ColumnInfo(name = "slots")
	private Slot[] slots;

	public Armor(String name, int rarity, Gender gender, ArmorType type,
				 int baseDefense, int maxDefense, int fireRes, int waterRes, int thunderRes, int iceRes,
				 int dragonRes, Skill[] skills, Slot[] slots) {
		this.name = name;
		this.rarity = rarity;
		this.gender = gender;
		this.type = type;
		this.baseDefense = baseDefense;
		this.maxDefense = maxDefense;
		this.fireRes = fireRes;
		this.waterRes = waterRes;
		this.iceRes = iceRes;
		this.thunderRes = thunderRes;
		this.dragonRes = dragonRes;
		this.skills = skills;
		this.slots = slots;
	}

	//region getters and setters
	public int getArmorId() {
		return armorId;
	}

	public void setArmorId(int armorId) {
		this.armorId = armorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRarity() {
		return rarity;
	}

	public void setRarity(int rarity) {
		this.rarity = rarity;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public ArmorType getType() {
		return type;
	}

	public void setType(ArmorType type) {
		this.type = type;
	}

	public int getBaseDefense() {
		return baseDefense;
	}

	public void setBaseDefense(int baseDefense) {
		this.baseDefense = baseDefense;
	}

	public int getMaxDefense() {
		return maxDefense;
	}

	public void setMaxDefense(int maxDefense) {
		this.maxDefense = maxDefense;
	}

	public int getFireRes() {
		return fireRes;
	}

	public void setFireRes(int fireRes) {
		this.fireRes = fireRes;
	}

	public int getWaterRes() {
		return waterRes;
	}

	public void setWaterRes(int waterRes) {
		this.waterRes = waterRes;
	}

	public int getIceRes() {
		return iceRes;
	}

	public void setIceRes(int iceRes) {
		this.iceRes = iceRes;
	}

	public int getThunderRes() {
		return thunderRes;
	}

	public void setThunderRes(int thunderRes) {
		this.thunderRes = thunderRes;
	}

	public int getDragonRes() {
		return dragonRes;
	}

	public void setDragonRes(int dragonRes) {
		this.dragonRes = dragonRes;
	}

	public Skill[] getSkills() {
		return skills;
	}

	public void setSkills(Skill[] skills) {
		this.skills = skills;
	}

	public Slot[] getSlots() {
		return slots;
	}

	public void setSlots(Slot[] slots) {
		this.slots = slots;
	}

	//endregion
}
