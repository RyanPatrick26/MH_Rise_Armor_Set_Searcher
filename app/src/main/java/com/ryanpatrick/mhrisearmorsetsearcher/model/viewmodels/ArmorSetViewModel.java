package com.ryanpatrick.mhrisearmorsetsearcher.model.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Armor;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.ArmorSet;
import com.ryanpatrick.mhrisearmorsetsearcher.repositories.ArmorSetRepository;

import java.util.List;

public class ArmorSetViewModel extends AndroidViewModel{
    private ArmorSetRepository armorSetRepository;
    private final LiveData<List<ArmorSet>> ALL_ARMOR_SETS;
    private ArmorSet tempSet;

    public ArmorSetViewModel(@NonNull Application application) {
        super(application);
        armorSetRepository = new ArmorSetRepository(application);
        ALL_ARMOR_SETS = armorSetRepository.getAllArmorSets();
    }

    public LiveData<List<ArmorSet>> getAllArmorSets() {
        return ALL_ARMOR_SETS;
    }
    public LiveData<ArmorSet> getArmorSet(long id){
        return armorSetRepository.getArmorSet(id);
    }
    public void insertArmorSet(ArmorSet set){
        armorSetRepository.insert(set);
    }
    public void updateArmorSet(ArmorSet set){
        armorSetRepository.update(set);
    }
    public boolean exists(long id){
        return armorSetRepository.exists(id);
    }
    public void deleteArmorSet(ArmorSet set){
        armorSetRepository.deleteArmorSet(set);
    }

    public ArmorSet getTempSet() {
        return tempSet;
    }

    public void setTempSet(ArmorSet tempSet) {
        this.tempSet = tempSet;
    }
}
