package com.ryanpatrick.mhrisearmorsetsearcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;

import com.ryanpatrick.mhrisearmorsetsearcher.data.ApplicationDatabase;
import com.ryanpatrick.mhrisearmorsetsearcher.data.ArmorRepository;
import com.ryanpatrick.mhrisearmorsetsearcher.data.GemRepository;
import com.ryanpatrick.mhrisearmorsetsearcher.data.SkillRepository;
import com.ryanpatrick.mhrisearmorsetsearcher.model.Armor;
import com.ryanpatrick.mhrisearmorsetsearcher.model.Gem;
import com.ryanpatrick.mhrisearmorsetsearcher.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GemRepository gemRepository = new GemRepository(MainActivity.this.getApplication());
        SkillRepository skillRepository = new SkillRepository(MainActivity.this.getApplication());

        
    }
}