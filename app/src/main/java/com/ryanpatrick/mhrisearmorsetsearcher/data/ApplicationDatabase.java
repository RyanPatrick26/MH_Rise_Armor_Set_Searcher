package com.ryanpatrick.mhrisearmorsetsearcher.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.ryanpatrick.mhrisearmorsetsearcher.data.daos.ArmorDao;
import com.ryanpatrick.mhrisearmorsetsearcher.data.daos.ArmorSetDao;
import com.ryanpatrick.mhrisearmorsetsearcher.data.daos.GemDao;
import com.ryanpatrick.mhrisearmorsetsearcher.data.daos.SkillDao;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Armor;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.ArmorSet;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Gem;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Skill;
import com.ryanpatrick.mhrisearmorsetsearcher.util.Convertors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Armor.class, ArmorSet.class, Gem.class, Skill.class}, version = 1)
@TypeConverters({Convertors.class})
public abstract class ApplicationDatabase extends RoomDatabase {
    public static volatile ApplicationDatabase INSTANCE;
    public static final int NUMBER_OF_THREADS = 4;
    public static final String DATABASE_NAME = "set_searcher_db";
    public static final ExecutorService databaseWriter = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static synchronized ApplicationDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ApplicationDatabase.class, DATABASE_NAME)
                    .addCallback(new Callback(){
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            databaseWriter.execute(() ->{
                                ArmorDao armorDao = INSTANCE.armorDao();
                                ArmorSetDao setDao = INSTANCE.setDao();
                                GemDao gemDao = INSTANCE.gemDao();
                                SkillDao skillDao = INSTANCE.skillDao();

                                armorDao.deleteAll();
                                gemDao.deleteAll();
                                setDao.deleteAll();
                                skillDao.deleteAll();
                            });
                        }
                    })
                    .build();
        }
        return INSTANCE;
    }

    public abstract ArmorDao armorDao();
    public abstract ArmorSetDao setDao();
    public abstract GemDao gemDao();
    public abstract SkillDao skillDao();

}
