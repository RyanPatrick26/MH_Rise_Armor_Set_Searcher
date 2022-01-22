package com.ryanpatrick.mhrisearmorsetsearcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.ryanpatrick.mhrisearmorsetsearcher.fragments.ArmorListFragment;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "here";
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fragment_container, new ArmorListFragment()).commit();

        ViewTreeObserver viewTreeObserver = findViewById(R.id.fragment_container).getViewTreeObserver();

    }
}