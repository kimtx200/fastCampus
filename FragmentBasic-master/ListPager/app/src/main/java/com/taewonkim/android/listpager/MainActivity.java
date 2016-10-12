package com.taewonkim.android.listpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<ListData> database = new ArrayList<>();
    public static int position=-1;
    public static ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setData();

        pager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.\n" +
                    "DE Modfastails inform ation here.\n" +
                    "a dfas dfre detailsadfdsformation here.\n" +
                    "SsSsadfg fd fsadrsadfails information here.\n" +
                    "K adfdsafore detaiasfdstion fsdae.\n" +
                    "Mofdsa fdetadfsds ifsdfation here.");
        }
        return builder.toString();
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {

        ListFragment listFragment;
        DetailFragment detailFragment;

        // 프레그먼트 어댑터를 생성하기 위해서는 프레그먼트 매니저를 생성자에 넘겨주고
        // 부모 어댑터가 초기화 되어야 한다.

        public PagerAdapter(FragmentManager manager) {
            super(manager);

            listFragment = new ListFragment();
            detailFragment = new DetailFragment();

            /*
            TextView txtTitle = (TextView) detailFragment.getActivity().findViewById(R.id.title);
            txtTitle.setText(database.get(position).title);

            TextView txtContents = (TextView) detailFragment.getActivity().findViewById(R.id.contents);
            txtContents.setText(database.get(position).contents);
            */
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
                    fragment = listFragment;
                    break;

                case 1:
                    fragment = detailFragment;
                    break;

            }
            return fragment;
        }
    }

    public void setData() {
        for (int i = 0; i < 20; i++) {
            ListData data = new ListData();
            data.title = " Item Title";
            data.contents = makeDetails(i);
            database.add(data);
        }
    }
}
