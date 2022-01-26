package com.ryanpatrick.mhrisearmorsetsearcher.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ryanpatrick.mhrisearmorsetsearcher.R;

public class BuilderHostFragment extends Fragment {
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_builder_host, container, false);
    }
}