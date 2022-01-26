package com.ryanpatrick.mhrisearmorsetsearcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.ryanpatrick.mhrisearmorsetsearcher.databinding.ActivityMainBinding;
import com.ryanpatrick.mhrisearmorsetsearcher.fragments.ArmorListFragment;
import com.ryanpatrick.mhrisearmorsetsearcher.fragments.BuilderHostFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    public static final String TAG = "here";
    FragmentManager fm;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fragment_container, new ArmorListFragment()).commit();

        setSupportActionBar(binding.appBarMain.toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawerView,
                binding.appBarMain.toolbar, R.string.open, R.string.close);
        binding.drawerView.addDrawerListener(toggle);

        toggle.syncState();

        binding.navView.setNavigationItemSelectedListener(this);


    }

    /**
     * Called when an item in the navigation menu is selected.
     *
     * @param item The selected item
     * @return true to display the item as the selected item
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_armor_list:
                fm.beginTransaction().replace(R.id.fragment_container, new ArmorListFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.menu_set_builder:
                fm.beginTransaction().replace(R.id.fragment_container, new BuilderHostFragment())
                        .addToBackStack(null)
                        .commit();
                break;
        }

        binding.drawerView.closeDrawer(GravityCompat.START);

        return true;
    }
}