package com.ryanpatrick.mhrisearmorsetsearcher.workers;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.common.collect.Lists;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Armor;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.ArmorSet;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Charm;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Decoration;
import com.ryanpatrick.mhrisearmorsetsearcher.util.Constants;
import com.ryanpatrick.mhrisearmorsetsearcher.util.Convertors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class SetBuilderWorker extends Worker {
    public static final String TAG = "here";
    int count = 0;

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

        List<Charm> charmsList = WorkerDataHolder.getInstance().getCharmList();

        ArmorSet firstSet = startSearch(allArmorsByType, charmsList, new Armor[5], searchSkills);
        if(firstSet != null){
            armorSetList.add(firstSet);
        }


        if(armorSetList.isEmpty())
            return Result.failure();
        else
            WorkerDataHolder.getInstance().setSetList(armorSetList);
            return Result.success();
    }

    private ArmorSet startSearch(List<List<Armor>> allArmorsByType, List<Charm> charmsList, Armor[] armors,
                                 HashMap<String, Integer> searchSkills) {
        List<Armor> armorList;
        for (Charm charm : charmsList) {
            if(isCharmRelevant(charm, searchSkills)) {
                armorList = armorSearch(allArmorsByType, charm, armors, searchSkills, 0);
                if (armorList != null) {
                    return createArmorSet(armorList, charm, null);
                }
            }
        }
        armorList = armorSearch(allArmorsByType, null, armors, searchSkills, 0);
        if(armorList != null){
            return createArmorSet(armorList, charmsList.size() > 0 ? charmsList.get(0) : null, null);
        }
        return null;
    }


    //function to find the first armor set to meet the conditions
    private List<Armor> armorSearch(List<List<Armor>> allArmors, Charm charm, Armor[] potentialSet, HashMap<String, Integer> searchSkills, int armorType){
        //Log.i(TAG, "armorSearch: " + Arrays.toString(potentialSet));
        //returns all one the search has reached the final list(legs)
        if(armorType >= allArmors.size())
            return Arrays.asList(potentialSet);

        /*
        loop through all armors of the given type
        0 - heads
        1 - chests
        2 - arms
        3 - waists
        4 - legs
         */
        for (Armor armor : allArmors.get(armorType)) {
            //Log.i(TAG, "armorSearch: " + armor);
            //check to see if a given armor piece is relevant to the armor set
            //(if it has any of the skills that are being searched for or slots for the set)
            if(isArmorRelevant(armor, searchSkills)){
                //add the armor to the array of potential armors
                potentialSet[armorType] = armor;
                //checks to see if the current set meets the search conditions
                if(meetsConditions(Arrays.asList(potentialSet), charm, searchSkills)) {
                    //loop through the array and set default armor pieces for armor slots
                    //that haven't been filled yet
                    for (int i = 0; i < 5; i++) {
                        if (potentialSet[i] == null)
                            potentialSet[i] = allArmors.get(i).get(0);
                        //Log.i("done", "armorSearch: " + potentialSet[i].getArmorName());
                    }
                    Log.i(TAG, "armorSearch: " + Arrays.toString(potentialSet));
                    //return the armor set
                    return Arrays.asList(potentialSet);
                }
                //loop through the next type of armors
                armorSearch(allArmors, charm, potentialSet, searchSkills, armorType + 1);
                //check again to see if the conditions are met
                if(meetsConditions(Arrays.asList(potentialSet), charm, searchSkills)){
                    //return the armor set
                    return Arrays.asList(potentialSet);
                }
                //remove the current piece from the potential set
                potentialSet[armorType] = null;
            }

        }
        //if there are no armor pieces of the current type that have a skill relevant to the set,
        //move to the next type of armor pieces, saving the result to a new variablead
        List<Armor> armorList = armorSearch(allArmors, charm, potentialSet, searchSkills, armorType + 1);
        if(meetsConditions(armorList, charm, searchSkills))
            return armorList;

        //return null if no armor sets were found
        return null;
    }

    //helper function to build an armor set
    private ArmorSet createArmorSet(List<Armor> potentialSet, Charm charm, List<Decoration> decorations) {
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
        if(charm != null){
            for (int slot : charm.getSlots()) {
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
        }

        int[] totalSlots = new int[]{lvl1Slots, lvl2Slots, lvl3Slots};
        //endregion

        //determine all of the skills and skill levels of the set
        HashMap<String, Integer> allSkills = filterSkills(potentialSet);

        return new ArmorSet(potentialSet.get(0), potentialSet.get(1), potentialSet.get(2), potentialSet.get(3), potentialSet.get(4),
                totalBaseDefense, totalMaxDefense, totalFireRes, totalWaterRes, totalIceRes, totalThunderRes, totalDragonRes,
                charm, decorations, allSkills, totalSlots);

    }

    //helper function to determine if a provided combination of armor pieces meets the user's requirements
    private boolean meetsConditions(List<Armor> potentialSet, Charm charm,  HashMap<String, Integer> searchSkills) {
        //create a map of all the skills on the proposed armor set
        HashMap<String, Integer> skillsOnSet = filterSkills(potentialSet);

        if(!skillsOnSet.keySet().containsAll(searchSkills.keySet()))
            return false;
        //create a map that will be used to see if the set has enough points into each skill
        HashMap<String, Integer> skillLevels = new HashMap<>();

        //add up the levels of all the skills on the proposed set that the user requested
        for(String key : skillsOnSet.keySet()){
            if(searchSkills.containsKey(key)){
                skillLevels.merge(key, searchSkills.get(key), Integer::sum);
            }
        }
        if(charm != null){
            if(charm.getSkill1() != null){
                skillLevels.merge(charm.getSkill1().getSkillName(), charm.getSkill1().getSkillLevel(), Integer::sum);
            }
            if(charm.getSkill2() != null){
                skillLevels.merge(charm.getSkill2().getSkillName(), charm.getSkill2().getSkillLevel(), Integer::sum);
            }
        }


        //returns false if any of the skills on the set do not have enough points into them
        for (String key : searchSkills.keySet()) {
            if (skillLevels.get(key) < searchSkills.get(key))
                return false;
        }
        return true;
    }

    //helper function to filter down to the unique skills in an armor set
    private HashMap<String, Integer> filterSkills(List<Armor> potentialSet){
        HashMap<String, Integer> allSkills = new HashMap<>();

        for (int i = 0; i < potentialSet.size(); i++) {
            if(potentialSet.get(i) != null)
                potentialSet.get(i).getSkills().forEach((name, level) ->
                        allSkills.merge(name, level, Integer::sum));
        }

        return allSkills;
    }

    //helper function to see if a given armor piece is relevant to the set
    private boolean isArmorRelevant(Armor armor, HashMap<String, Integer> searchSkills) {
        for (String key : searchSkills.keySet()) {
            if (armor.getSkills().containsKey(key))
                return true;
        }
        return false;
    }

    private boolean isCharmRelevant(Charm charm, HashMap<String, Integer> searchSkills){
        if(charm.getSkill1() != null && searchSkills.containsKey(charm.getSkill1().getSkillName()))
            return true;
        if(charm.getSkill2() != null && searchSkills.containsKey(charm.getSkill2().getSkillName()))
            return true;
        return false;
    }
}
