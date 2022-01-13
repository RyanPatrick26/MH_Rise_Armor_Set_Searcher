package com.ryanpatrick.mhrisearmorsetsearcher.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ryanpatrick.mhrisearmorsetsearcher.data.ApplicationDatabase;
import com.ryanpatrick.mhrisearmorsetsearcher.data.GemRepository;

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
}
