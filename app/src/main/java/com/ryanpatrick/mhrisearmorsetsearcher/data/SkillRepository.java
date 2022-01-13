package com.ryanpatrick.mhrisearmorsetsearcher.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.ryanpatrick.mhrisearmorsetsearcher.model.Skill;
import com.ryanpatrick.mhrisearmorsetsearcher.util.Utils;

import java.util.List;

public class SkillRepository {
    private SkillDao skillDao;
    private LiveData<List<Skill>> skillsList;

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
}
