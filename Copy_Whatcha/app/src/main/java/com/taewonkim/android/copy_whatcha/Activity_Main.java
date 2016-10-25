package com.taewonkim.android.copy_whatcha;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

public class Activity_Main extends AppCompatActivity {

    TabLayout tabLayout;

    Fragment home;
    Fragment search;
    Fragment like;
    Fragment profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__main);

        tabLayout = (TabLayout)findViewById(R.id.tabLayout);

        tabLayout.addTab(tabLayout.newTab().setText("HOME"));
        tabLayout.addTab(tabLayout.newTab().setText("SEARCH"));
        tabLayout.addTab(tabLayout.newTab().setText("LIKE"));
        tabLayout.addTab(tabLayout.newTab().setText("MY PROFILE"));

        home = new Fragment_Home();
        search = new Fragment_Search();
        like = new Fragment_Like();
        profile = new Fragment_Profile();


    }
}
