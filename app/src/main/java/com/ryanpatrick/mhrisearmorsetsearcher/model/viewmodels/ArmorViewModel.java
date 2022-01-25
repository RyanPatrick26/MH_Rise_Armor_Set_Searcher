package com.ryanpatrick.mhrisearmorsetsearcher.model.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ryanpatrick.mhrisearmorsetsearcher.repositories.ArmorRepository;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Armor;
import com.ryanpatrick.mhrisearmorsetsearcher.util.enums.ArmorType;
import com.ryanpatrick.mhrisearmorsetsearcher.util.enums.Gender;

import java.util.List;

public class ArmorViewModel extends AndroidViewModel {
    private ArmorRepository armorRepository;
    private final LiveData<List<Armor>> ALL_ARMOR;

    public ArmorViewModel(@NonNull Application application) {
        super(application);
        armorRepository = new ArmorRepository(application);
        ALL_ARMOR = armorRepository.getAllArmor();
    }
    public LiveData<List<Armor>> getAllArmor(){
        return ALL_ARMOR;
    }
    public LiveData<List<Armor>> getAllArmorOfType(ArmorType armorType, Gender gender){return armorRepository.getAllArmorOfType(armorType, gender);}
    public LiveData<List<Armor>> getAllArmorOfRarity(int rarity, Gender gender){return armorRepository.getAllArmorOfRarity(rarity, gender);}
    public Armor getArmor(long id){return armorRepository.getArmor(id);}
    public void updateDb(){
        armorRepository.updateDb();
    }
}
