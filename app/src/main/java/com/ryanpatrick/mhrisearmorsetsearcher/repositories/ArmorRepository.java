package com.ryanpatrick.mhrisearmorsetsearcher.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.ryanpatrick.mhrisearmorsetsearcher.data.ApplicationDatabase;
import com.ryanpatrick.mhrisearmorsetsearcher.data.daos.ArmorDao;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Armor;
import com.ryanpatrick.mhrisearmorsetsearcher.util.Constants;
import com.ryanpatrick.mhrisearmorsetsearcher.util.enums.ArmorType;
import com.ryanpatrick.mhrisearmorsetsearcher.util.enums.Gender;

import java.util.Arrays;
import java.util.List;

public class ArmorRepository {
    private final ArmorDao armorDao;
    private final LiveData<List<Armor>> armorList;

    public ArmorRepository(Context context){
        ApplicationDatabase db = ApplicationDatabase.getInstance(context);
        armorDao = db.armorDao();
        armorList = armorDao.getAllArmor();
    }

    public LiveData<List<Armor>> getAllArmor(){return armorList;}
    public void insert(Armor armor){
        ApplicationDatabase.databaseWriter.execute(() -> armorDao.insert(armor));
    }
    public Armor getArmor(long id){
        return armorDao.getArmor(id);
    }
    public LiveData<List<Armor>> getAllArmorOfType(ArmorType type, Gender gender){
        return armorDao.getAllArmorOfType(type, gender);
    }
    public List<Armor> getArmorlist() {
        return armorDao.getArmorList();
    }
    public void updateDb(){
        ApplicationDatabase.databaseWriter.execute(() -> {
            if(armorDao.getArmorList().size() < Constants.prepopulateArmors.length &&
                    armorDao.getArmorList().size() > 0){
                List<Armor> updateList = Arrays.asList(Constants.prepopulateArmors);
                updateList.removeAll(updateList.subList(0, armorDao.getArmorList().size()));

                armorDao.insertAll((Armor[]) updateList.toArray());
            }
        });

    }
}
