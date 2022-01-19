package com.ryanpatrick.mhrisearmorsetsearcher.data.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Armor;
import com.ryanpatrick.mhrisearmorsetsearcher.util.enums.ArmorType;
import com.ryanpatrick.mhrisearmorsetsearcher.util.enums.Gender;

import java.util.List;

@Dao
public abstract class ArmorDao {
    @Insert
    public abstract void insert(Armor armor);

    @Insert
    public abstract void insertAll(Armor[] armors);

    @Query("DELETE FROM armor_tbl")
    public abstract void deleteAll();

    @Query("SELECT armor_name, rarity, gender,armor_type, base_defense, " +
            " max_defense, fire_res, water_res, thunder_res, ice_res, dragon_res, " +
            "skills, slots FROM armor_tbl")
    public abstract List<Armor> getArmorList();

    @Query("SELECT * FROM armor_tbl")
    public abstract LiveData<List<Armor>> getAllArmor();

    @Query("SELECT * FROM armor_tbl WHERE armorId == :id")
    public abstract LiveData<Armor> getArmor(long id);

    @Query("SELECT * FROM armor_tbl WHERE armor_type == :armorType & gender == :gender OR 'Both'")
    public abstract LiveData<List<Armor>> getAllArmorOfType(ArmorType armorType, Gender gender);

    @Query("SELECT * FROM armor_tbl WHERE rarity == :rarity & gender == :gender OR 'Both'")
    public abstract LiveData<List<Armor>> getAllArmorOfRarity(int rarity, Gender gender);

    @Transaction
    public void updateDb(Armor[] armors){
        deleteAll();
        insertAll(armors);
    }

}
