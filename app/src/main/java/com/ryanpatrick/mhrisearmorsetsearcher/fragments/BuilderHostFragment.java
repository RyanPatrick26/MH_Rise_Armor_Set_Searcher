package com.ryanpatrick.mhrisearmorsetsearcher.fragments;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ryanpatrick.mhrisearmorsetsearcher.R;
import com.ryanpatrick.mhrisearmorsetsearcher.adapters.SetListAdapter;
import com.ryanpatrick.mhrisearmorsetsearcher.databinding.FragmentBuilderHostBinding;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.ArmorSet;
import com.ryanpatrick.mhrisearmorsetsearcher.model.viewmodels.ArmorSetViewModel;
import com.ryanpatrick.mhrisearmorsetsearcher.util.Utilities;

import java.util.List;

public class BuilderHostFragment extends Fragment implements SetListAdapter.OnSetClickListener{
    ArmorSetViewModel setViewModel;
    FragmentManager builderFragmentManager;
    FragmentBuilderHostBinding binding;

    public BuilderHostFragment() {
        // Required empty public constructor
    }
    public static BuilderHostFragment newInstance() {
        return new BuilderHostFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBuilderHostBinding.inflate(inflater, container, false);

        setViewModel = new ViewModelProvider(requireActivity()).get(ArmorSetViewModel.class);
        builderFragmentManager = requireActivity().getSupportFragmentManager();

        builderFragmentManager.beginTransaction().add(R.id.builder_fragment_container, new SetBuilderFragment(this)).commit();

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onSetClick(int position, List<ArmorSet> setList) {
        setViewModel.setTempSet(setList.get(position));
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE
            || Utilities.isTablet(requireContext())){
            binding.setDetailsContainer.setVisibility(View.VISIBLE);
            builderFragmentManager.beginTransaction()
                    .replace(R.id.set_details_container, new SetDetailsFragment())
                    .commit();
        }
        else{
            builderFragmentManager.beginTransaction()
                    .replace(R.id.builder_fragment_container, new SetDetailsFragment())
                    .addToBackStack("BACK")
                    .commit();
        }
    }
}