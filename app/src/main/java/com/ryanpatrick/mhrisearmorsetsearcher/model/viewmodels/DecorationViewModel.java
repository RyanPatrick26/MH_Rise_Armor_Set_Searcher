package com.ryanpatrick.mhrisearmorsetsearcher.model.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Decoration;
import com.ryanpatrick.mhrisearmorsetsearcher.repositories.DecorationRepository;

import java.util.List;

public class DecorationViewModel extends AndroidViewModel {
    private DecorationRepository decorationRepository;
    private LiveData<List<Decoration>> allDecorations;
    Application application;

    public DecorationViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        decorationRepository = new DecorationRepository(application);
        allDecorations = decorationRepository.getDecorationList();
    }
    public LiveData<List<Decoration>> getAllGems(){
        return allDecorations;
    }
    public LiveData<Decoration> getGem(long id){return decorationRepository.getGem(id);}
    public void updateDb(){
        decorationRepository.updateDb();
    }
}
