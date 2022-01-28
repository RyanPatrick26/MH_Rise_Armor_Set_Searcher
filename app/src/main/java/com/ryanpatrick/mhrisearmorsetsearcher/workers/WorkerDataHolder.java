package com.ryanpatrick.mhrisearmorsetsearcher.workers;

import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Armor;

import java.util.List;

public class WorkerDataHolder {
    private static WorkerDataHolder INSTANCE;
    private List<Armor> heads;
    private List<Armor> chests;
    private List<Armor> arms;
    private List<Armor> waists;
    private List<Armor> legs;

    public static synchronized  WorkerDataHolder getInstance(){
        if(INSTANCE == null){
            INSTANCE = new WorkerDataHolder();
        }
        return INSTANCE;
    }
    public List<Armor> getHeads() {
        return heads;
    }
    public void setHeads(List<Armor> heads) {
        this.heads = heads;
    }
    public List<Armor> getChests() {
        return chests;
    }
    public void setChests(List<Armor> chests) {
        this.chests = chests;
    }
    public List<Armor> getArms() {
        return arms;
    }
    public void setArms(List<Armor> arms) {
        this.arms = arms;
    }
    public List<Armor> getWaists() {
        return waists;
    }
    public void setWaists(List<Armor> waists) {
        this.waists = waists;
    }
    public List<Armor> getLegs() {
        return legs;
    }
    public void setLegs(List<Armor> legs) {
        this.legs = legs;
    }
}
