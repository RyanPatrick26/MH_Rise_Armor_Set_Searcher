package com.ryanpatrick.mhrisearmorsetsearcher.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.ryanpatrick.mhrisearmorsetsearcher.R;
import com.ryanpatrick.mhrisearmorsetsearcher.adapters.SetListAdapter;
import com.ryanpatrick.mhrisearmorsetsearcher.data.ApplicationDatabase;
import com.ryanpatrick.mhrisearmorsetsearcher.databinding.FragmentSetBuilderBinding;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Armor;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.ArmorSet;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Skill;
import com.ryanpatrick.mhrisearmorsetsearcher.model.viewmodels.ArmorSetViewModel;
import com.ryanpatrick.mhrisearmorsetsearcher.model.viewmodels.ArmorViewModel;
import com.ryanpatrick.mhrisearmorsetsearcher.model.viewmodels.SkillViewModel;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SetBuilderFragment extends Fragment{
    public static final String TAG = "here";
    FragmentSetBuilderBinding binding;
    List<ArmorSet> setList;
    SkillViewModel skillViewModel;
    ArmorViewModel armorViewModel;
    ArmorSetViewModel armorSetViewModel;
    private SetListAdapter adapter;
    SetListAdapter.OnSetClickListener onSetClickListener;

    Skill tempSkill1;
    Skill tempSkill2;
    Skill tempSkill3;

    public SetBuilderFragment() {
        // Required empty public constructor
    }
    public SetBuilderFragment(SetListAdapter.OnSetClickListener onSetClickListener){
        this.onSetClickListener = onSetClickListener;
    }
    public static SetBuilderFragment newInstance() {
        return new SetBuilderFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSetBuilderBinding.inflate(inflater, container, false);
        setList = new ArrayList<>();
        adapter = new SetListAdapter(setList, getContext(), onSetClickListener);
        
        tempSkill1 = new Skill();
        tempSkill2 = new Skill();
        tempSkill3 = new Skill();

        skillViewModel = new ViewModelProvider(requireActivity()).get(SkillViewModel.class);
        armorViewModel = new ViewModelProvider(requireActivity()).get(ArmorViewModel.class);
        armorSetViewModel = new ViewModelProvider(requireActivity()).get(ArmorSetViewModel.class);

        skillViewModel.getAllSkills().observe(getViewLifecycleOwner(), skills -> {
            ArrayList<String> skillNames = new ArrayList<>();
            ArrayList<String> skill1Level = new ArrayList<>();
            ArrayList<String> skill2Level = new ArrayList<>();
            ArrayList<String> skill3Level = new ArrayList<>();

            for (Skill skill : skills) {
                String skillName =  getString(getResources().getIdentifier(skill.getSkillName(), "string", requireActivity().getPackageName()));
                if(skillName.contains("("))
                    skillName = skillName.substring(0, skillName.indexOf("("));
                skillNames.add(skillName);
            }
            ArrayAdapter<String> skillAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, skillNames);
            ArrayAdapter<String> skill1LevelAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, skill1Level);
            ArrayAdapter<String> skill2LevelAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, skill2Level);
            ArrayAdapter<String> skill3LevelAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, skill3Level);

            binding.skill1Spinner.setAdapter(skillAdapter);
            binding.skill1Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Skill skill = skills.get(position);
                    skill1Level.clear();

                    tempSkill1.setSkillName(skill.getSkillName());
                    tempSkill1.setSkillMaxLevel(skill.getSkillMaxLevel());

                    for (int i = 1; i <= skill.getSkillMaxLevel(); i++) {
                        skill1Level.add(Integer.toString(i));
                    }
                    skill1LevelAdapter.notifyDataSetChanged();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            binding.skill2Spinner.setAdapter(skillAdapter);
            binding.skill2Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Skill skill = skills.get(position);
                    skill2Level.clear();

                    tempSkill2.setSkillName(skill.getSkillName());
                    tempSkill2.setSkillMaxLevel(skill.getSkillMaxLevel());

                    for (int i = 1; i <= skill.getSkillMaxLevel(); i++) {
                        skill2Level.add(Integer.toString(i));
                    }

                    skill2LevelAdapter.notifyDataSetChanged();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            binding.skill3Spinner.setAdapter(skillAdapter);
            binding.skill3Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Skill skill = skills.get(position);
                    skill3Level.clear();

                    tempSkill3.setSkillName(skill.getSkillName());
                    tempSkill3.setSkillMaxLevel(skill.getSkillMaxLevel());

                    for (int i = 1; i <= skill.getSkillMaxLevel(); i++) {
                        skill3Level.add(Integer.toString(i));
                    }

                    skill3LevelAdapter.notifyDataSetChanged();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            binding.skill1LevelSpinner.setAdapter(skill1LevelAdapter);
            binding.skill1LevelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (skill1Level.size() > 0) {
                        if (NumberUtils.isParsable(skill1Level.get(position)))
                            tempSkill1.setSkillLevel(Integer.parseInt(skill1Level.get(position)));
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            binding.skill2LevelSpinner.setAdapter(skill2LevelAdapter);
            binding.skill2LevelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(skill2Level.size() > 0) {
                        if (NumberUtils.isParsable(skill2Level.get(position)))
                            tempSkill2.setSkillLevel(Integer.parseInt(skill2Level.get(position)));
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            binding.skill3LevelSpinner.setAdapter(skill3LevelAdapter);
            binding.skill3LevelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(skill3Level.size() > 0) {
                        if (NumberUtils.isParsable(skill3Level.get(position)))
                            tempSkill3.setSkillLevel(Integer.parseInt(skill3Level.get(position)));
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        });

        binding.setBuilderList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.setBuilderList.setAdapter(adapter);
        binding.searchSetButton.setOnClickListener(v -> generateArmorSets());

        return binding.getRoot();
    }

    /**
     * Called when the view previously created by {@link #onCreateView} has
     * been detached from the fragment.  The next time the fragment needs
     * to be displayed, a new view will be created.  This is called
     * after {@link #onStop()} and before {@link #onDestroy()}.  It is called
     * <em>regardless</em> of whether {@link #onCreateView} returned a
     * non-null view.  Internally it is called after the view's state has
     * been saved but before it has been removed from its parent.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        skillViewModel = null;
        armorViewModel = null;
        armorSetViewModel = null;
    }

    //Temp function for generating the armor sets
    //will be replaced when search algorithm is built
    private void generateArmorSets(){
        /*setList.clear();

        ApplicationDatabase.databaseWriter.execute(() -> {
            int totalBaseDefense, totalMaxDefense, totalFireRes, totalWaterRes, totalThunderRes,
                    totalIceRes, totalDragonRes;
            int lvl1Slots = 0;
            int lvl2Slots = 0;
            int lvl3Slots = 0;
            int[] totalSlots;

            //region temp armors
            Armor head1 = armorViewModel.getArmor(1);
            Armor chest1 = armorViewModel.getArmor(7);
            Armor arms1 = armorViewModel.getArmor(13);
            Armor waist1 = armorViewModel.getArmor(19);
            Armor legs1 = armorViewModel.getArmor(25);

            Armor head2 = armorViewModel.getArmor(26);
            Armor chest2 = armorViewModel.getArmor(32);
            Armor arms2 = armorViewModel.getArmor(38);
            Armor waist2 = armorViewModel.getArmor(44);
            Armor legs2 = armorViewModel.getArmor(50);

            Armor head3 = armorViewModel.getArmor(51);
            Armor chest3 = armorViewModel.getArmor(57);
            Armor arms3 = armorViewModel.getArmor(63);
            Armor waist3 = armorViewModel.getArmor(69);
            Armor legs3 = armorViewModel.getArmor(75);
            //endregion

            //region generate temp set 1
            List<Skill> allSkills1 = new ArrayList<>();
            List<Skill> toRemove1 = new ArrayList<>();
            //region calculate total defenses
            totalBaseDefense = head1.getBaseDefense() + chest1.getBaseDefense() + arms1.getBaseDefense()
                    + waist1.getBaseDefense() + legs1.getBaseDefense();
            totalMaxDefense = head1.getMaxDefense() + chest1.getMaxDefense() + arms1.getMaxDefense()
                    + waist1.getMaxDefense() + legs1.getMaxDefense();
            totalFireRes = head1.getFireRes() + chest1.getFireRes() + arms1.getFireRes()
                    + waist1.getFireRes() + legs1.getFireRes();
            totalWaterRes = head1.getWaterRes() + chest1.getWaterRes() + arms1.getWaterRes()
                    + waist1.getWaterRes() + legs1.getWaterRes();
            totalThunderRes = head1.getThunderRes() + chest1.getThunderRes() + arms1.getThunderRes()
                    + waist1.getThunderRes() + legs1.getThunderRes();
            totalIceRes = head1.getIceRes() + chest1.getIceRes() + arms1.getIceRes()
                    + waist1.getIceRes() + legs1.getIceRes();
            totalDragonRes = head1.getDragonRes() + chest1.getDragonRes() + arms1.getDragonRes()
                    + waist1.getDragonRes() + legs1.getDragonRes();
            //endregion

            //region calculate total number of each slot
            for (int slot : head1.getSlots()) {
                switch (slot) {
                    case 1:
                        lvl1Slots++;
                        break;
                    case 2:
                        lvl2Slots++;
                        break;
                    case 3:
                        lvl3Slots++;
                        break;
                    default:
                        break;
                }
            }
            for (int slot : arms1.getSlots()) {
                switch (slot) {
                    case 1:
                        lvl1Slots++;
                        break;
                    case 2:
                        lvl2Slots++;
                        break;
                    case 3:
                        lvl3Slots++;
                        break;
                    default:
                        break;
                }
            }
            for (int slot : chest1.getSlots()) {
                switch (slot) {
                    case 1:
                        lvl1Slots++;
                        break;
                    case 2:
                        lvl2Slots++;
                        break;
                    case 3:
                        lvl3Slots++;
                        break;
                    default:
                        break;
                }
            }
            for (int slot : waist1.getSlots()) {
                switch (slot) {
                    case 1:
                        lvl1Slots++;
                        break;
                    case 2:
                        lvl2Slots++;
                        break;
                    case 3:
                        lvl3Slots++;
                        break;
                    default:
                        break;
                }
            }
            for (int slot : legs1.getSlots()) {
                switch (slot) {
                    case 1:
                        lvl1Slots++;
                        break;
                    case 2:
                        lvl2Slots++;
                        break;
                    case 3:
                        lvl3Slots++;
                        break;
                    default:
                        break;
                }
            }

            totalSlots = new int[]{lvl1Slots, lvl2Slots, lvl3Slots};
            //endregion

            //region determine all of the skills and skill levels of the set
            allSkills1.addAll(Arrays.asList(head1.getSkills()));
            allSkills1.addAll(Arrays.asList(chest1.getSkills()));
            allSkills1.addAll(Arrays.asList(arms1.getSkills()));
            allSkills1.addAll(Arrays.asList(waist1.getSkills()));
            allSkills1.addAll(Arrays.asList(legs1.getSkills()));


            for (int i = 0; i < allSkills1.size(); i++) {
                if(!toRemove1.contains(allSkills1.get(i))){
                    for (int j = i+1; j < allSkills1.size(); j++) {
                        if(allSkills1.get(i).getSkillName().equals(allSkills1.get(j).getSkillName())){
                            toRemove1.add(allSkills1.get(j));
                        }
                    }
                }
            }
            allSkills1.removeAll(toRemove1);

            for (Skill skill1 : allSkills1) {
                for (Skill skill2 : toRemove1) {
                    if (skill1.getSkillName().equals(skill2.getSkillName()))
                        skill1.setSkillLevel(skill1.getSkillLevel() + skill2.getSkillLevel());
                }
            }

            allSkills1.sort((o1, o2) -> {
                int result = o2.getSkillLevel() - o1.getSkillLevel();
                if(result != 0){
                    return result;
                }
                result = o1.getSkillName()
                        .compareTo(o2.getSkillName());
                return result;
            });
            //endregion

            ArmorSet set1 = new ArmorSet(head1, chest1, arms1, waist1, legs1,
                    totalBaseDefense, totalMaxDefense, totalFireRes, totalWaterRes,
                    totalThunderRes, totalIceRes, totalDragonRes, allSkills1, totalSlots);
            //endregion
            //region generate temp set 2
            List<Skill> allSkills2 = new ArrayList<>();
            List<Skill> toRemove2 = new ArrayList<>();
            //region calculate total defenses
            totalBaseDefense = head2.getBaseDefense() + chest2.getBaseDefense() + arms2.getBaseDefense()
                    + waist2.getBaseDefense() + legs2.getBaseDefense();
            totalMaxDefense = head2.getMaxDefense() + chest2.getMaxDefense() + arms2.getMaxDefense()
                    + waist2.getMaxDefense() + legs2.getMaxDefense();
            totalFireRes = head2.getFireRes() + chest2.getFireRes() + arms2.getFireRes()
                    + waist2.getFireRes() + legs2.getFireRes();
            totalWaterRes = head2.getWaterRes() + chest2.getWaterRes() + arms2.getWaterRes()
                    + waist2.getWaterRes() + legs2.getWaterRes();
            totalThunderRes = head2.getThunderRes() + chest2.getThunderRes() + arms2.getThunderRes()
                    + waist2.getThunderRes() + legs2.getThunderRes();
            totalIceRes = head2.getIceRes() + chest2.getIceRes() + arms2.getIceRes()
                    + waist2.getIceRes() + legs2.getIceRes();
            totalDragonRes = head2.getDragonRes() + chest2.getDragonRes() + arms2.getDragonRes()
                    + waist2.getDragonRes() + legs2.getDragonRes();
            //endregion

            //region calculate total number of each slot
            for (int slot : head2.getSlots()) {
                switch (slot) {
                    case 1:
                        lvl1Slots++;
                        break;
                    case 2:
                        lvl2Slots++;
                        break;
                    case 3:
                        lvl3Slots++;
                        break;
                    default:
                        break;
                }
            }
            for (int slot : arms2.getSlots()) {
                switch (slot) {
                    case 1:
                        lvl1Slots++;
                        break;
                    case 2:
                        lvl2Slots++;
                        break;
                    case 3:
                        lvl3Slots++;
                        break;
                    default:
                        break;
                }
            }
            for (int slot : chest2.getSlots()) {
                switch (slot) {
                    case 1:
                        lvl1Slots++;
                        break;
                    case 2:
                        lvl2Slots++;
                        break;
                    case 3:
                        lvl3Slots++;
                        break;
                    default:
                        break;
                }
            }
            for (int slot : waist2.getSlots()) {
                switch (slot) {
                    case 1:
                        lvl1Slots++;
                        break;
                    case 2:
                        lvl2Slots++;
                        break;
                    case 3:
                        lvl3Slots++;
                        break;
                    default:
                        break;
                }
            }
            for (int slot : legs2.getSlots()) {
                switch (slot) {
                    case 1:
                        lvl1Slots++;
                        break;
                    case 2:
                        lvl2Slots++;
                        break;
                    case 3:
                        lvl3Slots++;
                        break;
                    default:
                        break;
                }
            }

            totalSlots = new int[]{lvl1Slots, lvl2Slots, lvl3Slots};
            //endregion

            //region determine all of the skills and skill levels of the set
            allSkills2.addAll(Arrays.asList(head2.getSkills()));
            allSkills2.addAll(Arrays.asList(chest2.getSkills()));
            allSkills2.addAll(Arrays.asList(arms2.getSkills()));
            allSkills2.addAll(Arrays.asList(waist2.getSkills()));
            allSkills2.addAll(Arrays.asList(legs2.getSkills()));


            for (int i = 0; i < allSkills2.size(); i++) {
                if(!toRemove2.contains(allSkills2.get(i))){
                    for (int j = i+1; j < allSkills2.size(); j++) {
                        if(allSkills2.get(i).getSkillName().equals(allSkills2.get(j).getSkillName())){
                            toRemove2.add(allSkills2.get(j));
                        }
                    }
                }
            }
            allSkills2.removeAll(toRemove2);

            for (Skill skill1 : allSkills2) {
                for (Skill skill2 : toRemove2) {
                    if (skill1.getSkillName().equals(skill2.getSkillName()))
                        skill1.setSkillLevel(skill1.getSkillLevel() + skill2.getSkillLevel());
                }
            }

            allSkills2.sort((o1, o2) -> {
                int result = o2.getSkillLevel() - o1.getSkillLevel();
                if(result != 0){
                    return result;
                }
                result = o1.getSkillName()
                        .compareTo(o2.getSkillName());
                return result;
            });
            //endregion

            ArmorSet set2 = new ArmorSet(head2, chest2, arms2, waist2, legs2,
                    totalBaseDefense, totalMaxDefense, totalFireRes, totalWaterRes,
                    totalThunderRes, totalIceRes, totalDragonRes, allSkills2, totalSlots);
            //endregion
            //region generate temp set 3
            List<Skill> allSkills3 = new ArrayList<>();
            List<Skill> toRemove = new ArrayList<>();
            //region calculate total defenses
            totalBaseDefense = head3.getBaseDefense() + chest3.getBaseDefense() + arms3.getBaseDefense()
                    + waist3.getBaseDefense() + legs3.getBaseDefense();
            totalMaxDefense = head3.getMaxDefense() + chest3.getMaxDefense() + arms3.getMaxDefense()
                    + waist3.getMaxDefense() + legs3.getMaxDefense();
            totalFireRes = head3.getFireRes() + chest3.getFireRes() + arms3.getFireRes()
                    + waist3.getFireRes() + legs3.getFireRes();
            totalWaterRes = head3.getWaterRes() + chest3.getWaterRes() + arms3.getWaterRes()
                    + waist3.getWaterRes() + legs3.getWaterRes();
            totalThunderRes = head3.getThunderRes() + chest3.getThunderRes() + arms3.getThunderRes()
                    + waist3.getThunderRes() + legs3.getThunderRes();
            totalIceRes = head3.getIceRes() + chest3.getIceRes() + arms3.getIceRes()
                    + waist3.getIceRes() + legs3.getIceRes();
            totalDragonRes = head3.getDragonRes() + chest3.getDragonRes() + arms3.getDragonRes()
                    + waist3.getDragonRes() + legs3.getDragonRes();
            //endregion

            //region calculate total number of each slot
            for (int slot : head3.getSlots()) {
                switch (slot) {
                    case 1:
                        lvl1Slots++;
                        break;
                    case 2:
                        lvl2Slots++;
                        break;
                    case 3:
                        lvl3Slots++;
                        break;
                    default:
                        break;
                }
            }
            for (int slot : arms3.getSlots()) {
                switch (slot) {
                    case 1:
                        lvl1Slots++;
                        break;
                    case 2:
                        lvl2Slots++;
                        break;
                    case 3:
                        lvl3Slots++;
                        break;
                    default:
                        break;
                }
            }
            for (int slot : chest3.getSlots()) {
                switch (slot) {
                    case 1:
                        lvl1Slots++;
                        break;
                    case 2:
                        lvl2Slots++;
                        break;
                    case 3:
                        lvl3Slots++;
                        break;
                    default:
                        break;
                }
            }
            for (int slot : waist3.getSlots()) {
                switch (slot) {
                    case 1:
                        lvl1Slots++;
                        break;
                    case 2:
                        lvl2Slots++;
                        break;
                    case 3:
                        lvl3Slots++;
                        break;
                    default:
                        break;
                }
            }
            for (int slot : legs3.getSlots()) {
                switch (slot) {
                    case 1:
                        lvl1Slots++;
                        break;
                    case 2:
                        lvl2Slots++;
                        break;
                    case 3:
                        lvl3Slots++;
                        break;
                    default:
                        break;
                }
            }

            totalSlots = new int[]{lvl1Slots, lvl2Slots, lvl3Slots};
            //endregion

            //region determine all of the skills and skill levels of the set
            allSkills3.addAll(Arrays.asList(head3.getSkills()));
            allSkills3.addAll(Arrays.asList(chest3.getSkills()));
            allSkills3.addAll(Arrays.asList(arms3.getSkills()));
            allSkills3.addAll(Arrays.asList(waist3.getSkills()));
            allSkills3.addAll(Arrays.asList(legs3.getSkills()));


            for (int i = 0; i < allSkills3.size(); i++) {
                if(!toRemove.contains(allSkills3.get(i))){
                    for (int j = i+1; j < allSkills3.size(); j++) {
                        if(allSkills3.get(i).getSkillName().equals(allSkills3.get(j).getSkillName())){
                            toRemove.add(allSkills3.get(j));
                        }
                    }
                }
            }
            allSkills3.removeAll(toRemove);

            for (Skill skill3 : allSkills3) {
                for (Skill skill2 : toRemove) {
                    if (skill3.getSkillName().equals(skill2.getSkillName()))
                        skill3.setSkillLevel(skill3.getSkillLevel() + skill2.getSkillLevel());
                }
            }

            allSkills3.sort((o3, o2) -> {
                int result = o2.getSkillLevel() - o3.getSkillLevel();
                if(result != 0){
                    return result;
                }
                result = o3.getSkillName()
                        .compareTo(o2.getSkillName());
                return result;
            });
            //endregion

            ArmorSet set3 = new ArmorSet(head3, chest3, arms3, waist3, legs3,
                    totalBaseDefense, totalMaxDefense, totalFireRes, totalWaterRes,
                    totalThunderRes, totalIceRes, totalDragonRes, allSkills3, totalSlots);
            //endregion

            setList.add(set1);
            setList.add(set2);
            setList.add(set3);

            ApplicationDatabase.dbHandler.post(() -> adapter.notifyDataSetChanged());

        });*/

    }

}