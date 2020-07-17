package vuong.hx.tayduky.ui.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.constants.ActorMenuItem;
import vuong.hx.tayduky.ui.fragments.actor.ActorAccountFragment;
import vuong.hx.tayduky.ui.fragments.actor.ActorChallenges;
import vuong.hx.tayduky.ui.fragments.actor.ActorNotificationFragment;

public class ActorHomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNav;
    private ViewPager2 viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_home);

        initViews();

    }

    private void initViews(){
        bottomNav = findViewById(R.id.bottom_nav);

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
                    case ActorMenuItem.HOME:
                        bottomNav.setSelectedItemId(R.id.mnHome);
                        break;
                    case ActorMenuItem.HISTORY:
                        bottomNav.setSelectedItemId(R.id.mnHis);

                        break;
                    case ActorMenuItem.NOTIFICATION:
                        bottomNav.setSelectedItemId(R.id.mnNotif);

                        break;
                    case ActorMenuItem.ACCOUNT:
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
                    case ActorMenuItem.HOME:
                        fragment = ActorChallenges.newInstance(true);
                        break;
                    case ActorMenuItem.HISTORY:
                        fragment = ActorChallenges.newInstance(false);
                        break;
                    case ActorMenuItem.NOTIFICATION:
                        fragment = new ActorNotificationFragment();
                        break;
                    case ActorMenuItem.ACCOUNT:
                        fragment = new ActorAccountFragment();
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + position);
                }
                return fragment;
            }

            @Override
            public int getItemCount() {
                return ActorMenuItem.ITEM_COUNT;
            }
        };


        viewPager.setAdapter(adapter);

    }

    private boolean setFragment(int itemId){
        switch (itemId){
            case R.id.mnHome:
                viewPager.setCurrentItem(ActorMenuItem.HOME);
                break;
            case R.id.mnAcc:
                viewPager.setCurrentItem(ActorMenuItem.ACCOUNT);
                break;
            case R.id.mnHis:
                viewPager.setCurrentItem(ActorMenuItem.HISTORY);
                break;
            case R.id.mnNotif:
                viewPager.setCurrentItem(ActorMenuItem.NOTIFICATION);
                break;
            default:
                break;
        }

        return true;
    }
}
