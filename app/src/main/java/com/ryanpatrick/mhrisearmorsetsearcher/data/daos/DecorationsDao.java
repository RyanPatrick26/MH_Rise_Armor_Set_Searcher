package com.ryanpatrick.mhrisearmorsetsearcher.data.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Decoration;

import java.util.List;

@Dao
public interface DecorationsDao {
    @Insert
    void insert(Decoration decoration);

    @Insert
    void insertAll(Decoration[] decorations);

    @Update
    void update(Decoration decoration);

    @Query("DELETE FROM decoration_tbl")
    void deleteAll();

    @Query("SELECT * FROM decoration_tbl")
    List<Decoration> getDecorationList();

    @Query("SELECT * FROM decoration_tbl")
    LiveData<List<Decoration>> getAllDecorations();

    @Query("SELECT * FROM decoration_tbl WHERE id = :id")
    LiveData<Decoration> getDecoration(long id);
}
