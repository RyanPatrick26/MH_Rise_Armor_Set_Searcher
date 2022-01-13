package com.ryanpatrick.mhrisearmorsetsearcher.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ryanpatrick.mhrisearmorsetsearcher.data.SkillRepository;

import java.util.List;

public class SkillViewModel extends AndroidViewModel {
    private SkillRepository skillRepository;
    private final LiveData<List<Skill>> ALL_SKILLS;

    public SkillViewModel(@NonNull Application application) {
        super(application);
        skillRepository = new SkillRepository(application);
        ALL_SKILLS = skillRepository.getAllSkills();
    }

    public LiveData<List<Skill>> getAllSkills() {
        return ALL_SKILLS;
    }
    public LiveData<Skill> getSkill(long id){return skillRepository.getSkill(id);}
}
