package com.ryanpatrick.mhrisearmorsetsearcher.fragments;

import static com.ryanpatrick.mhrisearmorsetsearcher.R.color.card_background_color;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ryanpatrick.mhrisearmorsetsearcher.App;
import com.ryanpatrick.mhrisearmorsetsearcher.R;
import com.ryanpatrick.mhrisearmorsetsearcher.adapters.SkillDetailsAdapter;
import com.ryanpatrick.mhrisearmorsetsearcher.adapters.SkillsListAdapter;
import com.ryanpatrick.mhrisearmorsetsearcher.data.ApplicationDatabase;
import com.ryanpatrick.mhrisearmorsetsearcher.databinding.FragmentSetDetailsBinding;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.ArmorSet;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Skill;
import com.ryanpatrick.mhrisearmorsetsearcher.model.viewmodels.ArmorSetViewModel;
import com.ryanpatrick.mhrisearmorsetsearcher.model.viewmodels.SkillViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SetDetailsFragment extends Fragment {
    FragmentSetDetailsBinding binding;
    ArmorSet armorSet;
    ArmorSetViewModel armorSetViewModel;
    SkillViewModel skillViewModel;
    List<String> skillDescriptionList;
    SkillsListAdapter skillsListAdapter;
    SkillDetailsAdapter detailsAdapter;
    HashMap<String, Integer> skills;

    public SetDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        skillDescriptionList = new ArrayList<>();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //region fragment variables
        binding = FragmentSetDetailsBinding.inflate(inflater, container, false);
        armorSetViewModel = new ViewModelProvider(requireActivity()).get(ArmorSetViewModel.class);
        skillViewModel = new ViewModelProvider(requireActivity()).get(SkillViewModel.class);
        armorSet = armorSetViewModel.getTempSet();
        skills = armorSet.getAllSkills();
        skillsListAdapter = new SkillsListAdapter(skills, getContext());
        detailsAdapter = new SkillDetailsAdapter(skillDescriptionList, getContext());
        //endregion
        if(armorSet.getId() != null){
            binding.saveSetButton.setVisibility(View.GONE);
            binding.deleteSetButton.setVisibility(View.VISIBLE);
        }

        //set the skill list adapters on click listener to update description
        //list with the descriptions of the skill selected
        skillsListAdapter.setOnSkillClickListener((skillName, position) ->
                //get the skill from the database that corresponds with the skill the user selected
                skillViewModel.getSkill(skillName).observe(getViewLifecycleOwner(), skill -> {
                    Log.i("here", "onCreateView: " + skillName);
                    for (int i = 0; i < skillsListAdapter.getItemCount(); i++) {
                        if(binding.setSkillList.getChildAt(i) != null) {
                            if (i == position) {
                                binding.setSkillList.getChildAt(i)
                                        .setBackgroundColor(getResources().getColor(card_background_color, null));
                            } else {
                                binding.setSkillList.getChildAt(i)
                                        .setBackgroundColor(Color.TRANSPARENT);
                            }
                        }
                    }
                    updateDescriptionList(skill, skills.get(skillName));
                        }));

        //region set the text views for the armor pieces
        binding.piecesLayout.helmetText.setText(getResources()
                .getIdentifier(armorSet.getHead().getArmorName(),
                "string", requireActivity().getPackageName()));
        binding.piecesLayout.chestText.setText(getResources()
                .getIdentifier(armorSet.getChest().getArmorName(),
                "string", requireActivity().getPackageName()));
        binding.piecesLayout.armsText.setText(getResources().
                getIdentifier(armorSet.getArms().getArmorName(),
                "string", requireActivity().getPackageName()));
        binding.piecesLayout.waistText.setText(getResources()
                .getIdentifier(armorSet.getWaist().getArmorName(),
                "string", requireActivity().getPackageName()));
        binding.piecesLayout.legsText.setText(getResources()
                .getIdentifier(armorSet.getLegs().getArmorName(),
                "string", requireActivity().getPackageName()));
        //endregion

        //region set the text views for the defenses
        binding.defenseLayout.defenseText.setText(String.format("%d/%d", armorSet.getTotalBaseDefense(), armorSet.getTotalMaxDefense()));
        binding.defenseLayout.fireResText.setText(Integer.toString(armorSet.getTotalFireRes()));
        binding.defenseLayout.waterResText.setText(Integer.toString(armorSet.getTotalWaterRes()));
        binding.defenseLayout.thunderResText.setText(Integer.toString(armorSet.getTotalThunderRes()));
        binding.defenseLayout.iceResText.setText(Integer.toString(armorSet.getTotalIceRes()));
        binding.defenseLayout.dragonResText.setText(Integer.toString(armorSet.getTotalDragonRes()));
        //endregion

        //region set the text views for the slots
        binding.level1Slots.setText(Integer.toString(armorSet.getTotalSlots()[0]));
        binding.level2Slots.setText(Integer.toString(armorSet.getTotalSlots()[1]));
        binding.level3Slots.setText(Integer.toString(armorSet.getTotalSlots()[2]));
        //endregion

        //region set the layout managers and adapters for the recycler views
        binding.setSkillList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.setSkillList.setAdapter(skillsListAdapter);

        binding.skillDescriptionList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.skillDescriptionList.setAdapter(detailsAdapter);
        //endregion

        binding.saveSetButton.setOnClickListener(v -> saveSet());
        binding.deleteSetButton.setOnClickListener(v -> deleteSet());
        return binding.getRoot();
    }

    private void updateDescriptionList(Skill skill, int skillLevel){
        //clear the description list
        skillDescriptionList.clear();
        //add all of the descriptions for the selected skill
        skillDescriptionList.addAll(Arrays.asList(getResources().getStringArray(getResources()
                .getIdentifier(skill.getDescription(), "array", requireActivity().getPackageName()))));
        //set the skill level in the adapter to be the level of the selected skill
        detailsAdapter.setSkillLevel(skillLevel);
        //notify the adapter that the descriptions have changed
        detailsAdapter.notifyDataSetChanged();
    }

    private void saveSet(){
        armorSetViewModel.getAllArmorSets().observe(getViewLifecycleOwner(), new Observer<List<ArmorSet>>() {
            @Override
            public void onChanged(List<ArmorSet> armorSets) {
                if(!setAlreadySaved(armorSets, armorSet)){
                    armorSetViewModel.insertArmorSet(armorSet);
                    Toast.makeText(getContext(), R.string.set_saved, Toast.LENGTH_LONG).show();
                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new ArmorSetListFragment()).commit();
                    armorSetViewModel.getAllArmorSets().removeObserver(this);
                }
                else{
                    Toast.makeText(getContext(), R.string.set_already_saved, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void deleteSet(){
        armorSetViewModel.deleteArmorSet(armorSet);
        Toast.makeText(getContext(), R.string.set_deleted, Toast.LENGTH_LONG).show();
        requireActivity().getSupportFragmentManager().beginTransaction().
                replace(R.id.fragment_container, new ArmorSetListFragment()).commit();
    }

    private boolean setAlreadySaved(List<ArmorSet> allArmorSets, ArmorSet armorSet){
        for (ArmorSet tempSet : allArmorSets) {
            if (armorSet.equals(tempSet))
                Log.i("here", "setAlreadySaved: here");
                return true;
        }
        return false;
    }
}