package com.example.bookswap;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * adapter for sections
 * Home has owner, borrower, profile sections
 * Comment has owner. borrower sections
 */
public class SectionsPageAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    private Fragment mCurrent ;

    /**
     * get current visible fragment
     * @return
     */
    public Fragment getCurrentFragment(){
        return mCurrent;
    }

    /**
     * constructor
     * @param fm
     */
    public SectionsPageAdapter(FragmentManager fm){
        super(fm);
    }

    /**
     * get ith item in fragment list
     * @param i
     * @return
     */
    @Override
    public Fragment getItem(int i) {
        return mFragmentList.get(i);
    }

    /**
     * get length of fragment list
     * @return
     */
    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    /**
     * get fragment in position
     * @param position a position in fragment list
     * @return
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    /**
     * add a fragment to fragement list
     * @param f a fragment
     * @param title a string for title of this fragment
     */
    public void addFragment(Fragment f, String title){
        mFragmentList.add(f);
        mFragmentTitleList.add(title);
    }

    /**
     * refresh the current visible fragment
     * @param container parent view
     * @param position position in fragment list
     * @param object fragment
     */
    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.setPrimaryItem(container, position, object);
        if (mCurrent != object){
            mCurrent = ((Fragment)object);
        }
    }
}
