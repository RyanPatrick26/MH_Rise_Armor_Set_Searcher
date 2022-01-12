package com.ryanpatrick.mhrisearmorsetsearcher.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.ryanpatrick.mhrisearmorsetsearcher.model.Armor;
import com.ryanpatrick.mhrisearmorsetsearcher.model.ArmorSet;
import com.ryanpatrick.mhrisearmorsetsearcher.util.enums.ArmorType;
import com.ryanpatrick.mhrisearmorsetsearcher.util.enums.Gender;

import java.util.ArrayList;
import java.util.List;

public class ArmorSetRepository {
    private ArmorSetDao armorSetDao;
    private LiveData<List<ArmorSet>> armorSetList;

    public ArmorSetRepository(Application application) {
        ApplicationDatabase db = ApplicationDatabase.getInstance(application);
        armorSetDao = db.setDao();

        armorSetList = armorSetDao.selectAllArmorSets();
    }

    public LiveData<List<ArmorSet>> getAllArmorSets() {
        return armorSetList;
    }
    public void insert(ArmorSet armorSet){
        ApplicationDatabase.databaseWriter.execute(() -> armorSetDao.insert(armorSet));
    }
    public LiveData<ArmorSet> getArmorSet(long id){
        return armorSetDao.selectArmorSet(id);
    }
}
