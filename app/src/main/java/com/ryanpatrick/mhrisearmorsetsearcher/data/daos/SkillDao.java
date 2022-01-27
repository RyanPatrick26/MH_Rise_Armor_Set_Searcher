package com.ryanpatrick.mhrisearmorsetsearcher.data.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Skill;

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

    @Query("SELECT * FROM skills_tbl WHERE on_charm == 1 ORDER BY skill_name ASC")
    LiveData<List<Skill>> getSkillsOnCharms();

    @Query("SELECT * FROM skills_tbl WHERE skill_name == :name")
    LiveData<Skill> getSkill(String name);

    @Query("DELETE FROM skills_tbl")
    void deleteAll();
}
