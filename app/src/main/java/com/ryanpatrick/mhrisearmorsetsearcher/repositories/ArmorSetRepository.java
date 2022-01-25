package com.ryanpatrick.mhrisearmorsetsearcher.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.ryanpatrick.mhrisearmorsetsearcher.data.ApplicationDatabase;
import com.ryanpatrick.mhrisearmorsetsearcher.data.daos.ArmorSetDao;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.ArmorSet;

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
    public void deleteArmorSet(ArmorSet set){
        ApplicationDatabase.databaseWriter.execute(() -> armorSetDao.delete(set));
    }
}
