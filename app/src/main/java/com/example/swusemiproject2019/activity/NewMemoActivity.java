package com.example.swusemiproject2019.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.swusemiproject2019.fragment.FragmentCamera;
import com.example.swusemiproject2019.R;
import com.example.swusemiproject2019.fragment.FragmentMemoWrite;
import com.google.android.material.tabs.TabLayout;

public class NewMemoActivity extends AppCompatActivity {

    private TabLayout tabLayout; //tab 영역
    private ViewPager viewPager; //tab별 표시할 영역
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_memo_write);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        //tab 생성
        tabLayout.addTab(tabLayout.newTab().setText("글쓰기"));
        tabLayout.addTab(tabLayout.newTab().setText("사진찍기"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        NewMemoActivity.MyPagerAdapter adapter = new NewMemoActivity.MyPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
    class MyPagerAdapter extends FragmentPagerAdapter {
        int tabSize; //탭의 수

        public MyPagerAdapter(FragmentManager fm, int count){
            super(fm);
            this.tabSize = count; //탭의 수
        }


        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new FragmentMemoWrite();
                case 1:
                    return new FragmentCamera();


            }
            return null;
        }

        @Override
        public int getCount() {
            return this.tabSize;
        }
    }

}
