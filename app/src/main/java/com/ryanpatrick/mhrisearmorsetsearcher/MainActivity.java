package com.ryanpatrick.mhrisearmorsetsearcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.ryanpatrick.mhrisearmorsetsearcher.model.viewmodels.ArmorViewModel;
import com.ryanpatrick.mhrisearmorsetsearcher.model.viewmodels.GemViewModel;
import com.ryanpatrick.mhrisearmorsetsearcher.model.viewmodels.SkillViewModel;

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