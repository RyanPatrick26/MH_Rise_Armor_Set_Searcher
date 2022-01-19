package com.ryanpatrick.mhrisearmorsetsearcher.repositories;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.ryanpatrick.mhrisearmorsetsearcher.R;
import com.ryanpatrick.mhrisearmorsetsearcher.data.ApplicationDatabase;
import com.ryanpatrick.mhrisearmorsetsearcher.data.daos.SkillDao;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Skill;
import com.ryanpatrick.mhrisearmorsetsearcher.util.DbConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SkillRepository {
    private SkillDao skillDao;
    private LiveData<List<Skill>> skillsList;
    Context dbContext;

    public SkillRepository(Application application){
        ApplicationDatabase db = ApplicationDatabase.getInstance(application.getApplicationContext());
        skillDao = db.skillDao();
        skillsList = skillDao.getAllSkills();
    }

    public LiveData<List<Skill>> getAllSkills(){return skillsList;}
    public LiveData<Skill> getSkill(long id){
        return skillDao.getSkill(id);
    }
    public void insert(Skill skill){
        ApplicationDatabase.databaseWriter.execute(() -> skillDao.insert(skill));
    }
    public void updateDb(){
        if(!skillDao.getSkillsList().equals(Arrays.asList(DbConstants.PREPOPULATE_SKILLS)))
            ApplicationDatabase.databaseWriter.execute(() ->
                    skillDao.updateDb(DbConstants.PREPOPULATE_SKILLS));

    }
}
