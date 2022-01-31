package com.ryanpatrick.mhrisearmorsetsearcher.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkContinuation;
import androidx.work.WorkManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.ryanpatrick.mhrisearmorsetsearcher.R;
import com.ryanpatrick.mhrisearmorsetsearcher.adapters.SetListAdapter;
import com.ryanpatrick.mhrisearmorsetsearcher.databinding.FragmentSetBuilderBinding;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.ArmorSet;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Skill;
import com.ryanpatrick.mhrisearmorsetsearcher.model.viewmodels.ArmorSetViewModel;
import com.ryanpatrick.mhrisearmorsetsearcher.model.viewmodels.ArmorViewModel;
import com.ryanpatrick.mhrisearmorsetsearcher.model.viewmodels.SkillViewModel;
import com.ryanpatrick.mhrisearmorsetsearcher.util.Constants;
import com.ryanpatrick.mhrisearmorsetsearcher.util.Convertors;
import com.ryanpatrick.mhrisearmorsetsearcher.workers.DatabaseWorker;
import com.ryanpatrick.mhrisearmorsetsearcher.workers.SetBuilderWorker;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SetBuilderFragment extends Fragment{
    //region class variables
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

    String gender = "";

    int slot1, slot2, slot3;

    boolean rarity4, rarity5, rarity6, rarity7;
    //endregion

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
        //region set variables for the fragment
        binding = FragmentSetBuilderBinding.inflate(inflater, container, false);
        setList = new ArrayList<>();
        adapter = new SetListAdapter(setList, getContext(), onSetClickListener);
        
        tempSkill1 = new Skill();
        tempSkill2 = new Skill();
        tempSkill3 = new Skill();

        skillViewModel = new ViewModelProvider(requireActivity()).get(SkillViewModel.class);
        armorViewModel = new ViewModelProvider(requireActivity()).get(ArmorViewModel.class);
        armorSetViewModel = new ViewModelProvider(requireActivity()).get(ArmorSetViewModel.class);

        rarity4 = false;
        rarity5 = false;
        rarity6 = false;
        rarity7 = false;
        //endregion

        //setup the all the skill spinners within an observer
        skillViewModel.getAllSkills().observe(getViewLifecycleOwner(), skills -> {
            //create array lists to hold the lists for skill names and levels
            ArrayList<String> skillNames = new ArrayList<>();
            ArrayList<String> skill1Level = new ArrayList<>();
            ArrayList<String> skill2Level = new ArrayList<>();
            ArrayList<String> skill3Level = new ArrayList<>();

            //remove the (if upgrade) text from stormsoul
            for (Skill skill : skills) {
                String skillName =  getString(getResources().getIdentifier(skill.getSkillName(), "string", requireActivity().getPackageName()));
                if(skillName.contains("("))
                    skillName = skillName.substring(0, skillName.indexOf("("));
                skillNames.add(skillName);
            }

            //set the first element of the skill names list to a default no text value
            skillNames.add(0, "----");

            //region create the array adapters for the spinners
            ArrayAdapter<String> skillAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, skillNames);
            ArrayAdapter<String> skill1LevelAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, skill1Level);
            ArrayAdapter<String> skill2LevelAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, skill2Level);
            ArrayAdapter<String> skill3LevelAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, skill3Level);
            //endregion

            //region set the adapters and selection listeners for the skill spinners
            binding.skill1Spinner.setAdapter(skillAdapter);
            binding.skill1Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //region programmatically update the arrays for the skill level spinners based on the selected skill
                    //and set the name for the temp skill

                    //first empty the skill level list
                    skill1Level.clear();

                    //setup the spinner and temp skill values based on which item is selected
                    if(position == 0){
                        //if the first item in the spinner is selected, disable the corresponding skill level spinner
                        //and set the name of the temp skill to a blank string
                        binding.skill1LevelSpinner.setEnabled(false);
                        tempSkill1.setSkillName("");
                    }
                    else{
                        //otherwise, first re-enable the corresponding skill level spinner
                        binding.skill1LevelSpinner.setEnabled(true);
                        //then, create a new skill object based on which item has been selected
                        Skill skill = skills.get(position-1);

                        //set the name and max level of the temp skill to match that of the skill the user selected
                        tempSkill1.setSkillName(skill.getSkillName());
                        tempSkill1.setSkillMaxLevel(skill.getSkillMaxLevel());

                        //repopulate skill level list with all possible levels of the selected skill
                        for (int i = 1; i <= skill.getSkillMaxLevel(); i++) {
                            skill1Level.add(Integer.toString(i));
                        }
                    }

                    //notify the corresponding skill level adapter that values have changed
                    skill1LevelAdapter.notifyDataSetChanged();
                    //endregion
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            binding.skill2Spinner.setAdapter(skillAdapter);
            binding.skill2Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //region programmatically update the arrays for the skill level spinners based on the selected skill
                    //and set the name for the temp skill

                    //first empty the skill level list
                    skill2Level.clear();

                    //setup the spinner and temp skill values based on which item is selected
                    if(position == 0){
                        //if the first item in the spinner is selected, disable the corresponding skill level spinner
                        //and set the name of the temp skill to a blank string
                        binding.skill2LevelSpinner.setEnabled(false);
                        tempSkill2.setSkillName("");
                    }
                    else{
                        //otherwise, first re-enable the corresponding skill level spinner
                        binding.skill2LevelSpinner.setEnabled(true);
                        //then, create a new skill object based on which item has been selected
                        Skill skill = skills.get(position-1);

                        //set the name and max level of the temp skill to match that of the skill the user selected
                        tempSkill2.setSkillName(skill.getSkillName());
                        tempSkill2.setSkillMaxLevel(skill.getSkillMaxLevel());

                        //repopulate skill level list with all possible levels of the selected skill
                        for (int i = 1; i <= skill.getSkillMaxLevel(); i++) {
                            skill2Level.add(Integer.toString(i));
                        }
                    }
                    //notify the corresponding skill level adapter that values have changed
                    skill2LevelAdapter.notifyDataSetChanged();
                    //endregion
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            binding.skill3Spinner.setAdapter(skillAdapter);
            binding.skill3Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //region programmatically update the arrays for the skill level spinners based on the selected skill
                    //and set the name for the temp skill

                    //first empty the skill level list
                    skill3Level.clear();

                    //setup the spinner and temp skill values based on which item is selected
                    if(position == 0){
                        //if the first item in the spinner is selected, disable the corresponding skill level spinner
                        //and set the name of the temp skill to a blank string
                        binding.skill3LevelSpinner.setEnabled(false);
                        tempSkill3.setSkillName("");
                    }
                    else{
                        //otherwise, first re-enable the corresponding skill level spinner
                        binding.skill3LevelSpinner.setEnabled(true);
                        //then, create a new skill object based on which item has been selected
                        Skill skill = skills.get(position-1);

                        //set the name and max level of the temp skill to match that of the skill the user selected
                        tempSkill3.setSkillName(skill.getSkillName());
                        tempSkill3.setSkillMaxLevel(skill.getSkillMaxLevel());

                        //repopulate skill level list with all possible levels of the selected skill
                        for (int i = 1; i <= skill.getSkillMaxLevel(); i++) {
                            skill3Level.add(Integer.toString(i));
                        }
                    }
                    //notify the corresponding skill level adapter that values have changed
                    skill3LevelAdapter.notifyDataSetChanged();
                    //endregion
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            //endregion

            //region setup the skill level spinners
            binding.skill1LevelSpinner.setAdapter(skill1LevelAdapter);
            binding.skill1LevelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //check to see if the selected string is able to be converted into an int
                    //if yes, set the temp skill level to be the selected item, otherwise set it to 0
                    if (NumberUtils.isParsable(skill1Level.get(position)))
                        tempSkill1.setSkillLevel(Integer.parseInt(skill1Level.get(position)));
                    else
                        tempSkill1.setSkillLevel(0);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            binding.skill2LevelSpinner.setAdapter(skill2LevelAdapter);
            binding.skill2LevelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //check to see if the selected string is able to be converted into an int
                    //if yes, set the temp skill level to be the selected item, otherwise set it to 0
                    if (NumberUtils.isParsable(skill2Level.get(position)))
                        tempSkill2.setSkillLevel(Integer.parseInt(skill2Level.get(position)));
                    else
                        tempSkill2.setSkillLevel(0);

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            binding.skill3LevelSpinner.setAdapter(skill3LevelAdapter);
            binding.skill3LevelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //check to see if the selected string is able to be converted into an int
                    //if yes, set the temp skill level to be the selected item, otherwise set it to 0
                    if (NumberUtils.isParsable(skill3Level.get(position)))
                        tempSkill3.setSkillLevel(Integer.parseInt(skill3Level.get(position)));
                    else
                        tempSkill3.setSkillLevel(0);

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            //endregion
        });

        //region setup the weapon slot spinners
        String[] slotLevels = new String[]{"0", "1", "2", "3"};
        ArrayAdapter<String> weaponSlotsAdapter = new ArrayAdapter<>(requireContext(), R.layout.spinner_item, slotLevels);

        binding.weaponSlot1Spinner.setAdapter(weaponSlotsAdapter);
        binding.weaponSlot1Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                slot1 = Integer.parseInt(slotLevels[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.weaponSlot2Spinner.setAdapter(weaponSlotsAdapter);
        binding.weaponSlot2Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                slot2 = Integer.parseInt(slotLevels[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.weaponSlot3Spinner.setAdapter(weaponSlotsAdapter);
        binding.weaponSlot3Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                slot3 = Integer.parseInt(slotLevels[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //endregion

        //setup the recycler view
        binding.setBuilderList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.setBuilderList.setAdapter(adapter);

        //region setup the rarity checkboxes
        binding.rarity4Checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> rarity4 = isChecked);
        binding.rarity5Checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> rarity5 = isChecked);
        binding.rarity6Checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> rarity6 = isChecked);
        binding.rarity7Checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> rarity7 = isChecked);
        //endregion

        //setup the radio group
        binding.genderRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch(checkedId){
                case R.id.male_radio_button:
                    gender = "Male";
                case R.id.female_radio_button:
                    gender = "Female";
            }
        });

        //set the on click listener for the save button to generate armor sets based on the users specifications
        binding.searchSetButton.setOnClickListener(v -> generateArmorSets());

        WorkManager.getInstance(requireContext()).getWorkInfosByTagLiveData(Constants.SET_SEARCH_TAG)
                .observe(getViewLifecycleOwner(), workInfos -> {
                    if(workInfos == null || workInfos.isEmpty())
                        return;
                    Log.i(TAG, "onCreateView: " + workInfos.get(0).getState());

                });


        return binding.getRoot();
    }


    //Temp function for generating the armor sets
    //will be replaced when search algorithm is built
    private void generateArmorSets(){
        //check to make sure the user selected a gender and set at least 1 skill
        if(!gender.equals("") || (tempSkill1.getSkillName().equals("")
                && tempSkill2.getSkillName().equals("") && tempSkill3.getSkillName().equals(""))){
            //create list of integers to hold each of the rarities
            List<Integer> rarities = new ArrayList<>();
            //region add the rarities to search based on which checkboxes the user selected
            //(a checked rarity is excluded)
            if(!rarity4)
                rarities.add(4);
            if(!rarity5)
                rarities.add(5);
            if(!rarity6)
                rarities.add(6);
            if(!rarity7)
                rarities.add(7);
            //endregion

            //create the input data for database worker
            Data dbInputData = new Data.Builder()
                    .putString(Constants.GENDER, gender)
                    .putIntArray(Constants.RARITIES, rarities.stream().mapToInt(i->i).toArray())
                    .build();
            //create a work request for the database worker
            OneTimeWorkRequest databaseRequest = new OneTimeWorkRequest.Builder(DatabaseWorker.class)
                    .setInputData(dbInputData)
                    .build();

            //create the input data for the set builder worker
            HashMap<String, Integer> searchSkills = new HashMap<>();
            if(!tempSkill1.getSkillName().equals(""))
                searchSkills.put(tempSkill1.getSkillName(), tempSkill2.getSkillLevel());
            if(!tempSkill3.getSkillName().equals(""))
                searchSkills.put(tempSkill2.getSkillName(), tempSkill2.getSkillLevel());
            if(!tempSkill3.getSkillName().equals(""))
                searchSkills.put(tempSkill3.getSkillName(), tempSkill3.getSkillLevel());

            Data setSearchInputData = new Data.Builder()
                    .putString(Constants.SEARCH_SKILLS, Convertors.fromSkillMap(searchSkills))
                    .putIntArray(Constants.WEAPON_SLOTS, new int[]{slot1, slot2, slot3})
                    .build();

            //create a work request for the set builder worker
            OneTimeWorkRequest setBuilderWorkRequest = new OneTimeWorkRequest.Builder(SetBuilderWorker.class)
                    .setInputData(setSearchInputData)
                    .addTag(Constants.SET_SEARCH_TAG)
                    .build();

            //create a work continuation using the database and set builder workers
            WorkContinuation continuation = WorkManager.getInstance(requireContext())
                    .beginUniqueWork(Constants.SET_BUILDER_WORK_NAME, ExistingWorkPolicy.REPLACE, databaseRequest)
                    .then(setBuilderWorkRequest);
            //run the work
            continuation.enqueue();
        }


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