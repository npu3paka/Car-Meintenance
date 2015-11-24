package com.example.kosyo.carserviceproject.activities;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by kosyo on 20.11.15.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount()>0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
