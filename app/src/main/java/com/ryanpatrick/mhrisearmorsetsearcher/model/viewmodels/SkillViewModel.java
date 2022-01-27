package com.ryanpatrick.mhrisearmorsetsearcher.model.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ryanpatrick.mhrisearmorsetsearcher.repositories.SkillRepository;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Skill;

import java.util.List;

public class SkillViewModel extends AndroidViewModel {
    private final SkillRepository skillRepository;
    private final LiveData<List<Skill>> ALL_SKILLS;

    public SkillViewModel(@NonNull Application application) {
        super(application);
        skillRepository = new SkillRepository(application);
        ALL_SKILLS = skillRepository.getAllSkills();
    }
    public LiveData<List<Skill>> getAllSkills() {
        return ALL_SKILLS;
    }
    public LiveData<Skill> getSkill(String name){return skillRepository.getSkill(name);}
    public LiveData<List<Skill>> getSkillsOnCharms(){ return skillRepository.getSkillsOnCharms();}
    public void updateDb(){
        skillRepository.updateDb();
    }
}
