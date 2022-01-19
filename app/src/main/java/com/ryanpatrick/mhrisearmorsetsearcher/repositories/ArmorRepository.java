package com.ryanpatrick.mhrisearmorsetsearcher.repositories;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.ryanpatrick.mhrisearmorsetsearcher.data.ApplicationDatabase;
import com.ryanpatrick.mhrisearmorsetsearcher.data.daos.ArmorDao;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Armor;
import com.ryanpatrick.mhrisearmorsetsearcher.util.DbConstants;
import com.ryanpatrick.mhrisearmorsetsearcher.util.enums.ArmorType;
import com.ryanpatrick.mhrisearmorsetsearcher.util.enums.Gender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArmorRepository {
    private ArmorDao armorDao;
    private LiveData<List<Armor>> armorList;
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
        if(!armorDao.getArmorList().equals(Arrays.asList(DbConstants.PREPOPULATE_ARMORS)))
            ApplicationDatabase.databaseWriter.execute(() ->
                    armorDao.updateDb(DbConstants.PREPOPULATE_ARMORS));

    }
}
