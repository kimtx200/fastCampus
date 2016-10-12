package com.taewonkim.android.basicwidget02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class TapActivity extends AppCompatActivity {

    TabHost tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap);

        tab = (TabHost)findViewById(R.id.tabHost);
        tab.setup();

        // Tab1 생성
        TabHost.TabSpec spec1 = tab.newTabSpec("Tab One");

        // Tab1
        spec1.setContent(R.id.linearLayout1);
        spec1.setIndicator("Tab 001");
        tab.addTab(spec1);

        TabHost.TabSpec spec2 = tab.newTabSpec("Tab Two");
        spec2.setContent(R.id.linearLayout2);
        spec2.setIndicator("Tab 002");
        tab.addTab(spec2);

        TabHost.TabSpec spec3 = tab.newTabSpec("Tab Three");
        spec3.setContent(R.id.linearLayout3);
        spec3.setIndicator("Tab 003");
        tab.addTab(spec3);

    }
}
