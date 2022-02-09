package com.ryanpatrick.mhrisearmorsetsearcher.workers;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.common.collect.Lists;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Armor;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.ArmorSet;
import com.ryanpatrick.mhrisearmorsetsearcher.util.Constants;
import com.ryanpatrick.mhrisearmorsetsearcher.util.Convertors;

import java.util.ArrayList;
import java.util.Arrays;
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
        List<List<Armor>> allArmorsByType = Arrays.asList(WorkerDataHolder.getInstance().getHeads(),
                WorkerDataHolder.getInstance().getChests(), WorkerDataHolder.getInstance().getArms(),
                WorkerDataHolder.getInstance().getWaists(), WorkerDataHolder.getInstance().getLegs());
        HashMap<String, Integer> searchSkills = Convertors.toSkillMap(getInputData().getString(Constants.SEARCH_SKILLS));


        
        //loop through each possible armor set, check to see if it meets the conditions set by the user, and add it to the list if it does
        //TEMPORARY
//        for (int i = 0; i < heads.size(); i++) {
//            for (int j = 0; j < chests.size(); j++) {
//                for (int k = 0; k < arms.size(); k++) {
//                    for (int l = 0; l < waists.size(); l++) {
//                        for (int m = 0; m < legs.size(); m++) {
//                            if(meetsConditions(heads.get(i), chests.get(j), arms.get(k), waists.get(l), legs.get(m), searchSkills)){
//                                armorSetList.add(createArmorSet(heads.get(i), chests.get(j), arms.get(k), waists.get(l), legs.get(m)));
//                            }
//                        }
//                    }
//                }
//            }
//        }

        //returns a failed result if no possible sets were found or success if there were
        if(armorSetList.isEmpty())
            return Result.failure();
        else
            return Result.success();
    }

    //helper function to build an armor set
    private ArmorSet createArmorSet(List<Armor> potentialSet) {
        //region calculate total defenses
        int totalBaseDefense = potentialSet.get(0).getBaseDefense() + potentialSet.get(1).getBaseDefense() + potentialSet.get(2).getBaseDefense()
                + potentialSet.get(3).getBaseDefense() + potentialSet.get(4).getBaseDefense();
        int totalMaxDefense = potentialSet.get(0).getMaxDefense() + potentialSet.get(1).getMaxDefense() + potentialSet.get(2).getMaxDefense()
                + potentialSet.get(3).getMaxDefense() + potentialSet.get(4).getMaxDefense();
        int totalFireRes = potentialSet.get(0).getFireRes() + potentialSet.get(1).getFireRes() + potentialSet.get(2).getFireRes()
                + potentialSet.get(3).getFireRes() + potentialSet.get(4).getFireRes();
        int totalWaterRes = potentialSet.get(0).getWaterRes() + potentialSet.get(1).getWaterRes() + potentialSet.get(2).getWaterRes()
                + potentialSet.get(3).getWaterRes() + potentialSet.get(4).getWaterRes();
        int totalThunderRes = potentialSet.get(0).getThunderRes() + potentialSet.get(1).getThunderRes() + potentialSet.get(2).getThunderRes()
                + potentialSet.get(3).getThunderRes() + potentialSet.get(4).getThunderRes();
        int totalIceRes = potentialSet.get(0).getIceRes() + potentialSet.get(1).getIceRes() + potentialSet.get(2).getIceRes()
                + potentialSet.get(3).getIceRes() + potentialSet.get(4).getIceRes();
        int totalDragonRes = potentialSet.get(0).getDragonRes() + potentialSet.get(1).getDragonRes() + potentialSet.get(2).getDragonRes()
                + potentialSet.get(3).getDragonRes() + potentialSet.get(4).getDragonRes();
        //endregion

        //region calculate total number of each slot
        int lvl1Slots = 0;
        int lvl2Slots = 0;
        int lvl3Slots = 0;

        for (int slot : potentialSet.get(0).getSlots()) {
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
        for (int slot : potentialSet.get(2).getSlots()) {
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
        for (int slot : potentialSet.get(1).getSlots()) {
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
        for (int slot : potentialSet.get(3).getSlots()) {
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
        for (int slot : potentialSet.get(4).getSlots()) {
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
        HashMap<String, Integer> allSkills = filterSkills(potentialSet);

        return new ArmorSet(potentialSet.get(0), potentialSet.get(1), potentialSet.get(2), potentialSet.get(3), potentialSet.get(4), totalBaseDefense, totalMaxDefense, totalFireRes,
                totalWaterRes, totalIceRes, totalThunderRes, totalDragonRes, allSkills, totalSlots);

    }

    //helper function to determine if a provided combination of armor pieces meets the user's requirements
    private boolean meetsConditions(List<Armor> potentialSet,  HashMap<String, Integer> searchSkills) {
        //create a map of all the skills on the proposed armor set
        HashMap<String, Integer> skillsOnSet = filterSkills(potentialSet);

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
    private HashMap<String, Integer> filterSkills(List<Armor> potentialSet){
        HashMap<String, Integer> allSkills = new HashMap<>(potentialSet.get(0).getSkills());

        for (int i = 1; i < potentialSet.size(); i++) {
            potentialSet.get(i).getSkills().forEach((name, level) -> 
                    allSkills.merge(name, level, Integer::sum));
        }

        return allSkills;
    }
}
