package com.ryanpatrick.mhrisearmorsetsearcher.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ryanpatrick.mhrisearmorsetsearcher.model.Skill;

import java.util.ArrayList;

@Dao
public interface SkillDao {
    @Insert
    void insert(Skill skill);

    @Insert
    void batchInsert(ArrayList<Skill> skills);

    @Update
    void update(Skill skill);

    @Query("SELECT * FROM skills_tbl")
    LiveData<ArrayList<Skill>> getAllSkills();

    @Query("SELECT * FROM skills_tbl WHERE skillId == :id")
    LiveData<Skill> getSkill(long id);
}
