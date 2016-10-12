package com.taewonkim.android.fragmentbasic_pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

/*

메인 뷰에서 여러개의 프레그먼트를 이동하면서 보여줄 수 있는 프로젝트

 */
public class MainActivity extends AppCompatActivity {

    ArrayList<Fragment> database = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager pager = (ViewPager)findViewById(R.id.pager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        Fragment blankFragment = new BlankFragment();
        database.add(blankFragment);

        Fragment galleryFragment = new GalleryFragment();
        database.add(galleryFragment);

    }

    private class PagerAdapter extends FragmentStatePagerAdapter {

        BlankFragment blankFragment;
        GalleryFragment galleryFragment;

        ViewPager pager = new PagerAdapter(getSupportFragmentManager(), database);


        // 프레그먼트 어댑터를 생성하기 위해서는 프레그먼트 매니저를 생성자에 넘겨주고
        // 부모 어댑터가 초기화 되어야 한다.

        public PagerAdapter(FragmentManager manager) {
            super(manager);


        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = blankFragment;
                    break;

                case 1:
                    fragment = galleryFragment;
                    break;

            }
            return fragment;
        }
    }
}

