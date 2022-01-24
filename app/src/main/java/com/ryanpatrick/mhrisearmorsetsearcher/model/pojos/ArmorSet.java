package com.ryanpatrick.mhrisearmorsetsearcher.model.pojos;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "armor_set_tbl")
public class ArmorSet{
	@PrimaryKey(autoGenerate = true)
	private Long id;
	@ColumnInfo(name = "head")
	private Armor head;
	@ColumnInfo(name = "chest")
	private Armor chest;
	@ColumnInfo(name = "arms")
	private Armor arms;
	@ColumnInfo(name = "waist")
	private Armor waist;
	@ColumnInfo(name = "legs")
	private Armor legs;
	@ColumnInfo(name = "total_base_defense")
	private int totalBaseDefense;
	@ColumnInfo(name = "total_max_defense")
	private int totalMaxDefense;
	@ColumnInfo(name = "total_fire_res")
	private int totalFireRes;
	@ColumnInfo(name = "total_water_res")
	private int totalWaterRes;
	@ColumnInfo(name = "total_ice_res")
	private int totalIceRes;
	@ColumnInfo(name = "total_thunder_res")
	private int totalThunderRes;
	@ColumnInfo(name = "total_dragon_res")
	private int totalDragonRes;
	@ColumnInfo(name = "total_skills")
	private List<Skill> totalSkills;
	@ColumnInfo(name="total_slots")
	private List<Integer> totalSlots;

	public ArmorSet(Armor head, Armor chest, Armor arms, Armor waist, Armor legs,
					int totalBaseDefense, int totalMaxDefense, int totalFireRes,
					int totalWaterRes, int totalIceRes, int totalThunderRes, int totalDragonRes,
					List<Skill> totalSkills, List<Integer> totalSlots) {
		this.head = head;
		this.chest = chest;
		this.arms = arms;
		this.waist = waist;
		this.legs = legs;
		this.totalBaseDefense = totalBaseDefense;
		this.totalMaxDefense = totalMaxDefense;
		this.totalFireRes = totalFireRes;
		this.totalWaterRes = totalWaterRes;
		this.totalIceRes = totalIceRes;
		this.totalThunderRes = totalThunderRes;
		this.totalDragonRes = totalDragonRes;
		this.totalSkills = totalSkills;
		this.totalSlots = totalSlots;
	}

	//region getters and setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Armor getHead() {
		return head;
	}

	public void setHead(Armor head) {
		this.head = head;
	}

	public Armor getChest() {
		return chest;
	}

	public void setChest(Armor chest) {
		this.chest = chest;
	}

	public Armor getArms() {
		return arms;
	}

	public void setArms(Armor arms) {
		this.arms = arms;
	}

	public Armor getWaist() {
		return waist;
	}

	public void setWaist(Armor waist) {
		this.waist = waist;
	}

	public Armor getLegs() {
		return legs;
	}

	public void setLegs(Armor legs) {
		this.legs = legs;
	}

	public int getTotalBaseDefense() {
		return totalBaseDefense;
	}

	public void setTotalBaseDefense(int totalBaseDefense) {
		this.totalBaseDefense = totalBaseDefense;
	}

	public int getTotalMaxDefense() {
		return totalMaxDefense;
	}

	public void setTotalMaxDefense(int totalMaxDefense) {
		this.totalMaxDefense = totalMaxDefense;
	}

	public int getTotalFireRes() {
		return totalFireRes;
	}

	public void setTotalFireRes(int totalFireRes) {
		this.totalFireRes = totalFireRes;
	}

	public int getTotalWaterRes() {
		return totalWaterRes;
	}

	public void setTotalWaterRes(int totalWaterRes) {
		this.totalWaterRes = totalWaterRes;
	}

	public int getTotalIceRes() {
		return totalIceRes;
	}

	public void setTotalIceRes(int totalIceRes) {
		this.totalIceRes = totalIceRes;
	}

	public int getTotalThunderRes() {
		return totalThunderRes;
	}

	public void setTotalThunderRes(int totalThunderRes) {
		this.totalThunderRes = totalThunderRes;
	}

	public int getTotalDragonRes() {
		return totalDragonRes;
	}

	public void setTotalDragonRes(int totalDragonRes) {
		this.totalDragonRes = totalDragonRes;
	}

	public List<Skill> getTotalSkills() {
		return totalSkills;
	}

	public void setTotalSkills(List<Skill> totalSkills) {
		this.totalSkills = totalSkills;
	}

	public List<Integer> getTotalSlots() {
		return totalSlots;
	}

	public void setTotalSlots(List<Integer> totalSlots) {
		this.totalSlots = totalSlots;
	}

	//endregion
}
