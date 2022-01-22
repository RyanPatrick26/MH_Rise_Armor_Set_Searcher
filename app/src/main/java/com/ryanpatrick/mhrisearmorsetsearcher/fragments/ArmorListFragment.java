package com.ryanpatrick.mhrisearmorsetsearcher.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ryanpatrick.mhrisearmorsetsearcher.R;
import com.ryanpatrick.mhrisearmorsetsearcher.adapters.ArmorListAdapter;
import com.ryanpatrick.mhrisearmorsetsearcher.databinding.FragmentArmorListBinding;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Armor;
import com.ryanpatrick.mhrisearmorsetsearcher.model.viewmodels.ArmorViewModel;

import java.util.List;

public class ArmorListFragment extends Fragment {
    FragmentArmorListBinding binding;
    private List<Armor> armorList;
    private ArmorListAdapter adapter;
    ArmorViewModel viewModel;

    public ArmorListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentArmorListBinding.inflate(inflater, container, false);
        binding.armorListView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModel = new ViewModelProvider(requireActivity()).get(ArmorViewModel.class);

        viewModel.getAllArmor().observe(getViewLifecycleOwner(), armorList -> {
            adapter = new ArmorListAdapter(armorList, getContext());
            binding.armorListView.setAdapter(adapter);
        });

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}