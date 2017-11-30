package com.example.android.miwok;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by Annie on 8/11/2017.
 */

public class SimpleFragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

    private Context contxt;

    public SimpleFragmentPagerAdapter(Context contxt, FragmentManager fm) {
        super(fm);
        this.contxt = contxt;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NumbersFragment();
        } else if (position == 1){
            return new ColorsFragment();
        } else if (position == 2) {
            return new FamilyFragment();
        }
        else {
            return new PhrasesFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return contxt.getString(R.string.category_numbers);
        } else if (position == 1){
            return contxt.getString(R.string.category_colors);
        } else if (position == 2) {
            return contxt.getString(R.string.category_family);
        }
        else {
            return contxt.getString(R.string.category_phrases);
        }
    }
}
