package com.ryanpatrick.mhrisearmorsetsearcher.repositories;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.ryanpatrick.mhrisearmorsetsearcher.data.ApplicationDatabase;
import com.ryanpatrick.mhrisearmorsetsearcher.data.daos.DecorationsDao;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Decoration;
import com.ryanpatrick.mhrisearmorsetsearcher.util.DbConstants;

import java.util.Arrays;
import java.util.List;

public class DecorationRepository {
    private final DecorationsDao decorationsDao;
    private final LiveData<List<Decoration>> decorationsList;

    public DecorationRepository(Application application) {
        ApplicationDatabase db = ApplicationDatabase.getInstance(application);
        decorationsDao = db.gemDao();
        decorationsList = decorationsDao.getAllDecorations();
    }
    public LiveData<List<Decoration>> getDecorationList() {
        return decorationsList;
    }
    public void insert(Decoration decoration){
        ApplicationDatabase.databaseWriter.execute(() -> decorationsDao.insert(decoration));
    }
    public LiveData<Decoration> getGem(long id){
        return decorationsDao.getDecoration(id);
    }
    public void updateDb(){
        ApplicationDatabase.databaseWriter.execute(() ->{
            if(decorationsDao.getDecorationList().size() < DbConstants.prepopulateDecorations.length &&
                    decorationsDao.getDecorationList().size() > 0) {
                List<Decoration> updateList = Arrays.asList(DbConstants.prepopulateDecorations);
                updateList.removeAll(updateList.subList(0, decorationsDao.getDecorationList().size()));
                decorationsDao.insertAll((Decoration[]) updateList.toArray());
            }
        });
    }
}
