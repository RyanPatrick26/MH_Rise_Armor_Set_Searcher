package com.ryanpatrick.mhrisearmorsetsearcher.util;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ryanpatrick.mhrisearmorsetsearcher.model.Armor;
import com.ryanpatrick.mhrisearmorsetsearcher.model.ArmorSet;
import com.ryanpatrick.mhrisearmorsetsearcher.model.Gem;
import com.ryanpatrick.mhrisearmorsetsearcher.model.Skill;
import com.ryanpatrick.mhrisearmorsetsearcher.model.Slot;
import com.ryanpatrick.mhrisearmorsetsearcher.util.enums.ArmorType;
import com.ryanpatrick.mhrisearmorsetsearcher.util.enums.Gender;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Convertors {
    @TypeConverter
    public static String fromArmoryType(ArmorType armorType){
        return armorType == null ? null : armorType.name();
    }

    @TypeConverter
    public static ArmorType toArmorType(String armorType){
        return armorType == null ? null : ArmorType.valueOf(armorType);
    }

    @TypeConverter
    public static String fromGender(Gender gender){
        return gender == null ? null : gender.name();
    }

    @TypeConverter
    public static Gender toGender(String gender){
        return gender == null ? null : Gender.valueOf(gender);
    }

    @TypeConverter
    public static String fromSkillList(ArrayList<Skill> skills){
        if(skills == null){
            return "";
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Skill>>(){}.getType();

        return gson.toJson(skills, type);
    }

    @TypeConverter
    public static ArrayList<Skill> toSkillList(String skills){
        if(skills == null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Skill>>(){}.getType();

        return gson.fromJson(skills, type);
    }

    @TypeConverter
    public static String fromArmor(Armor armor){
        if(armor == null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Armor>(){}.getType();

        return gson.toJson(armor, type);
    }

    @TypeConverter
    public static Armor toArmor(String armor){
        if(armor == null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Armor>(){}.getType();

        return gson.fromJson(armor, type);
    }

    @TypeConverter
    public static String fromGem(Gem gem){
        if(gem == null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Gem>(){}.getType();

        return gson.toJson(gem, type);
    }

    @TypeConverter
    public static Gem toGem(String gem){
        if(gem == null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Gem>(){}.getType();

        return gson.fromJson(gem, type);
    }

    @TypeConverter
    public static String fromSlotList(ArrayList<Slot> slots){
        if(slots == null){
            return "";
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Slot>>(){}.getType();

        return gson.toJson(slots, type);
    }

    @TypeConverter
    public static ArrayList<Slot> toSlotList(String slots){
        if(slots == null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Slot>>(){}.getType();

        return gson.fromJson(slots, type);
    }
}
