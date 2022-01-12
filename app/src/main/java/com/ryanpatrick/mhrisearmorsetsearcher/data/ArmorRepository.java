package com.ryanpatrick.mhrisearmorsetsearcher.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.ryanpatrick.mhrisearmorsetsearcher.model.Armor;
import com.ryanpatrick.mhrisearmorsetsearcher.util.enums.ArmorType;
import com.ryanpatrick.mhrisearmorsetsearcher.util.enums.Gender;

import java.util.ArrayList;

public class ArmorRepository {
    private ArmorDao armorDao;
    private LiveData<ArrayList<Armor>> armorList;

    public ArmorRepository(Application application){
        ApplicationDatabase db = ApplicationDatabase.getInstance(application);
        armorDao = db.armorDao();

        armorList = armorDao.getAllArmor();
    }

    public LiveData<ArrayList<Armor>> getAllArmor(){return armorList;}
    public void insert(Armor armor){
        ApplicationDatabase.databaseWriter.execute(() -> armorDao.insert(armor));
    }

    public LiveData<Armor> getArmor(long id){
        return armorDao.getArmor(id);
    }
    public LiveData<ArrayList<Armor>> getAllArmorOfType(ArmorType type, Gender gender){
        return armorDao.getAllArmorOfType(type, gender);
    }
    public LiveData<ArrayList<Armor>> getAllArmorOfRarity(int rarity, Gender gender){
        return armorDao.getAllArmorOfRarity(rarity, gender);
    }
}
