package com.ryanpatrick.mhrisearmorsetsearcher.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.ryanpatrick.mhrisearmorsetsearcher.data.ApplicationDatabase;
import com.ryanpatrick.mhrisearmorsetsearcher.data.daos.DecorationsDao;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Decoration;
import com.ryanpatrick.mhrisearmorsetsearcher.util.Constants;

import java.util.Arrays;
import java.util.List;

public class DecorationRepository {
    private final DecorationsDao decorationsDao;
    private final List<Decoration> decorationsList;

    public DecorationRepository(Context context) {
        ApplicationDatabase db = ApplicationDatabase.getInstance(context);
        decorationsDao = db.gemDao();
        decorationsList = decorationsDao.getAllDecorations();
    }
    public List<Decoration> getAllDecorations(){
        return decorationsList;
    }
    public List<Decoration> getDecorationList() {
        return decorationsDao.getDecorationList();
    }
    public void insert(Decoration decoration){
        ApplicationDatabase.databaseWriter.execute(() -> decorationsDao.insert(decoration));
    }
    public Decoration getDecoration(long id){
        return decorationsDao.getDecoration(id);
    }
    public Decoration getDecoBySkillName(String skillName){
        return decorationsDao.getDecoBySkillName(skillName);
    }
    public void updateDb(){
        ApplicationDatabase.databaseWriter.execute(() ->{
            if(decorationsDao.getDecorationList().size() < Constants.prepopulateDecorations.length &&
                    decorationsDao.getDecorationList().size() > 0) {
                List<Decoration> updateList = Arrays.asList(Constants.prepopulateDecorations);
                updateList.removeAll(updateList.subList(0, decorationsDao.getDecorationList().size()));
                decorationsDao.insertAll((Decoration[]) updateList.toArray());
            }
        });
    }
}
