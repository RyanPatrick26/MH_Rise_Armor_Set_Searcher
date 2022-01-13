package com.ryanpatrick.mhrisearmorsetsearcher.data;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.ryanpatrick.mhrisearmorsetsearcher.model.Armor;
import com.ryanpatrick.mhrisearmorsetsearcher.util.enums.ArmorType;
import com.ryanpatrick.mhrisearmorsetsearcher.util.enums.Gender;

import java.util.List;

public class ArmorRepository {
    private ArmorDao armorDao;
    private LiveData<List<Armor>> armorList;

    public ArmorRepository(Application application){
        Log.d("here", "ArmorRepository: " + application);
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
    public void initializeArmorDb(){
        //240 armor pieces

    }
}
