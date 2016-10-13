package com.taewonkim.android.secretboard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.taewonkim.android.secretboard.com.taewon.android.secretboard.domain.Memo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ViewPager pager;

    ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // database 생성
        DatabaseUtils databaseUtils = new DatabaseUtils(this);
        Memo memo = new Memo();

        // test data 생성
        memo.title = "메모 제목";
        memo.name = "메모 작성자";
        memo.contents = "메모 내용";
        memo.nDate = databaseUtils.getTimeStamp();
        memo.image = "this is image";
        databaseUtils.dbInsert(memo);

        ArrayList<Memo> database = databaseUtils.dbSelectAll();
        for(Memo m : database){
            Log.i("Memo","no="+m.no+"");
            Log.i("Memo","title="+m.title);
            Log.i("Memo","name="+m.name);
            Log.i("Memo","contents="+m.contents);
            Log.i("Memo","Date="+m.nDate);
            Log.i("Memo","image="+m.image);
        }

        ListFragment listFragment = new ListFragment();
        fragments.add(listFragment);

        DetailFragment detailFragment = new DetailFragment();
        fragments.add(detailFragment);

        pager = (ViewPager) findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter{

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
