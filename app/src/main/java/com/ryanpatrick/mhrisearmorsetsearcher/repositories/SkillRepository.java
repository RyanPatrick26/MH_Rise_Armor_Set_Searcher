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
    private final SkillDao skillDao;
    private final LiveData<List<Skill>> skillsList;

    public SkillRepository(Application application){
        ApplicationDatabase db = ApplicationDatabase.getInstance(application.getApplicationContext());
        skillDao = db.skillDao();
        skillsList = skillDao.getAllSkills();
    }

    public LiveData<List<Skill>> getAllSkills(){return skillsList;}
    public LiveData<Skill> getSkill(String name){
        return skillDao.getSkill(name);
    }
    public LiveData<List<Skill>> getSkillsOnCharms(){
        return skillDao.getSkillsOnCharms();
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
