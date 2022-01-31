package com.ryanpatrick.mhrisearmorsetsearcher.workers;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Armor;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.ArmorSet;
import com.ryanpatrick.mhrisearmorsetsearcher.util.Constants;
import com.ryanpatrick.mhrisearmorsetsearcher.util.Convertors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SetBuilderWorker extends Worker {
    public static final String TAG = "here";

    public SetBuilderWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        //create an empty list to hold all potential armor sets
        List<ArmorSet> armorSetList = new ArrayList<>();

        //get all of the armor pieces queried by the previous worker, along with the skills the user inputted
        List<Armor> heads = WorkerDataHolder.getInstance().getHeads();
        List<Armor> chests = WorkerDataHolder.getInstance().getChests();
        List<Armor> arms = WorkerDataHolder.getInstance().getArms();
        List<Armor> waists = WorkerDataHolder.getInstance().getWaists();
        List<Armor> legs = WorkerDataHolder.getInstance().getLegs();
        HashMap<String, Integer> searchSkills = Convertors.toSkillMap(getInputData().getString(Constants.SEARCH_SKILLS));

        //loop through each possible armor set, check to see if it meets the conditions set by the user, and add it to the list if it does
        //TEMPORARY
        for (int i = 0; i < heads.size(); i++) {
            for (int j = 0; j < chests.size(); j++) {
                for (int k = 0; k < arms.size(); k++) {
                    for (int l = 0; l < waists.size(); l++) {
                        for (int m = 0; m < legs.size(); m++) {
                            if(meetsConditions(heads.get(i), chests.get(j), arms.get(k), waists.get(l), legs.get(m), searchSkills)){
                                armorSetList.add(createArmorSet(heads.get(i), chests.get(j), arms.get(k), waists.get(l), legs.get(m)));
                            }
                        }
                    }
                }
            }
        }

        //returns a failed result if no possible sets were found or success if there were
        if(armorSetList.isEmpty())
            return Result.failure();
        else
            return Result.success();
    }

    //helper function to build an armor set
    private ArmorSet createArmorSet(Armor head, Armor chest, Armor arms, Armor waist, Armor legs) {
        //region calculate total defenses
        int totalBaseDefense = head.getBaseDefense() + chest.getBaseDefense() + arms.getBaseDefense()
                + waist.getBaseDefense() + legs.getBaseDefense();
        int totalMaxDefense = head.getMaxDefense() + chest.getMaxDefense() + arms.getMaxDefense()
                + waist.getMaxDefense() + legs.getMaxDefense();
        int totalFireRes = head.getFireRes() + chest.getFireRes() + arms.getFireRes()
                + waist.getFireRes() + legs.getFireRes();
        int totalWaterRes = head.getWaterRes() + chest.getWaterRes() + arms.getWaterRes()
                + waist.getWaterRes() + legs.getWaterRes();
        int totalThunderRes = head.getThunderRes() + chest.getThunderRes() + arms.getThunderRes()
                + waist.getThunderRes() + legs.getThunderRes();
        int totalIceRes = head.getIceRes() + chest.getIceRes() + arms.getIceRes()
                + waist.getIceRes() + legs.getIceRes();
        int totalDragonRes = head.getDragonRes() + chest.getDragonRes() + arms.getDragonRes()
                + waist.getDragonRes() + legs.getDragonRes();
        //endregion

        //region calculate total number of each slot
        int lvl1Slots = 0;
        int lvl2Slots = 0;
        int lvl3Slots = 0;

        for (int slot : head.getSlots()) {
            switch (slot) {
                case 1:
                    lvl1Slots++;
                    break;
                case 2:
                    lvl2Slots++;
                    break;
                case 3:
                    lvl3Slots++;
                    break;
                default:
                    break;
            }
        }
        for (int slot : arms.getSlots()) {
            switch (slot) {
                case 1:
                    lvl1Slots++;
                    break;
                case 2:
                    lvl2Slots++;
                    break;
                case 3:
                    lvl3Slots++;
                    break;
                default:
                    break;
            }
        }
        for (int slot : chest.getSlots()) {
            switch (slot) {
                case 1:
                    lvl1Slots++;
                    break;
                case 2:
                    lvl2Slots++;
                    break;
                case 3:
                    lvl3Slots++;
                    break;
                default:
                    break;
            }
        }
        for (int slot : waist.getSlots()) {
            switch (slot) {
                case 1:
                    lvl1Slots++;
                    break;
                case 2:
                    lvl2Slots++;
                    break;
                case 3:
                    lvl3Slots++;
                    break;
                default:
                    break;
            }
        }
        for (int slot : legs.getSlots()) {
            switch (slot) {
                case 1:
                    lvl1Slots++;
                    break;
                case 2:
                    lvl2Slots++;
                    break;
                case 3:
                    lvl3Slots++;
                    break;
                default:
                    break;
            }
        }

        int[] totalSlots = new int[]{lvl1Slots, lvl2Slots, lvl3Slots};
        //endregion

        //determine all of the skills and skill levels of the set
        HashMap<String, Integer> allSkills = filterSkills(head, chest, arms, waist, legs);

        return new ArmorSet(head, chest, arms, waist, legs, totalBaseDefense, totalMaxDefense, totalFireRes,
                totalWaterRes, totalIceRes, totalThunderRes, totalDragonRes, allSkills, totalSlots);

    }

    //helper function to determine if a provided combination of armor pieces meets the user's requirements
    private boolean meetsConditions(Armor head, Armor chest, Armor arms, Armor waist, Armor legs,  HashMap<String, Integer> searchSkills) {
        //create a map of all the skills on the proposed armor set
        HashMap<String, Integer> skillsOnSet = filterSkills(head, chest, arms, waist, legs);

        //create a map that will be used to see if the set has enough points into each skill
        HashMap<String, Integer> skillLevels = new HashMap<>();

        //add up the levels of all the skills on the proposed set that the user requested
        for(String key : skillsOnSet.keySet()){
            if(searchSkills.containsKey(key)){
                if(skillLevels.containsKey(key)){
                    skillLevels.replace(key, skillLevels.get(key) + skillsOnSet.get(key));
                }
                else{
                    skillLevels.put(key, 0);
                }
            }
        }

        //returns false if any of the skills on the set do not have enough points into them
        for (String key : skillLevels.keySet()) {
            if (skillLevels.get(key) < searchSkills.get(key)) {
                return false;
            }
        }

        return true;
    }

    //helper function to filter down to the unique skills in an armor set
    private HashMap<String, Integer> filterSkills(Armor head, Armor chest, Armor arms, Armor waist, Armor legs){
        HashMap<String, Integer> allSkills = new HashMap<>(head.getSkills());

        chest.getSkills().forEach((name, level) ->
                allSkills.merge(name, level, Integer::sum));
        arms.getSkills().forEach((name, level) ->
                allSkills.merge(name, level, Integer::sum));
        waist.getSkills().forEach((name, level) ->
                allSkills.merge(name, level, Integer::sum));
        legs.getSkills().forEach((name, level) ->
                allSkills.merge(name, level, Integer::sum));

        return allSkills;
    }
}
