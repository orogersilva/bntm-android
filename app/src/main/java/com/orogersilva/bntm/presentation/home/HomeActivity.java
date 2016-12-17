package com.orogersilva.bntm.presentation.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.orogersilva.bntm.R;
import com.orogersilva.bntm.presentation.home.view.HomeFragment;
import com.orogersilva.bntm.util.ActivityUtils;

/**
 * Created by orogersilva on 12/17/2016.
 */

public class HomeActivity extends AppCompatActivity {

    // region FIELDS

    private HomePresenter mHomePresenter;
    private Toolbar mToolbar;

    // endregion

    // region ACTIVITY LIFECYCLE METHODS

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");

        setSupportActionBar(mToolbar);

        HomeFragment homeFragment = (HomeFragment)
                getSupportFragmentManager().findFragmentById(R.id.contentFrameLayout);

        if (homeFragment == null) {

            homeFragment = HomeFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), homeFragment,
                    R.id.contentFrameLayout);
        }

        mHomePresenter = new HomePresenter(homeFragment);
    }

    // endregion
}
