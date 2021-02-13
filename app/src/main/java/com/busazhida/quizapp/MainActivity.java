package com.busazhida.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.busazhida.quizapp.databinding.ActivityMainBinding;
import com.busazhida.quizapp.databinding.MainFragmentBinding;
import com.busazhida.quizapp.ui.adapter.MainViewPagerAdapter;
import com.busazhida.quizapp.ui.main.MainViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private BottomNavigationView bottomNavigationMenu;
    private ViewPager viewPager;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(App.getInstance().getPrefs().getTheme());
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        bottomNavigationMenu = findViewById(R.id.bottom_nav_view);
        viewPager = findViewById(R.id.main_pager);
        mainViewModel = new MainViewModel();
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