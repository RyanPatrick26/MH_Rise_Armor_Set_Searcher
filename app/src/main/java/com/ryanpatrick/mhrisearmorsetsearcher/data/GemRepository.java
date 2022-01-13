package com.ryanpatrick.mhrisearmorsetsearcher.data;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.ryanpatrick.mhrisearmorsetsearcher.R;
import com.ryanpatrick.mhrisearmorsetsearcher.model.Gem;

import java.util.ArrayList;
import java.util.List;

public class GemRepository {
    private GemDao gemDao;
    private LiveData<List<Gem>> gemList;
    private Context dbContext;

    public GemRepository(Application application) {
        ApplicationDatabase db = ApplicationDatabase.getInstance(application);
        gemDao = db.gemDao();
        gemList = gemDao.getAllGems();
        dbContext = application.getApplicationContext();
    }
    public LiveData<List<Gem>> getGemList() {
        return gemList;
    }
    public void insert(Gem gem){
        ApplicationDatabase.databaseWriter.execute(() -> gemDao.insert(gem));
    }
    public LiveData<Gem> getGem(long id){
        return gemDao.getGem(id);
    }
    public void initializeGemDb(){
        ArrayList<Gem> gems = new ArrayList<>();

        gems.add(new Gem(dbContext.getResources().getString(R.string.mastery),
                dbContext.getResources().getString(R.string.masters_touch), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.handicraft),
                dbContext.getResources().getString(R.string.handicraft_skill), 3));
        gems.add(new Gem(dbContext.getResources().getString(R.string.fate),
                dbContext.getResources().getString(R.string.good_luck), 3));
        gems.add(new Gem(dbContext.getResources().getString(R.string.hellfire),
                dbContext.getResources().getString(R.string.hellfire_cloak), 3));
        gems.add(new Gem(dbContext.getResources().getString(R.string.leap),
                dbContext.getResources().getString(R.string.jump_master), 3));
        gems.add(new Gem(dbContext.getResources().getString(R.string.salvo),
                dbContext.getResources().getString(R.string.rapid_fire_up), 3));
        gems.add(new Gem(dbContext.getResources().getString(R.string.thrift),
                dbContext.getResources().getString(R.string.spare_shot), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.razor),
                dbContext.getResources().getString(R.string.razor_sharp), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.spread),
                dbContext.getResources().getString(R.string.spread_up), 3));
        gems.add(new Gem(dbContext.getResources().getString(R.string.pierce),
                dbContext.getResources().getString(R.string.pierce_up), 3));
        gems.add(new Gem(dbContext.getResources().getString(R.string.forceshot),
                dbContext.getResources().getString(R.string.normal_rapid_up), 3));
        gems.add(new Gem(dbContext.getResources().getString(R.string.sharp),
                dbContext.getResources().getString(R.string.protective_polish), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.blast),
                dbContext.getResources().getString(R.string.blast_attack), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.sleep),
                dbContext.getResources().getString(R.string.sleep_attack), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.paralyzer),
                dbContext.getResources().getString(R.string.paralysis_attack), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.throttle),
                dbContext.getResources().getString(R.string.latent_power), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.crisis),
                dbContext.getResources().getString(R.string.resuscitate), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.furor),
                dbContext.getResources().getString(R.string.resentment), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.flawless),
                dbContext.getResources().getString(R.string.peak_performance), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.challenger),
                dbContext.getResources().getString(R.string.agitator), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.tenderizer),
                dbContext.getResources().getString(R.string.weakness_exploit), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.critical),
                dbContext.getResources().getString(R.string.critical_boost), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.capacity),
                dbContext.getResources().getString(R.string.ammo_up), 3));
        gems.add(new Gem(dbContext.getResources().getString(R.string.guardian),
                dbContext.getResources().getString(R.string.offensive_guard), 3));
        gems.add(new Gem(dbContext.getResources().getString(R.string.fungiform),
                dbContext.getResources().getString(R.string.mushroomancer), 3));
        gems.add(new Gem(dbContext.getResources().getString(R.string.draw),
                dbContext.getResources().getString(R.string.critical_draw), 3));
        gems.add(new Gem(dbContext.getResources().getString(R.string.precise),
                dbContext.getResources().getString(R.string.ballistics), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.crit_element),
                dbContext.getResources().getString(R.string.critical_element), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.artillery),
                dbContext.getResources().getString(R.string.artillery_skill), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.sprinter),
                dbContext.getResources().getString(R.string.marathon_runner), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.charger),
                dbContext.getResources().getString(R.string.focus), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.minds_eye),
                dbContext.getResources().getString(R.string.minds_eye_skill), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.potential),
                dbContext.getResources().getString(R.string.latent_power), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.trueshot),
                dbContext.getResources().getString(R.string.special_ammo_boost), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.magazine),
                dbContext.getResources().getString(R.string.load_shells), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.shield),
                dbContext.getResources().getString(R.string.guard_up), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.ironwall),
                dbContext.getResources().getString(R.string.guard), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.refresh),
                dbContext.getResources().getString(R.string.stamina_surge), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.enhancer),
                dbContext.getResources().getString(R.string.power_prolonger), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.mighty),
                dbContext.getResources().getString(R.string.maximum_might), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.attack),
                dbContext.getResources().getString(R.string.attack_boost), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.expert),
                dbContext.getResources().getString(R.string.critical_eye), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.quickswitch),
                dbContext.getResources().getString(R.string.rapid_morph), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.absorber),
                dbContext.getResources().getString(R.string.recoil_down), 1));
        gems.add(new Gem(dbContext.getResources().getString(R.string.blunt),
                dbContext.getResources().getString(R.string.bludgeoner), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.quickload),
                dbContext.getResources().getString(R.string.reload_speed), 1));
        gems.add(new Gem(dbContext.getResources().getString(R.string.steadfast),
                dbContext.getResources().getString(R.string.stun_res), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.footing),
                dbContext.getResources().getString(R.string.tremor_res), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.earplug),
                dbContext.getResources().getString(R.string.earplugs_skill), 3));
        gems.add(new Gem(dbContext.getResources().getString(R.string.counter),
                dbContext.getResources().getString(R.string.counterstrike), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.bubble),
                dbContext.getResources().getString(R.string.bubbly_dance), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.sniper),
                dbContext.getResources().getString(R.string.steadiness), 1));
        gems.add(new Gem(dbContext.getResources().getString(R.string.gambit),
                dbContext.getResources().getString(R.string.punishing_draw), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.jumping),
                dbContext.getResources().getString(R.string.evade_extender), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.evasion),
                dbContext.getResources().getString(R.string.evade_window), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.brace),
                dbContext.getResources().getString(R.string.flinch_free), 1));
        gems.add(new Gem(dbContext.getResources().getString(R.string.sheath),
                dbContext.getResources().getString(R.string.quick_sheathe), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.wind_res),
                dbContext.getResources().getString(R.string.windproof), 1));
        gems.add(new Gem(dbContext.getResources().getString(R.string.ko),
                dbContext.getResources().getString(R.string.slugger), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.friendship),
                dbContext.getResources().getString(R.string.wide_range), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.destroyer),
                dbContext.getResources().getString(R.string.partbreaker), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.resistor),
                dbContext.getResources().getString(R.string.blight_res), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.gobbler),
                dbContext.getResources().getString(R.string.speed_eating), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.protection),
                dbContext.getResources().getString(R.string.divine_blessing), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.wall_run),
                dbContext.getResources().getString(R.string.wall_runner), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.wirebug),
                dbContext.getResources().getString(R.string.wirebug_whisperer), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.grinder),
                dbContext.getResources().getString(R.string.speed_sharpening), 1));
        gems.add(new Gem(dbContext.getResources().getString(R.string.enduring),
                dbContext.getResources().getString(R.string.item_prolonger), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.recovery),
                dbContext.getResources().getString(R.string.recovery_speed), 1));
        gems.add(new Gem(dbContext.getResources().getString(R.string.medicine),
                dbContext.getResources().getString(R.string.recovery_up), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.sonorous),
                dbContext.getResources().getString(R.string.horn_maestro), 1));
        gems.add(new Gem(dbContext.getResources().getString(R.string.drain),
                dbContext.getResources().getString(R.string.stamina_thief), 1));
        gems.add(new Gem(dbContext.getResources().getString(R.string.physique),
                dbContext.getResources().getString(R.string.constitution), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.venom),
                dbContext.getResources().getString(R.string.poison_atk), 1));
        gems.add(new Gem(dbContext.getResources().getString(R.string.slider),
                dbContext.getResources().getString(R.string.affinity_sliding), 1));
        gems.add(new Gem(dbContext.getResources().getString(R.string.bomber),
                dbContext.getResources().getString(R.string.bombardier), 1));
        gems.add(new Gem(dbContext.getResources().getString(R.string.blaze),
                dbContext.getResources().getString(R.string.fire_atk), 1));
        gems.add(new Gem(dbContext.getResources().getString(R.string.stream),
                dbContext.getResources().getString(R.string.water_atk), 1));
        gems.add(new Gem(dbContext.getResources().getString(R.string.bolt),
                dbContext.getResources().getString(R.string.thunder_atk), 1));
        gems.add(new Gem(dbContext.getResources().getString(R.string.dragon),
                dbContext.getResources().getString(R.string.dragon_atk), 1));
        gems.add(new Gem(dbContext.getResources().getString(R.string.defense),
                dbContext.getResources().getString(R.string.def_boost), 1));
        gems.add(new Gem(dbContext.getResources().getString(R.string.carver),
                dbContext.getResources().getString(R.string.carving_pro), 1));
        gems.add(new Gem(dbContext.getResources().getString(R.string.rodeo),
                dbContext.getResources().getString(R.string.master_mounter), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.hungerless),
                dbContext.getResources().getString(R.string.hunger_res), 1));
        gems.add(new Gem(dbContext.getResources().getString(R.string.satiated),
                dbContext.getResources().getString(R.string.free_meal), 1));
        gems.add(new Gem(dbContext.getResources().getString(R.string.muck),
                dbContext.getResources().getString(R.string.muck_res), 1));
        gems.add(new Gem(dbContext.getResources().getString(R.string.diversion),
                dbContext.getResources().getString(R.string.diversion_skill), 1));
        gems.add(new Gem(dbContext.getResources().getString(R.string.dive),
                dbContext.getResources().getString(R.string.leap_of_faith), 1));
        gems.add(new Gem(dbContext.getResources().getString(R.string.fortitude),
                dbContext.getResources().getString(R.string.fortify), 2));
        gems.add(new Gem(dbContext.getResources().getString(R.string.geology),
                dbContext.getResources().getString(R.string.geologist), 1));
        gems.add(new Gem(dbContext.getResources().getString(R.string.botany),
                dbContext.getResources().getString(R.string.botanist), 1));
        gems.add(new Gem(dbContext.getResources().getString(R.string.antiblast),
                dbContext.getResources().getString(R.string.blast_res), 1));
        gems.add(new Gem(dbContext.getResources().getString(R.string.pep),
                dbContext.getResources().getString(R.string.sleep_res), 1));
        gems.add(new Gem(dbContext.getResources().getString(R.string.antipara),
                dbContext.getResources().getString(R.string.para_res), 1));
        gems.add(new Gem(dbContext.getResources().getString(R.string.antitode),
                dbContext.getResources().getString(R.string.poison_res), 1));
        gems.add(new Gem(dbContext.getResources().getString(R.string.fire_res),
                dbContext.getResources().getString(R.string.fire_res_skill), 1));
        gems.add(new Gem(dbContext.getResources().getString(R.string.water_res),
                dbContext.getResources().getString(R.string.water_res_skill), 1));
        gems.add(new Gem(dbContext.getResources().getString(R.string.thunder_res),
                dbContext.getResources().getString(R.string.thunder_res_skill), 1));
        gems.add(new Gem(dbContext.getResources().getString(R.string.ice_res),
                dbContext.getResources().getString(R.string.ice_res_skill), 1));
        gems.add(new Gem(dbContext.getResources().getString(R.string.dragon_res),
                dbContext.getResources().getString(R.string.dragon_res_skill), 1));

        Log.d("here", "initializeGemDb: ");

        gemDao.batchInsert(gems);
    }
}
