package com.ryanpatrick.mhrisearmorsetsearcher.data.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Charm;

import java.util.List;

@Dao
public interface CharmDao {
    @Insert
    void insert(Charm charm);

    @Update
    void update(Charm charm);

    @Query("SELECT * FROM charm_tbl")
    LiveData<List<Charm>> getAllCharms();

    @Query("SELECT * FROM charm_tbl WHERE charmId = :id")
    LiveData<Charm> getCharm(long id);

    @Delete
    void deleteCharm(Charm charm);

    @Query("DELETE FROM charm_tbl")
    void deleteAll();
}
