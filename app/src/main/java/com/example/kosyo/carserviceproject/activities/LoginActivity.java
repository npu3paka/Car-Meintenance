package com.example.kosyo.carserviceproject.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import com.example.kosyo.carserviceproject.R;
import com.example.kosyo.carserviceproject.fragments.LoginFragment;
import com.example.kosyo.carserviceproject.fragments.RegisterFragment;

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
        RegisterFragment registerFragment = RegisterFragment.getInstance();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.login_framelayout, registerFragment, RegisterFragment.TAG)
                .commit();
    }

    @Override
    public void onBtnRegisterClicked() {
            // TODO: Do smth with the newly created account
    }
}