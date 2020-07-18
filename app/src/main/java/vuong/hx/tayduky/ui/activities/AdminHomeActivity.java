package vuong.hx.tayduky.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.constants.AdminMenuItem;
import vuong.hx.tayduky.ui.fragments.admin.AdminAccountFragment;
import vuong.hx.tayduky.ui.fragments.admin.AdminChallengesFragment;
import vuong.hx.tayduky.ui.fragments.admin.AdminCharactersFragment;
import vuong.hx.tayduky.ui.fragments.admin.AdminNotificationFragment;
import vuong.hx.tayduky.ui.fragments.admin.AdminToolsFragment;

public class AdminHomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNav;
    private ViewPager2 viewPager;
    private FloatingActionButton mFab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        initViews();

    }

    private void initViews(){
        bottomNav = findViewById(R.id.bottom_nav);
        mFab = findViewById(R.id.fabCart);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this, TodoCartActivity.class);

                startActivity(intent);
            }
        });

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return setFragment(item.getItemId());
            }
        });

        viewPager = findViewById(R.id.viewPagerActor);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                switch (position){
                    case AdminMenuItem.CHALLENGE:
                        bottomNav.setSelectedItemId(R.id.mnHome);
                        break;
                    case AdminMenuItem.ACTORS:
                        bottomNav.setSelectedItemId(R.id.mnActors); 
                        break;
                    case AdminMenuItem.TOOLS:
                        bottomNav.setSelectedItemId(R.id.mnTools);

                        break;
                    case AdminMenuItem.NOTIFICATION:
                        bottomNav.setSelectedItemId(R.id.mnNotif);

                        break;
                    case AdminMenuItem.ACCOUNT:
                        bottomNav.setSelectedItemId(R.id.mnAcc);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + position);
                }
            }
        });

        setupViewPager();
    }

    private void setupViewPager(){
        FragmentStateAdapter adapter = new FragmentStateAdapter(this) {

            Fragment fragment = null;
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                switch (position){
                    case AdminMenuItem.CHALLENGE:
                        fragment = new AdminChallengesFragment();
                        break;
                    case AdminMenuItem.ACTORS:
                        fragment = new AdminCharactersFragment();
                        break;
                    case AdminMenuItem.TOOLS:
                        fragment = new AdminToolsFragment();
                        break;
                    case AdminMenuItem.NOTIFICATION:
                        fragment = new AdminNotificationFragment();
                        break;
                    case AdminMenuItem.ACCOUNT:
                        fragment = new AdminAccountFragment();
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + position);
                }
                return fragment;
            }

            @Override
            public int getItemCount() {
                return AdminMenuItem.ITEM_COUNT;
            }
        };


        viewPager.setAdapter(adapter);

    }

    private boolean setFragment(int itemId){
        switch (itemId){
            case R.id.mnHome:
                viewPager.setCurrentItem(AdminMenuItem.CHALLENGE);
                break;
            case R.id.mnAcc:
                viewPager.setCurrentItem(AdminMenuItem.ACCOUNT);
                break;
            case R.id.mnActors:
                viewPager.setCurrentItem(AdminMenuItem.ACTORS);
                break;
            case R.id.mnNotif:
                viewPager.setCurrentItem(AdminMenuItem.NOTIFICATION);
                break;
            case R.id.mnTools:
                viewPager.setCurrentItem(AdminMenuItem.TOOLS);
                break;
            default:
                break;
        }

        return true;
    }
}
