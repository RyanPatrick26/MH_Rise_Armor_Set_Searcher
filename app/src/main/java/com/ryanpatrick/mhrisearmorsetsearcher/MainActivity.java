package com.ryanpatrick.mhrisearmorsetsearcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.ryanpatrick.mhrisearmorsetsearcher.data.ArmorRepository;
import com.ryanpatrick.mhrisearmorsetsearcher.data.GemRepository;
import com.ryanpatrick.mhrisearmorsetsearcher.data.SkillRepository;
import com.ryanpatrick.mhrisearmorsetsearcher.model.ArmorViewModel;
import com.ryanpatrick.mhrisearmorsetsearcher.model.Gem;
import com.ryanpatrick.mhrisearmorsetsearcher.model.GemViewModel;
import com.ryanpatrick.mhrisearmorsetsearcher.model.SkillViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private GemViewModel gemViewModel;
    private ArmorViewModel armorViewModel;
    private SkillViewModel skillViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gemViewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this.getApplication())
                .create(GemViewModel.class);
        armorViewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this.getApplication())
                .create(ArmorViewModel.class);
        skillViewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this.getApplication())
                .create(SkillViewModel.class);
    }
}