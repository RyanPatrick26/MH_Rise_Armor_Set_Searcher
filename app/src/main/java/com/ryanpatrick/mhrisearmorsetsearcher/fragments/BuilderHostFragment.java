package com.ryanpatrick.mhrisearmorsetsearcher.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ryanpatrick.mhrisearmorsetsearcher.R;
import com.ryanpatrick.mhrisearmorsetsearcher.adapters.SetListAdapter;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.ArmorSet;
import com.ryanpatrick.mhrisearmorsetsearcher.model.viewmodels.ArmorSetViewModel;

import java.util.List;

public class BuilderHostFragment extends Fragment implements SetListAdapter.OnSetClickListener{
    ArmorSetViewModel setViewModel;
    FragmentManager builderFragmentManager;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setViewModel = new ViewModelProvider(requireActivity()).get(ArmorSetViewModel.class);
        builderFragmentManager = requireActivity().getSupportFragmentManager();

        builderFragmentManager.beginTransaction().add(R.id.builder_fragment_container, new SetBuilderFragment(this)).commit();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_builder_host, container, false);
    }

    @Override
    public void onSetClick(int position, List<ArmorSet> setList) {
        setViewModel.setTempSet(setList.get(position));
        builderFragmentManager.beginTransaction()
                .replace(R.id.builder_fragment_container, new SetDetailsFragment())
                .commit();
    }
}