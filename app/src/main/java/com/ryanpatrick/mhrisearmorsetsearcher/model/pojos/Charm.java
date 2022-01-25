package com.ryanpatrick.mhrisearmorsetsearcher.model.pojos;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "charm_tbl")
public class Charm {
    @PrimaryKey(autoGenerate = true)
    private long charmId;
    @ColumnInfo(name = "skill1")
    private Skill skill1;
    @ColumnInfo(name = "skill2")
    private Skill skill2;
    @ColumnInfo(name = "slots")
    private int[] slots;

    public Charm(Skill skill1, Skill skill2, int[] slots) {
        this.skill1 = skill1;
        this.skill2 = skill2;
        this.slots = slots;
    }

    public long getCharmId() {
        return charmId;
    }

    public void setCharmId(long charmId) {
        this.charmId = charmId;
    }

    public Skill getSkill1() {
        return skill1;
    }

    public void setSkill1(Skill skill1) {
        this.skill1 = skill1;
    }

    public Skill getSkill2() {
        return skill2;
    }

    public void setSkill2(Skill skill2) {
        this.skill2 = skill2;
    }

    public int[] getSlots() {
        return slots;
    }

    public void setSlots(int[] slots) {
        this.slots = slots;
    }
}
