package com.ryanpatrick.mhrisearmorsetsearcher.data.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Gem;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface GemDao {
    @Insert
    void insert(Gem gem);

    @Insert
    void batchInsert(ArrayList<Gem> gems);

    @Update
    void update(Gem gem);

    @Query("DELETE FROM gem_tbl")
    void deleteAll();

    @Query("SELECT * FROM gem_tbl")
    LiveData<List<Gem>> getAllGems();

    @Query("SELECT * FROM gem_tbl WHERE id = :id")
    LiveData<Gem> getGem(long id);
}
