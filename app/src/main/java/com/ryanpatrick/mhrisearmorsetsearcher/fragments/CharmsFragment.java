package com.ryanpatrick.mhrisearmorsetsearcher.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ryanpatrick.mhrisearmorsetsearcher.R;
import com.ryanpatrick.mhrisearmorsetsearcher.adapters.ArmorListAdapter;
import com.ryanpatrick.mhrisearmorsetsearcher.adapters.CharmListAdapter;
import com.ryanpatrick.mhrisearmorsetsearcher.databinding.FragmentCharmsBinding;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Charm;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Skill;
import com.ryanpatrick.mhrisearmorsetsearcher.model.viewmodels.CharmViewModel;
import com.ryanpatrick.mhrisearmorsetsearcher.model.viewmodels.SkillViewModel;

import java.util.ArrayList;
import java.util.List;

public class CharmsFragment extends Fragment {
    FragmentCharmsBinding binding;
    CharmViewModel charmViewModel;
    SkillViewModel skillViewModel;
    String[] skillLevels, slotLevels;
    int skill1Level, skill2Level, slot1Level, slot2Level, slot3Level;
    String skill1Name, skill2Name;
    CharmListAdapter adapter;


    public CharmsFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //region instantiate variables for the fragment
        skillLevels = new String[]{"0", "1", "2", "3", "4"};
        slotLevels = new String[]{"0", "1", "2", "3"};
        charmViewModel = new ViewModelProvider(requireActivity()).get(CharmViewModel.class);
        skillViewModel = new ViewModelProvider(requireActivity()).get(SkillViewModel.class);
        binding = FragmentCharmsBinding.inflate(inflater, container, false);
        skill1Name = "----";
        skill2Name = "----";
        //endregion

        //region create the array adapters for the spinners
        ArrayAdapter<String> skillLevelAdapter =
                new ArrayAdapter<String>(getContext(), R.layout.spinner_item, skillLevels){
                    @Override
                    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
                        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        return textView;
                    }
                };
        ArrayAdapter<String> slotLevelAdapter = new ArrayAdapter<String>(getContext(),
                R.layout.spinner_item, slotLevels){
                    @Override
                    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
                        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        return textView;
                    }
                };
        //endregion

        //region set the adapters and select listeners for the skill level and slot spinners
        binding.skill1LevelSpinner.setAdapter(skillLevelAdapter);
        binding.skill1LevelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                skill1Level = Integer.parseInt(skillLevels[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.skill2LevelSpinner.setAdapter(skillLevelAdapter);
        binding.skill2LevelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                skill2Level = Integer.parseInt(skillLevels[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.slot1Spinner.setAdapter(slotLevelAdapter);
        binding.slot1Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                slot1Level = Integer.parseInt(slotLevels[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.slot2Spinner.setAdapter(slotLevelAdapter);
        binding.slot2Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                slot2Level = Integer.parseInt(slotLevels[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.slot3Spinner.setAdapter(slotLevelAdapter);
        binding.slot3Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                slot3Level = Integer.parseInt(slotLevels[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //endregion

        //set the skill name spinners inside an observer
        skillViewModel.getAllSkills().observe(getViewLifecycleOwner(), skills -> {
            ArrayList<String> skillNames = new ArrayList<>();
            for (Skill skill : skills) {
                String skillName =  getString(getResources().getIdentifier(skill.getSkillName(), "string", requireActivity().getPackageName()));
                if(skillName.contains("("))
                    skillName = skillName.substring(0, skillName.indexOf("("));
                skillNames.add(skillName);
            }

            skillNames.add(0, "----");

            ArrayAdapter<String> skillAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, skillNames);
            binding.skill1Spinner.setAdapter(skillAdapter);
            binding.skill1Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(position == 0){
                        skill1Name = skillNames.get(position);
                    }
                    else{
                        skill1Name = skills.get(position-1).getSkillName();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            binding.skill2Spinner.setAdapter(skillAdapter);
            binding.skill2Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(position == 0){
                        skill1Name = skillNames.get(position);
                    }
                    else{
                        skill2Name = skills.get(position-1).getSkillName();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        });

        //region setup the charm list
        binding.charmListView.setLayoutManager(new LinearLayoutManager(getContext()));

        charmViewModel.getAllCharms().observe(getViewLifecycleOwner(), charms -> {
            if(charms.size() > 0)
                binding.noCharmsText.setVisibility(View.GONE);
            else
                binding.noCharmsText.setVisibility(View.VISIBLE);
            adapter = new CharmListAdapter(charms, getContext());
            binding.charmListView.setAdapter(adapter);
        });
        //endregion

        binding.saveCharmButton.setOnClickListener(v -> saveCharm());

        return binding.getRoot();
    }

    private void saveCharm(){
        Skill skill1 = new Skill(skill1Name, !skill1Name.equals("----") ? skill1Level : 0);
        Skill skill2 = new Skill(skill2Name, !skill2Name.equals("----") ? skill2Level : 0);

        Charm charm = new Charm(skill1, skill2, new int[]{slot1Level, slot2Level, slot3Level});

        charmViewModel.addCharm(charm);

    }
}