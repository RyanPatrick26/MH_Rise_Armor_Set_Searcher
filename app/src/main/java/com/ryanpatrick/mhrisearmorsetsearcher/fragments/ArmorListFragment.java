package com.ryanpatrick.mhrisearmorsetsearcher.fragments;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ryanpatrick.mhrisearmorsetsearcher.adapters.ArmorListAdapter;
import com.ryanpatrick.mhrisearmorsetsearcher.databinding.FragmentArmorListBinding;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Armor;
import com.ryanpatrick.mhrisearmorsetsearcher.model.viewmodels.ArmorViewModel;
import com.ryanpatrick.mhrisearmorsetsearcher.util.Utilities;

import java.util.ArrayList;
import java.util.List;

public class ArmorListFragment extends Fragment {
    FragmentArmorListBinding binding;
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentArmorListBinding.inflate(inflater, container, false);

        //set the layout manager for the recycler view to be a grid layout if he user is on a wide screen,
        //or a linear layout if not
        if(Utilities.isTablet(requireContext()) || getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE)
            binding.armorListView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        else
            binding.armorListView.setLayoutManager(new LinearLayoutManager(getContext()));

        binding.armorListView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModel = new ViewModelProvider(requireActivity()).get(ArmorViewModel.class);

        viewModel.getAllArmor().observe(getViewLifecycleOwner(), armorList -> {
            adapter = new ArmorListAdapter(armorList, getContext());
            binding.armorListView.setAdapter(adapter);
        });

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
        viewModel = null;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}