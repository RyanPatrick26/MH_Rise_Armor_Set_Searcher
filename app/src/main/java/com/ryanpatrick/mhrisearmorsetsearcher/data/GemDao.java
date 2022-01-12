package com.ryanpatrick.mhrisearmorsetsearcher.data;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ryanpatrick.mhrisearmorsetsearcher.model.Gem;

import java.util.ArrayList;

public interface GemDao {
    @Insert
    void insert(Gem gem);

    @Update
    void update(Gem gem);

    @Query("DELETE FROM gem_tbl")
    void deleteAll();

    @Query("SELECT * FROM gem_tbl")
    LiveData<ArrayList<Gem>> getAllGems();

    @Query("SELECT * FROM gem_tbl WHERE id = :id")
    LiveData<Gem> getGem(long id);
}
