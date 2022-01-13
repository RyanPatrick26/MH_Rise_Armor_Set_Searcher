package com.ryanpatrick.mhrisearmorsetsearcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ryanpatrick.mhrisearmorsetsearcher.data.GemRepository;
import com.ryanpatrick.mhrisearmorsetsearcher.data.SkillRepository;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GemRepository gemRepository = new GemRepository(MainActivity.this.getApplication());
        SkillRepository skillRepository = new SkillRepository(MainActivity.this.getApplication());


    }
}