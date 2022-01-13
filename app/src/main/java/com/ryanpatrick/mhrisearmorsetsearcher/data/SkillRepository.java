package com.ryanpatrick.mhrisearmorsetsearcher.data;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.ryanpatrick.mhrisearmorsetsearcher.R;
import com.ryanpatrick.mhrisearmorsetsearcher.model.Skill;

import java.util.ArrayList;
import java.util.List;

public class SkillRepository {
    private SkillDao skillDao;
    private LiveData<List<Skill>> skillsList;
    Context dbContext;

    public SkillRepository(Application application){
        ApplicationDatabase db = ApplicationDatabase.getInstance(application.getApplicationContext());
        skillDao = db.skillDao();
        skillsList = skillDao.getAllSkills();
        dbContext = application.getApplicationContext();
    }

    public LiveData<List<Skill>> getAllSkills(){return skillsList;}
    public LiveData<Skill> getSkill(long id){
        return skillDao.getSkill(id);
    }
    public void insert(Skill skill){
        ApplicationDatabase.databaseWriter.execute(() -> skillDao.insert(skill));
    }
    public void initializeSkillDb(){
        ArrayList<Skill> skills = new ArrayList<>();

        skills.add(new Skill(dbContext.getResources().getString(R.string.masters_touch), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.handicraft_skill), 5));
        skills.add(new Skill(dbContext.getResources().getString(R.string.good_luck), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.hellfire_cloak), 4));
        skills.add(new Skill(dbContext.getResources().getString(R.string.jump_master), 1));
        skills.add(new Skill(dbContext.getResources().getString(R.string.rapid_fire_up), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.spare_shot), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.razor_sharp), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.spread_up), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.pierce_up), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.normal_rapid_up), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.protective_polish), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.blast_attack), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.sleep_attack), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.paralysis_attack), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.latent_power), 5));
        skills.add(new Skill(dbContext.getResources().getString(R.string.resuscitate), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.resentment), 5));
        skills.add(new Skill(dbContext.getResources().getString(R.string.peak_performance), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.agitator), 5));
        skills.add(new Skill(dbContext.getResources().getString(R.string.weakness_exploit), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.critical_boost), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.ammo_up), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.offensive_guard), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.mushroomancer), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.critical_draw), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.ballistics), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.critical_element), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.artillery_skill), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.marathon_runner), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.focus), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.minds_eye_skill), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.heroics), 5));
        skills.add(new Skill(dbContext.getResources().getString(R.string.special_ammo_boost), 2));
        skills.add(new Skill(dbContext.getResources().getString(R.string.load_shells), 2));
        skills.add(new Skill(dbContext.getResources().getString(R.string.guard_up), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.guard), 5));
        skills.add(new Skill(dbContext.getResources().getString(R.string.stamina_surge), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.power_prolonger), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.maximum_might), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.attack_boost), 7));
        skills.add(new Skill(dbContext.getResources().getString(R.string.critical_eye), 7));
        skills.add(new Skill(dbContext.getResources().getString(R.string.rapid_morph), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.recoil_down), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.bludgeoner), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.reload_speed), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.stun_res), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.tremor_res), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.earplugs_skill), 5));
        skills.add(new Skill(dbContext.getResources().getString(R.string.counterstrike), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.bubbly_dance), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.steadiness), 2));
        skills.add(new Skill(dbContext.getResources().getString(R.string.punishing_draw), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.evade_extender), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.evade_window), 5));
        skills.add(new Skill(dbContext.getResources().getString(R.string.flinch_free), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.quick_sheathe), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.windproof), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.slugger), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.wide_range), 5));
        skills.add(new Skill(dbContext.getResources().getString(R.string.partbreaker), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.blight_res), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.speed_eating), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.wirebug_whisperer), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.divine_blessing), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.wall_runner), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.speed_sharpening), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.item_prolonger), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.recovery_speed), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.recovery_up), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.horn_maestro), 1));
        skills.add(new Skill(dbContext.getResources().getString(R.string.stamina_thief), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.constitution), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.poison_atk), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.affinity_sliding), 1));
        skills.add(new Skill(dbContext.getResources().getString(R.string.bombardier), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.fire_atk), 5));
        skills.add(new Skill(dbContext.getResources().getString(R.string.water_atk), 5));
        skills.add(new Skill(dbContext.getResources().getString(R.string.thunder_atk), 5));
        skills.add(new Skill(dbContext.getResources().getString(R.string.ice_atk), 5));
        skills.add(new Skill(dbContext.getResources().getString(R.string.dragon_atk), 5));
        skills.add(new Skill(dbContext.getResources().getString(R.string.def_boost), 7));
        skills.add(new Skill(dbContext.getResources().getString(R.string.carving_pro), 1));
        skills.add(new Skill(dbContext.getResources().getString(R.string.master_mounter), 1));
        skills.add(new Skill(dbContext.getResources().getString(R.string.hunger_res), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.free_meal), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.muck_res), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.diversion_skill), 1));
        skills.add(new Skill(dbContext.getResources().getString(R.string.leap_of_faith), 1));
        skills.add(new Skill(dbContext.getResources().getString(R.string.fortify), 1));
        skills.add(new Skill(dbContext.getResources().getString(R.string.geologist), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.botanist), 4));
        skills.add(new Skill(dbContext.getResources().getString(R.string.blast_res), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.sleep_res), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.para_res), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.poison_res), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.fire_res_skill), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.water_res_skill), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.thunder_res_skill), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.ice_res_skill), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.dragon_res_skill), 3));
        skills.add(new Skill(dbContext.getResources().getString(R.string.bow_charge_plus), 1));
        skills.add(new Skill(dbContext.getResources().getString(R.string.capture_master), 1));
        skills.add(new Skill(dbContext.getResources().getString(R.string.chameleos_blessing), 4));
        skills.add(new Skill(dbContext.getResources().getString(R.string.kushala_blessing), 4));
        skills.add(new Skill(dbContext.getResources().getString(R.string.teostra_blessing), 4));
        skills.add(new Skill(dbContext.getResources().getString(R.string.dragonheart), 5));
        skills.add(new Skill(dbContext.getResources().getString(R.string.wind_alignment), 5));
        skills.add(new Skill(dbContext.getResources().getString(R.string.thunder_alignment), 5));
        skills.add(new Skill(dbContext.getResources().getString(R.string.stormsoul), 5));

        skillDao.batchInsert(skills);

    }
}
