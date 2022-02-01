package com.ryanpatrick.mhrisearmorsetsearcher.model.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Decoration;
import com.ryanpatrick.mhrisearmorsetsearcher.repositories.DecorationRepository;

import java.util.List;

public class DecorationViewModel extends AndroidViewModel {
    private DecorationRepository decorationRepository;
    private List<Decoration> allDecorations;
    Application application;

    public DecorationViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        decorationRepository = new DecorationRepository(application);
        allDecorations = decorationRepository.getDecorationList();
    }
    public List<Decoration> getAllDecorations(){
        return allDecorations;
    }
    public Decoration getDecoration(long id){return decorationRepository.getDecoration(id);}
    public Decoration getDecoBySkillName(String skillName){
        return decorationRepository.getDecoBySkillName(skillName);
    }
    public void updateDb(){
        decorationRepository.updateDb();
    }
}
