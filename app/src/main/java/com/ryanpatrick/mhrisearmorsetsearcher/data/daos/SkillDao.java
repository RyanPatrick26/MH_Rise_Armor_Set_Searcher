package com.ryanpatrick.mhrisearmorsetsearcher.data.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Skill;

import java.util.ArrayList;
import java.util.List;

@Dao
public abstract class SkillDao {
    @Insert
    public abstract void insert(Skill skill);

    @Insert
    public abstract void insertAll(Skill[] skills);

    @Update
    public abstract void update(Skill skill);

    @Query("SELECT * FROM skills_tbl")
    public abstract LiveData<List<Skill>> getAllSkills();

    @Query("SELECT * FROM skills_tbl")
    public abstract List<Skill> getSkillsList();

    @Query("SELECT * FROM skills_tbl WHERE skillId == :id")
    public abstract LiveData<Skill> getSkill(long id);

    @Query("DELETE FROM skills_tbl")
    public abstract void deleteAll();

    @Transaction
    public void updateDb(Skill[] skills){
        deleteAll();
        insertAll(skills);
    }
}
