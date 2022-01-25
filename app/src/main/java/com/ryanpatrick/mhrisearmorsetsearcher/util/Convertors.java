package com.ryanpatrick.mhrisearmorsetsearcher.util;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Armor;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Decoration;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Skill;
import com.ryanpatrick.mhrisearmorsetsearcher.util.enums.ArmorType;
import com.ryanpatrick.mhrisearmorsetsearcher.util.enums.Gender;

import java.lang.reflect.Type;
import java.util.List;

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
    public static String fromSkillArray(Skill[] skills){
        if(skills == null){
            return "";
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Skill[]>(){}.getType();

        return gson.toJson(skills, type);
    }
    @TypeConverter
    public static Skill[]toSkillArray(String skills){
        if(skills == null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Skill[]>(){}.getType();

        return gson.fromJson(skills, type);
    }

    @TypeConverter
    public static String fromTotalSkillList(List<Skill> skills){
        if(skills == null){
            return "";
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Skill>>(){}.getType();

        return gson.toJson(skills, type);
    }
    @TypeConverter
    public static List<Skill> toTotalSkillList(String skills){
        if(skills == null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Skill>>(){}.getType();

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
    public static String fromDecoration(Decoration decoration){
        if(decoration == null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Decoration>(){}.getType();

        return gson.toJson(decoration, type);
    }
    @TypeConverter
    public static Decoration toDecoration(String decoration){
        if(decoration == null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Decoration>(){}.getType();

        return gson.fromJson(decoration, type);
    }

    @TypeConverter
    public static String fromSlotArray(int[] slots){
        if(slots == null){
            return "";
        }
        Gson gson = new Gson();
        Type type = new TypeToken<int[]>(){}.getType();

        return gson.toJson(slots, type);
    }
    @TypeConverter
    public static int[] toSlotArray(String slots){
        if(slots == null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<int[]>(){}.getType();

        return gson.fromJson(slots, type);
    }

    @TypeConverter
    public static String fromSkill(Skill skill){
        if(skill == null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Skill>(){}.getType();

        return gson.toJson(skill, type);
    }
    @TypeConverter
    public static Skill toSkill(String skill){
        if(skill == null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Skill>(){}.getType();

        return gson.fromJson(skill, type);
    }
}
