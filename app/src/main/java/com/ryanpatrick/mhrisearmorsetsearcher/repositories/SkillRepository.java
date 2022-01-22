package com.ryanpatrick.mhrisearmorsetsearcher.repositories;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.ryanpatrick.mhrisearmorsetsearcher.data.ApplicationDatabase;
import com.ryanpatrick.mhrisearmorsetsearcher.data.daos.SkillDao;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Skill;
import com.ryanpatrick.mhrisearmorsetsearcher.util.DbConstants;

import java.util.Arrays;
import java.util.List;

public class SkillRepository {
    private SkillDao skillDao;
    private LiveData<List<Skill>> skillsList;
    Context dbContext;
    DbConstants values;

    public SkillRepository(Application application){
        ApplicationDatabase db = ApplicationDatabase.getInstance(application.getApplicationContext());
        skillDao = db.skillDao();
        skillsList = skillDao.getAllSkills();
        values = new DbConstants();
    }

    public LiveData<List<Skill>> getAllSkills(){return skillsList;}
    public LiveData<Skill> getSkill(long id){
        return skillDao.getSkillById(id);
    }
    public void insert(Skill skill){
        ApplicationDatabase.databaseWriter.execute(() -> skillDao.insert(skill));
    }
    public void updateDb(){
        ApplicationDatabase.databaseWriter.execute(() -> {
            if (skillDao.getSkillsList().size() < DbConstants.prepopulateSkills.length) {
                List<Skill> updateList = Arrays.asList(DbConstants.prepopulateSkills);
                updateList.removeAll(updateList.subList(0, skillDao.getSkillsList().size()));
                skillDao.insertAll((Skill[]) updateList.toArray());
            }
        });

    }
}
