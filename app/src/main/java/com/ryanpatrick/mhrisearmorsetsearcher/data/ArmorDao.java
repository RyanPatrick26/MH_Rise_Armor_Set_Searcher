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
import java.util.List;

@Dao
public interface ArmorDao {
    @Insert
    void insert(Armor armor);

    @Insert
    void batchInsert(ArrayList<Armor> armors);

    @Query("DELETE FROM armor_tbl")
    void deleteAll();

    @Query("SELECT * FROM armor_tbl")
    LiveData<List<Armor>> getAllArmor();

    @Query("SELECT * FROM armor_tbl WHERE armorId == :id")
    LiveData<Armor> getArmor(long id);

    @Query("SELECT * FROM armor_tbl WHERE armor_type == :armorType & gender == :gender OR 'Both'")
    LiveData<List<Armor>> getAllArmorOfType(ArmorType armorType, Gender gender);

    @Query("SELECT * FROM armor_tbl WHERE rarity == :rarity & gender == :gender OR 'Both'")
    LiveData<List<Armor>> getAllArmorOfRarity(int rarity, Gender gender);

}
