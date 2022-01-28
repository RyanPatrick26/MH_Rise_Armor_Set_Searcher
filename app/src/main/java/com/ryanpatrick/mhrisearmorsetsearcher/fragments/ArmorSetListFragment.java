package com.ryanpatrick.mhrisearmorsetsearcher.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ryanpatrick.mhrisearmorsetsearcher.R;
import com.ryanpatrick.mhrisearmorsetsearcher.adapters.SetListAdapter;
import com.ryanpatrick.mhrisearmorsetsearcher.databinding.FragmentArmorSetListBinding;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.ArmorSet;
import com.ryanpatrick.mhrisearmorsetsearcher.model.viewmodels.ArmorSetViewModel;
import com.ryanpatrick.mhrisearmorsetsearcher.util.Utilities;

import java.util.List;

public class ArmorSetListFragment extends Fragment implements SetListAdapter.OnSetClickListener {
    private ArmorSetViewModel armorSetViewModel;
    private SetListAdapter adapter;
    private FragmentArmorSetListBinding binding;

    public ArmorSetListFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentArmorSetListBinding.inflate(inflater, container, false);
        armorSetViewModel = new ViewModelProvider(requireActivity()).get(ArmorSetViewModel.class);
        if(Utilities.isTablet(requireContext()) || getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE)
            binding.armorSetList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        else
            binding.armorSetList.setLayoutManager(new LinearLayoutManager(getContext()));

        armorSetViewModel.getAllArmorSets().observe(getViewLifecycleOwner(), armorSets -> {
            if(armorSets.size() == 0)
                binding.noSetsTextView.setVisibility(View.VISIBLE);
            adapter = new SetListAdapter(armorSets, getContext(), this);
            binding.armorSetList.setAdapter(adapter);
        });

        return binding.getRoot();
    }

    @Override
    public void onSetClick(int position, List<ArmorSet> setList) {
        armorSetViewModel.setTempSet(setList.get(position));
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new SetDetailsFragment()).commit();
    }
}
