package com.ryanpatrick.mhrisearmorsetsearcher.workers;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Armor;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Charm;
import com.ryanpatrick.mhrisearmorsetsearcher.repositories.ArmorRepository;
import com.ryanpatrick.mhrisearmorsetsearcher.repositories.CharmRepository;
import com.ryanpatrick.mhrisearmorsetsearcher.util.Constants;
import com.ryanpatrick.mhrisearmorsetsearcher.util.Convertors;
import com.ryanpatrick.mhrisearmorsetsearcher.util.enums.Gender;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseWorker extends Worker {
    private Context context;

    public DatabaseWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        //get references to the armor and charm repositories
        ArmorRepository armorRepository = new ArmorRepository(context);
        CharmRepository charmRepository = new CharmRepository(context);

        //reference to the search skills
        HashMap<String, Integer> searchSkills = Convertors.toSkillMap(getInputData().getString(Constants.SEARCH_SKILLS));

        //region get a list of all armor pieces, then
        //divides it based on the armor type into separate lists
        List<Armor> armorList = armorRepository.getArmorlist();
        List<Armor> heads = new ArrayList<>();
        List<Armor> chests = new ArrayList<>();
        List<Armor> arms = new ArrayList<>();
        List<Armor> waists = new ArrayList<>();
        List<Armor> legs = new ArrayList<>();

        for (Armor armor : armorList) {
            switch(armor.getType()){
                case Head:
                    heads.add(armor);
                    break;
                case Chest:
                    chests.add(armor);
                    break;
                case Arms:
                    arms.add(armor);
                    break;
                case Waist:
                    waists.add(armor);
                    break;
                case Legs:
                    legs.add(armor);
                    break;
            }
        }
        //endregion

        //get a list of all of the charms, and find all of the relevant ones
        List<Charm> charmsList  = charmRepository.getCharmsList();

        WorkerDataHolder.getInstance().setHeads(heads);
        WorkerDataHolder.getInstance().setChests(chests);
        WorkerDataHolder.getInstance().setArms(arms);
        WorkerDataHolder.getInstance().setWaists(waists);
        WorkerDataHolder.getInstance().setLegs(legs);
        WorkerDataHolder.getInstance().setCharmList(charmsList);

        return Result.success();
    }
}
