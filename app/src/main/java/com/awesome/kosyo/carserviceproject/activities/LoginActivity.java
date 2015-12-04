package com.awesome.kosyo.carserviceproject.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.awesome.kosyo.carserviceproject.R;
import com.awesome.kosyo.carserviceproject.fragments.LoginFragment;
import com.awesome.kosyo.carserviceproject.fragments.RegisterFragment;
import com.awesome.kosyo.carserviceproject.gcmquickstart.QuickstartPreferences;
import com.awesome.kosyo.carserviceproject.gcmquickstart.RegistrationIntentService;
import com.awesome.kosyo.carserviceproject.helpers.ApplicationConfiguration;
import com.awesome.kosyo.carserviceproject.helpers.SessionManager;
import com.awesome.kosyo.carserviceproject.interfaces.ApiInterface;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends BaseActivity implements LoginFragment.LoginRegisterListener,
        RegisterFragment.OnBtnRegisterListener {
    public final static String TAG = LoginActivity.class.getSimpleName();
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private ProgressBar mRegistrationProgressBar;
    private TextView mInformationTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // mRegistrationProgressBar = (ProgressBar) findViewById(R.id.registrationProgressBar);
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // mRegistrationProgressBar.setVisibility(ProgressBar.GONE);
                SharedPreferences sharedPreferences =
                        PreferenceManager.getDefaultSharedPreferences(context);
                boolean sentToken = sharedPreferences
                        .getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false);
               // if (!sentToken) {
                    //   mInformationTextView.setText(getString(R.string.gcm_send_message));
                    if (checkPlayServices()) {
                        Log.d(TAG, "checkPlayServices");
                        // Start IntentService to register this application with GCM.
                        Intent intentService = new Intent(getApplicationContext(), RegistrationIntentService.class);
                        startService(intentService);
                    }
                //}
            }
        };
        //  mInformationTextView = (TextView) findViewById(R.id.informationTextView);


        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.login_framelayout, LoginFragment.getInstance(), LoginFragment.TAG)
                .commit();
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
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(QuickstartPreferences.REGISTRATION_COMPLETE));
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
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
                        switch (error.getResponse().getStatus()) {
                            case 401: {
                                String message = "Невалиден е-мейл или парола!";
                                Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
                                toast.show();
                                break;
                            }
                            default: {
                                Toast toast = Toast.makeText(getApplicationContext(), "Възникна грешка! Моля, опитайте отново.", Toast.LENGTH_LONG);
                                toast.show();
                                btnLogin.setEnabled(true);
                            }
                        }
                    }
                });
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