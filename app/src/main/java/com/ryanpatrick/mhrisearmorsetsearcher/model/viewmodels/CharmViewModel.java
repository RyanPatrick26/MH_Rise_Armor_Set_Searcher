package com.ryanpatrick.mhrisearmorsetsearcher.model.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Charm;
import com.ryanpatrick.mhrisearmorsetsearcher.repositories.CharmRepository;

import java.util.List;

public class CharmViewModel extends AndroidViewModel {
    private CharmRepository repository;
    private LiveData<List<Charm>> charms;

    public CharmViewModel(@NonNull Application application) {
        super(application);
        repository = new CharmRepository(application.getApplicationContext());
        charms = repository.getAllCharms();
    }

    public LiveData<List<Charm>> getAllCharms(){
        return charms;
    }
    public LiveData<Charm> getCharm(long id){
        return repository.getCharm(id);
    }
    public void addCharm(Charm charm){
        repository.insertCharm(charm);
    }
    public void deleteCharm(Charm charm){
        repository.deleteCharm(charm);
    }
    public void updateCharm(Charm charm){
        repository.updateCharm(charm);
    }
    public boolean exists(long id){
        return repository.exists(id);
    }
}
