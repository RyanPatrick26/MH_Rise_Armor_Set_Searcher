package com.ryanpatrick.mhrisearmorsetsearcher.model.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ryanpatrick.mhrisearmorsetsearcher.repositories.GemRepository;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Gem;

import java.util.ArrayList;
import java.util.List;

public class GemViewModel extends AndroidViewModel {
    private GemRepository gemRepository;
    private final LiveData<List<Gem>> ALL_GEMS;
    Application application;

    public GemViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        gemRepository = new GemRepository(application);
        ALL_GEMS = gemRepository.getGemList();
    }

    public LiveData<List<Gem>> getAllGems(){
        return ALL_GEMS;
    }
    public LiveData<Gem> getGem(long id){return gemRepository.getGem(id);}
    public void initializeGemDb(ArrayList<Gem> gems){
        gemRepository.initializeGemDb(gems);
    }
}
