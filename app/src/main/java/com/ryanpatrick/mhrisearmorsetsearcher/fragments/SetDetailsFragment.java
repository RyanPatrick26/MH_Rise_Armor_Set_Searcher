package com.ryanpatrick.mhrisearmorsetsearcher.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ryanpatrick.mhrisearmorsetsearcher.R;
import com.ryanpatrick.mhrisearmorsetsearcher.adapters.SkillsListAdapter;
import com.ryanpatrick.mhrisearmorsetsearcher.databinding.FragmentSetBuilderBinding;
import com.ryanpatrick.mhrisearmorsetsearcher.databinding.FragmentSetDetailsBinding;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.ArmorSet;
import com.ryanpatrick.mhrisearmorsetsearcher.model.viewmodels.ArmorSetViewModel;
import com.ryanpatrick.mhrisearmorsetsearcher.model.viewmodels.SkillViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SetDetailsFragment extends Fragment {
    FragmentSetDetailsBinding binding;
    ArmorSet armorSet;
    ArmorSetViewModel armorSetViewModel;
    SkillViewModel skillViewModel;
    List<String> skillDescriptionList;
    SkillsListAdapter skillsListAdapter;

    public SetDetailsFragment() {
        // Required empty public constructor
    }

    public static SetDetailsFragment newInstance() {
        return new SetDetailsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        skillDescriptionList = new ArrayList<>();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSetDetailsBinding.inflate(inflater, container, false);
        armorSetViewModel = new ViewModelProvider(requireActivity()).get(ArmorSetViewModel.class);
        skillViewModel = new ViewModelProvider(requireActivity()).get(SkillViewModel.class);
        armorSet = armorSetViewModel.getTempSet();
        skillsListAdapter = new SkillsListAdapter(armorSet.getTotalSkills(), getContext());

        skillsListAdapter.setOnSkillClickListener(position -> {
            skillViewModel.getSkill(armorSet.getTotalSkills().get(position).getSkillName()).observe(getViewLifecycleOwner(), skill -> {
                Log.i("here", "onCreateView: " + skill.getDescription());
                skillDescriptionList = Arrays.asList(getResources().getStringArray(getResources()
                        .getIdentifier(skill.getDescription(), "array", requireActivity().getPackageName())));
                binding.skillDescription.setText(skillDescriptionList.get(0));
            });
        });

        Log.i("here", "onCreateView: " + skillDescriptionList.size());


        binding.piecesLayout.helmetText.setText(getResources().getIdentifier(armorSet.getHead().getArmorName(),
                "string", requireActivity().getPackageName()));
        binding.piecesLayout.chestText.setText(getResources().getIdentifier(armorSet.getChest().getArmorName(),
                "string", requireActivity().getPackageName()));
        binding.piecesLayout.armsText.setText(getResources().getIdentifier(armorSet.getArms().getArmorName(),
                "string", requireActivity().getPackageName()));
        binding.piecesLayout.waistText.setText(getResources().getIdentifier(armorSet.getWaist().getArmorName(),
                "string", requireActivity().getPackageName()));
        binding.piecesLayout.legsText.setText(getResources().getIdentifier(armorSet.getLegs().getArmorName(),
                "string", requireActivity().getPackageName()));

        binding.defenseLayout.defenseText.setText(String.format("%d/%d", armorSet.getTotalBaseDefense(), armorSet.getTotalMaxDefense()));
        binding.defenseLayout.fireResText.setText(Integer.toString(armorSet.getTotalFireRes()));
        binding.defenseLayout.waterResText.setText(Integer.toString(armorSet.getTotalWaterRes()));
        binding.defenseLayout.thunderResText.setText(Integer.toString(armorSet.getTotalThunderRes()));
        binding.defenseLayout.iceResText.setText(Integer.toString(armorSet.getTotalIceRes()));
        binding.defenseLayout.dragonResText.setText(Integer.toString(armorSet.getTotalDragonRes()));

        binding.level1Slots.setText(Integer.toString(armorSet.getTotalSlots()[0]));
        binding.level2Slots.setText(Integer.toString(armorSet.getTotalSlots()[1]));
        binding.level3Slots.setText(Integer.toString(armorSet.getTotalSlots()[2]));

        binding.setSkillList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.setSkillList.setAdapter(skillsListAdapter);

        return binding.getRoot();
    }
}