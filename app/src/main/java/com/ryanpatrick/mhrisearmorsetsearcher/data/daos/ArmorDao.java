package com.ryanpatrick.mhrisearmorsetsearcher.data.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Armor;
import com.ryanpatrick.mhrisearmorsetsearcher.util.enums.ArmorType;
import com.ryanpatrick.mhrisearmorsetsearcher.util.enums.Gender;

import java.util.List;

@Dao
public interface ArmorDao {
    @Insert
    void insert(Armor armor);

    @Insert
    void insertAll(Armor[] armors);

    @Query("DELETE FROM armor_tbl")
    void deleteAll();

    @Query("SELECT * FROM armor_tbl")
    List<Armor> getArmorList();

    @Query("SELECT * FROM armor_tbl ORDER BY rarity DESC, armorId ASC")
    LiveData<List<Armor>> getAllArmor();

    @Query("SELECT * FROM armor_tbl WHERE armorId == :id")
    Armor getArmor(long id);

    @Query("SELECT * FROM armor_tbl WHERE armor_type == :armorType AND gender IN (:gender, 'Both')" +
            "ORDER BY rarity DESC, armorId ASC")
    LiveData<List<Armor>> getAllArmorOfType(ArmorType armorType, Gender gender);

    @Query("SELECT * FROM armor_tbl WHERE rarity IN (:rarity) AND gender IN (:gender, 'Both')")
    List<Armor> getAllArmorOfRarity(int[] rarity, Gender gender);
}
