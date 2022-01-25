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
public interface SkillDao {
    @Insert
    void insert(Skill skill);

    @Insert
    void insertAll(Skill[] skills);

    @Update
    void update(Skill skill);

    @Query("SELECT * FROM skills_tbl ORDER BY skill_name ASC")
    LiveData<List<Skill>> getAllSkills();

    @Query("SELECT * FROM skills_tbl")
    List<Skill> getSkillsList();

    @Query("SELECT * FROM skills_tbl WHERE skillId == :id")
    LiveData<Skill> getSkillById(long id);

    @Query("DELETE FROM skills_tbl")
    void deleteAll();
}
