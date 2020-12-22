package com.daotian.memo5;

import android.text.TextWatcher;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

public class FragmentAdapter extends FragmentStatePagerAdapter  {
    private Fragment fragment;
    public static int position;

    public FragmentAdapter(@NonNull FragmentManager fm,int behavior) {
        super(fm,behavior);

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new fragmemo1();
            case 1:
                return new fragmemo2();
            case 2:
                return new fragmemo3();
            case 3:
                return new fragmemo4();
            case 4:
                return new fragmemo5();

        }
        return null;
    }


    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        super.setPrimaryItem(container, position, object);
        this.position=position;
        fragment=(Fragment)object;
    }

    public int getPosition(){
        return position;
    }


    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    public Fragment getFragment(){
        return fragment;
    }


    @Override
    public void finishUpdate(@NonNull ViewGroup container) {
        super.finishUpdate(container);
    }


}
