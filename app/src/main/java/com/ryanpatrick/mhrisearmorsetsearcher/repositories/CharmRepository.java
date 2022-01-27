package com.ryanpatrick.mhrisearmorsetsearcher.repositories;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.ryanpatrick.mhrisearmorsetsearcher.App;
import com.ryanpatrick.mhrisearmorsetsearcher.data.ApplicationDatabase;
import com.ryanpatrick.mhrisearmorsetsearcher.data.daos.CharmDao;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Charm;

import java.util.List;

public class CharmRepository {
    private final CharmDao charmDao;
    private final LiveData<List<Charm>> charms;

    public CharmRepository(Context context){
        ApplicationDatabase db = ApplicationDatabase.getInstance(context);
        charmDao = db.charmDao();
        charms = charmDao.getAllCharms();
    }

    public LiveData<List<Charm>> getAllCharms(){
        return charms;
    }

    public LiveData<Charm> getCharm(long id){
        return charmDao.getCharm(id);
    }
    public void insertCharm(Charm charm){
        ApplicationDatabase.databaseWriter.execute(() -> charmDao.insert(charm));
    }
    public void updateCharm(Charm charm){
        ApplicationDatabase.databaseWriter.execute(() -> charmDao.update(charm));
    }
    public void deleteCharm(Charm charm){
        ApplicationDatabase.databaseWriter.execute(() -> charmDao.deleteCharm(charm));
    }
    public boolean exists(long id){
        return charmDao.exists(id);
    }
}
