package com.awesome.kosyo.carserviceproject.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.awesome.kosyo.carserviceproject.R;
import com.awesome.kosyo.carserviceproject.fragments.LoginFragment;
import com.awesome.kosyo.carserviceproject.fragments.RegisterFragment;
import com.awesome.kosyo.carserviceproject.helpers.ApplicationConfiguration;
import com.awesome.kosyo.carserviceproject.helpers.SessionManager;
import com.awesome.kosyo.carserviceproject.interfaces.ApiInterface;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends BaseActivity implements LoginFragment.LoginRegisterListener,
        RegisterFragment.OnBtnRegisterListener {
    public final static String TAG = LoginActivity.class.getSimpleName();

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
    public void onLoginPrompted(String email, String password, final Button btnLogin) {
        btnLogin.setEnabled(false);

        RestAdapter adapter = new RestAdapter.Builder().
                setEndpoint(ApplicationConfiguration.BASE_URL).build();
        final ApiInterface restInterface = adapter.create(ApiInterface.class);

        restInterface.getAuthorization(ApplicationConfiguration.VERSION_VALUE,
                ApplicationConfiguration.ACCEPT_VALUE, email, password, new Callback<JsonObject>() {
                    @Override
                    public void success(JsonObject jsonObject, Response response) {
                        // Get token from jsonObject
                        JsonObject jsonDataObj = jsonObject.get(ApplicationConfiguration.DATA_ARRAY_KEY).getAsJsonObject();
                        String tokenFromApi = jsonDataObj.get(ApplicationConfiguration.TOKEN).getAsString();
                        SessionManager.getInstance().setLOGIN_TOKEN("Bearer " + tokenFromApi);

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);

                        // In order not to show this activity if user clicks the back button
                        finish();
                           }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.v(TAG, "In failure. RetrofitError:" + error);
                        Toast toast = Toast.makeText(getApplicationContext(), "Възникна грешка! Моля, опитайте отново.", Toast.LENGTH_LONG);
                        toast.show();
                        btnLogin.setEnabled(true);
                    }
                });
    }

    @Override
    public void onCreateRegistrationClicked() {
        RegisterFragment registerFragment = new RegisterFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction
                .addToBackStack(null)
                .replace(R.id.login_framelayout, registerFragment, RegisterFragment.TAG)
                .commit();
    }

    @Override
    public void onBtnRegisterClicked(final String email, String password, String password_confirmation, final Button btnRegister) {
        // Deactivate the Button
        btnRegister.setEnabled(false);

        RestAdapter adapter = new RestAdapter.Builder().
                setEndpoint(ApplicationConfiguration.BASE_URL).build();
        ApiInterface restInterface = adapter.create(ApiInterface.class);
        restInterface.addUser(ApplicationConfiguration.VERSION_VALUE, ApplicationConfiguration.ACCEPT_VALUE,
                email, password, password_confirmation, new Callback<JsonArray>() {
                    @Override
                    public void success(JsonArray jsonElements, Response response) {
                        // Log.v(TAG, "Response status is:" + response.getStatus());

                        Toast toast = Toast.makeText(getApplicationContext(), "Регистрирахте се успешно! Може да влезете в акаунта си.", Toast.LENGTH_LONG);
                        toast.show();

                        // Save email in SharedPreferences
                        SharedPreferences sharedPreferences = getApplicationContext()
                                .getSharedPreferences(getString(R.string.email_pref_key), Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(getString(R.string.user_email), email);
                        editor.commit();

                        // Load login Fragment
                        LoginFragment loginFragment = LoginFragment.getInstance();
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.addToBackStack(null)
                                .replace(R.id.login_framelayout, loginFragment, LoginFragment.TAG)
                                .commit();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //Enable the button again
                        btnRegister.setEnabled(true);
                        switch (error.getResponse().getStatus()) {
                            case 422: {
                                String message = "Вече съществува потребител" + "\n" + "с такъв е-мейл";
                                Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
                                toast.show();
                                break;
                            }
                            default: {
                                Toast toast = Toast.makeText(getApplicationContext(), "Възникна грешка! Моля, опитайте отново.", Toast.LENGTH_LONG);
                                toast.show();
                                break;
                            }

                        }
                        //Log.v(TAG, "In onBtnRegisterClicked - failure error:" + error);
                    }
                });
    }
}