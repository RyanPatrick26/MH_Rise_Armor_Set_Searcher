package com.ryanpatrick.mhrisearmorsetsearcher.workers;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Armor;
import com.ryanpatrick.mhrisearmorsetsearcher.repositories.ArmorRepository;
import com.ryanpatrick.mhrisearmorsetsearcher.util.Constants;
import com.ryanpatrick.mhrisearmorsetsearcher.util.enums.Gender;

import java.util.ArrayList;
import java.util.List;

public class DatabaseWorker extends Worker {
    private ArmorRepository armorRepository;

    public DatabaseWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        armorRepository = new ArmorRepository(context);
    }

    @NonNull
    @Override
    public Result doWork() {
        List<Armor> armorList = armorRepository
                .getAllArmorOfRarity(getInputData().getIntArray(Constants.RARITIES),
                        Gender.valueOf(getInputData().getString(Constants.GENDER)));
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

        WorkerDataHolder.getInstance().setHeads(heads);
        WorkerDataHolder.getInstance().setChests(chests);
        WorkerDataHolder.getInstance().setArms(arms);
        WorkerDataHolder.getInstance().setWaists(waists);
        WorkerDataHolder.getInstance().setLegs(legs);

        Log.i("here", "doWork: " + armorList.size());

        return Result.success();
    }
}
