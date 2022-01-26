package com.ryanpatrick.mhrisearmorsetsearcher.repositories;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.ryanpatrick.mhrisearmorsetsearcher.data.ApplicationDatabase;
import com.ryanpatrick.mhrisearmorsetsearcher.data.daos.ArmorDao;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Armor;
import com.ryanpatrick.mhrisearmorsetsearcher.util.DbConstants;
import com.ryanpatrick.mhrisearmorsetsearcher.util.enums.ArmorType;
import com.ryanpatrick.mhrisearmorsetsearcher.util.enums.Gender;

import java.util.Arrays;
import java.util.List;

public class ArmorRepository {
    private final ArmorDao armorDao;
    private final LiveData<List<Armor>> armorList;
    private Context dbContext;

    public ArmorRepository(Application application){
        ApplicationDatabase db = ApplicationDatabase.getInstance(application.getApplicationContext());
        armorDao = db.armorDao();
        armorList = armorDao.getAllArmor();
    }

    public LiveData<List<Armor>> getAllArmor(){return armorList;}
    public void insert(Armor armor){
        ApplicationDatabase.databaseWriter.execute(() -> armorDao.insert(armor));
    }
    public LiveData<Armor> getArmor(long id){
        return armorDao.getArmor(id);
    }
    public LiveData<List<Armor>> getAllArmorOfType(ArmorType type, Gender gender){
        return armorDao.getAllArmorOfType(type, gender);
    }
    public LiveData<List<Armor>> getAllArmorOfRarity(int rarity, Gender gender){
        return armorDao.getAllArmorOfRarity(rarity, gender);
    }
    public void updateDb(){
        ApplicationDatabase.databaseWriter.execute(() -> {
            if(armorDao.getArmorList().size() < DbConstants.prepopulateArmors.length){
                List<Armor> updateList = Arrays.asList(DbConstants.prepopulateArmors);
                updateList.removeAll(updateList.subList(0, armorDao.getArmorList().size()));

                armorDao.insertAll((Armor[]) updateList.toArray());
            }
        });

    }
}
