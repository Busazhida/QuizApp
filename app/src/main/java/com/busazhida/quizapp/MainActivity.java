package com.busazhida.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.busazhida.quizapp.ui.adapter.MainViewPagerAdapter;
import com.busazhida.quizapp.ui.main.MainViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationMenu;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationMenu = findViewById(R.id.bottom_nav_view);
        viewPager = findViewById(R.id.main_pager);

        viewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(3);

        bottomNavigationMenu.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.main_nav:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.history_nav:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.settings_nav:
                    viewPager.setCurrentItem(2);
                    break;
            }
            return true;
        });


    }
}