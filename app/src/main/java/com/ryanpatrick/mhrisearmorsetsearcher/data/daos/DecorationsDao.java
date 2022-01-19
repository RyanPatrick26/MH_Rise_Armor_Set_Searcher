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
public abstract class DecorationsDao {
    @Insert
    public abstract void insert(Decoration decoration);

    @Insert
    public abstract void insertAll(Decoration[] decorations);

    @Update
    public abstract void update(Decoration decoration);

    @Query("DELETE FROM decoration_tbl")
    public abstract void deleteAll();

    @Query("SELECT decoration_name, skill_name, decoration_level FROM decoration_tbl")
    public abstract List<Decoration> getDecorationList();

    @Query("SELECT * FROM decoration_tbl")
    public abstract LiveData<List<Decoration>> getAllDecorations();

    @Query("SELECT * FROM decoration_tbl WHERE id = :id")
    public abstract LiveData<Decoration> getDecoration(long id);

    @Transaction
    public void updateDb(Decoration[] decorations){
        deleteAll();
        insertAll(decorations);
    }
}
