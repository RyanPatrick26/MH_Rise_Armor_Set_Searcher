package com.ryanpatrick.mhrisearmorsetsearcher.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.ryanpatrick.mhrisearmorsetsearcher.model.ArmorSet;
import com.ryanpatrick.mhrisearmorsetsearcher.model.Gem;
import com.ryanpatrick.mhrisearmorsetsearcher.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class GemRepository {
    private GemDao gemDao;
    private LiveData<List<Gem>> gemList;

    public GemRepository(Application application) {
        ApplicationDatabase db = ApplicationDatabase.getInstance(application);
        gemDao = db.gemDao();
        gemList = gemDao.getAllGems();
    }

    public LiveData<List<Gem>> getGemList() {
        return gemList;
    }
    public void insert(Gem gem){
        ApplicationDatabase.databaseWriter.execute(() -> gemDao.insert(gem));
    }
    public LiveData<Gem> getGem(long id){
        return gemDao.getGem(id);
    }
}
