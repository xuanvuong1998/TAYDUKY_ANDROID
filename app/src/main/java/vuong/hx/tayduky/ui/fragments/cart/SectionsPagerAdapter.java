package vuong.hx.tayduky.ui.fragments.cart;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static final String[] TAB_TITLES = new String[]{"Scene Roles", "Scene Tools"};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment curFrag;
        if (position == 0){
            curFrag = new CartRolesFragment();
        }else if (position == 1){
            curFrag = new CartToolsFragment();
        }else{
            curFrag = new PlaceholderFragment();
        }

        return curFrag;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return (TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return TAB_TITLES.length;
    }
}