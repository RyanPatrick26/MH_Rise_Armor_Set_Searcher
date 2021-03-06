package com.ryanpatrick.mhrisearmorsetsearcher.data.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.ArmorSet;

import java.util.List;

@Dao
public interface ArmorSetDao {
    @Insert
    void insert(ArmorSet set);

    @Update
    void update(ArmorSet set);

    @Query("DELETE FROM armor_set_tbl")
    void deleteAll();

    @Query("DELETE FROM armor_set_tbl WHERE id = :id")
    void delete(long id);

    @Query("SELECT * FROM armor_set_tbl")
    LiveData<List<ArmorSet>> selectAllArmorSets();

    @Query("SELECT * FROM armor_set_tbl WHERE id = :id")
    LiveData<ArmorSet> selectArmorSet(long id);
}
