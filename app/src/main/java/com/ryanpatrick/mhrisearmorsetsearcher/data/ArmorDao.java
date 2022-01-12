package com.ryanpatrick.mhrisearmorsetsearcher.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ryanpatrick.mhrisearmorsetsearcher.model.Armor;
import com.ryanpatrick.mhrisearmorsetsearcher.util.enums.ArmorType;
import com.ryanpatrick.mhrisearmorsetsearcher.util.enums.Gender;

import java.util.ArrayList;

@Dao
public interface ArmorDao {
    @Insert
    void insert(Armor armor);

    @Query("DELETE FROM armor_tbl")
    void deleteAll();

    @Query("SELECT * FROM armor_tbl WHERE gender = :gender OR 'Both'")
    LiveData<ArrayList<Armor>> getAllArmor(Gender gender);

    @Query("SELECT * FROM armor_tbl WHERE armorId = :id")
    LiveData<Armor> getArmor(long id);

    @Query("SELECT * FROM armor_tbl WHERE armor_type = :armorType & gender = :gender OR 'Both'")
    LiveData<ArrayList<Armor>> getAllArmorOfType(ArmorType armorType, Gender gender);

    @Query("SELECT * FROM armor_tbl WHERE rarity = :rarity & gender = :gender OR 'Both'")
    LiveData<ArrayList<Armor>> getAllArmorOfRarity(int rarity, Gender gender);

    @Update
    void update(Armor armor);
}
