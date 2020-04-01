package com.fendy.covid19;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;

import com.fendy.covid19.fragment.CaseFragment;
import com.fendy.covid19.fragment.DeadFragment;
import com.fendy.covid19.fragment.GetWellFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * created by phephen 2020
 */

public class MainActivity extends AppCompatActivity {

    public ViewPager viewPager;
    private TabLayout tabLayout;
    TextView username, division, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (TextView) findViewById(R.id.tvName);
        username.setText("Kawal");
        username.setTextColor(R.color.headerColor);

        division = (TextView) findViewById(R.id.tvDivision);
        division.setText("Corona");
        division.setTextColor(R.color.headerColor);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CaseFragment(), "Indonesia");
//        adapter.addFragment(new GetWellFragment(), "Kasus");
//        adapter.addFragment(new DeadFragment(), "Meninggal");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public static void ShowFragment(int resId, Fragment fragment, FragmentManager fm) {
        final FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(resId, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
