package com.ryanpatrick.mhrisearmorsetsearcher.workers;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.ryanpatrick.mhrisearmorsetsearcher.R;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Armor;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.ArmorSet;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Skill;
import com.ryanpatrick.mhrisearmorsetsearcher.util.Constants;
import com.ryanpatrick.mhrisearmorsetsearcher.util.Convertors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
        List<Skill> searchSkills = Convertors.toTotalSkillList(getInputData().getString(Constants.SEARCH_SKILLS));

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

        //region determine all of the skills and skill levels of the set
        List<Skill> allSkills = filterSkills(head, chest, arms, waist, legs);

        allSkills.sort((o1, o2) -> {
            int result = o2.getSkillLevel() - o1.getSkillLevel();
            if(result != 0){
                return result;
            }
            result = o1.getSkillName()
                    .compareTo(o2.getSkillName());
            return result;
        });
        //endregion

        return new ArmorSet(head, chest, arms, waist, legs, totalBaseDefense, totalMaxDefense, totalFireRes,
                totalWaterRes, totalIceRes, totalThunderRes, totalDragonRes, allSkills, totalSlots);

    }

    //helper function to determine if a provided combination of armor pieces meets the user's requirements
    private boolean meetsConditions(Armor head, Armor chest, Armor arms, Armor waist, Armor legs, List<Skill> searchSkills) {
        //create a list of all the skills on the proposed armor set
        List<Skill> skillsOnSet = filterSkills(head, chest, arms, waist, legs);

        //create an array of ints that will be used to see if the set has enough points into each skill
        int[] skillLevels = new int[searchSkills.size()];

        //add up the levels of all the skills on the proposed set that the user requested
        for (int i = 0; i < searchSkills.size(); i++) {
            for (int j = 0; j < skillsOnSet.size(); j++) {
                if(searchSkills.get(i).getSkillName().equals(skillsOnSet.get(i).getSkillName())){
                    skillLevels[i] += skillsOnSet.get(j).getSkillLevel();
                }
            }
        }

        //returns false if any of the skills on the set do not have enough points into them
        for (int i = 0; i < searchSkills.size(); i++) {
            if(!(skillLevels[i] < searchSkills.get(i).getSkillLevel())){
                return false;
            }
        }

        return true;
    }

    //helper function to filter down to the unique skills in an armor set
    private List<Skill> filterSkills(Armor head, Armor chest, Armor arms, Armor waist, Armor legs){
        List<Skill> allSkills = new ArrayList<>();
        List<Skill> toRemove = new ArrayList<>();
        allSkills.addAll(Arrays.asList(head.getSkills()));
        allSkills.addAll(Arrays.asList(chest.getSkills()));
        allSkills.addAll(Arrays.asList(arms.getSkills()));
        allSkills.addAll(Arrays.asList(waist.getSkills()));
        allSkills.addAll(Arrays.asList(legs.getSkills()));


        for (int i = 0; i < allSkills.size(); i++) {
            if(!toRemove.contains(allSkills.get(i))){
                for (int j = i+1; j < allSkills.size(); j++) {
                    if(allSkills.get(i).getSkillName().equals(allSkills.get(j).getSkillName())){
                        toRemove.add(allSkills.get(j));
                    }
                }
            }
        }

        allSkills.removeAll(toRemove);

        for (Skill skill1 : allSkills) {
            for (Skill skill2 : toRemove) {
                if (skill1.getSkillName().equals(skill2.getSkillName()))
                    skill1.setSkillLevel(skill1.getSkillLevel() + skill2.getSkillLevel());
            }
        }

        return allSkills;
    }
}
