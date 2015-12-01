package com.awesome.kosyo.carserviceproject.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import com.awesome.kosyo.carserviceproject.R;
import com.awesome.kosyo.carserviceproject.fragments.LoginFragment;
import com.awesome.kosyo.carserviceproject.fragments.RegisterFragment;

public class LoginActivity extends BaseActivity implements LoginFragment.LoginRegisterListener, RegisterFragment.OnBtnRegisterListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.login_framelayout, LoginFragment.getInstance(), LoginFragment.TAG)
                .commit();
    }

    @Override
    public void onLoginPrompted(String username, String password) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        // In order not to show this activity if user clicks the back button
        finish();
    }

    @Override
    public void onCreateRegisterationClicked() {
        RegisterFragment registerFragment = new RegisterFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction
                .addToBackStack(null)
                .replace(R.id.login_framelayout, registerFragment, RegisterFragment.TAG)
                .commit();
    }

    @Override
    public void onBtnRegisterClicked() {
            // TODO: add the new user registration to database
    }
}