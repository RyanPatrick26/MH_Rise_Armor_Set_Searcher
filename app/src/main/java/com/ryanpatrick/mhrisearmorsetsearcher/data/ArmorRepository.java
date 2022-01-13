package com.ryanpatrick.mhrisearmorsetsearcher.data;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.ryanpatrick.mhrisearmorsetsearcher.model.Armor;
import com.ryanpatrick.mhrisearmorsetsearcher.model.Skill;
import com.ryanpatrick.mhrisearmorsetsearcher.model.Slot;
import com.ryanpatrick.mhrisearmorsetsearcher.util.enums.ArmorType;
import com.ryanpatrick.mhrisearmorsetsearcher.util.enums.Gender;

import java.util.ArrayList;
import java.util.List;

public class ArmorRepository {
    private ArmorDao armorDao;
    private LiveData<List<Armor>> armorList;
    private Context dbContext;

    public ArmorRepository(Application application){
        ApplicationDatabase db = ApplicationDatabase.getInstance(application.getApplicationContext());
        dbContext = application.getApplicationContext();
        armorDao = db.armorDao();
        armorList = armorDao.getAllArmor();
        armorList.observeForever(new Observer<List<Armor>>() {
            @Override
            public void onChanged(List<Armor> armors) {
                initializeArmorDb(armors);
                armorList.removeObserver(this);
            }
        });
    }

    public LiveData<List<Armor>> getAllArmor(){return armorList;}
    public void insert(Armor armor){
        ApplicationDatabase.databaseWriter.execute(() -> armorDao.insert(armor));
    }
    public LiveData<Armor> getArmor(long id){
        return armorDao.getArmor(id);
    }
    public LiveData<List<Armor>> getAllArmorOfType(ArmorType type, Gender gender){
        return armorDao.getAllArmorOfType(type, gender);
    }
    public LiveData<List<Armor>> getAllArmorOfRarity(int rarity, Gender gender){
        return armorDao.getAllArmorOfRarity(rarity, gender);
    }
    public void initializeArmorDb(List<Armor> oldArmors){
        ArrayList<Armor> armors = new ArrayList<>();
        /*//region create a list of armor pieces to insert into the database
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        armors.add(new Armor("", 1, Gender.Male, ArmorType.Head,
                1,1,1,1,1,1,
                new Skill[]{},
                new Slot[]{}));
        //endregion*/

        if(oldArmors.size() > 0 && armors.size() >= oldArmors.size()){
            for(int i = oldArmors.size(); i > 0; i--){
                if(armors.get(i-1).getName().equals(oldArmors.get(i-1).getName())){
                    armors.remove(i-1);
                }
            }
        }
        if(armors.size() > 0)
            ApplicationDatabase.databaseWriter.execute(() -> armorDao.batchInsert(armors));
    }
}
