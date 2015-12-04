package com.awesome.kosyo.carserviceproject.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.awesome.kosyo.carserviceproject.R;

/**
 * Created by kosyo on 19.11.15.
 */
public class LoginFragment extends Fragment {
    public final static String TAG = LoginFragment.class.getSimpleName();
    private SharedPreferences sharedPreferences;
    private LoginRegisterListener mListener;
    private Button btnLogin;
    private Button btnRegister;
    private EditText etEmail;
    private EditText etPassword;
    private String mEmail;
    private String mPassword;

    // Singleton implementation
    private static LoginFragment instance;

    public static LoginFragment getInstance() {
        if (instance == null) {
            instance = new LoginFragment();
        }
        return instance;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (LoginRegisterListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (LoginRegisterListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        etEmail = (EditText) view.findViewById(R.id.etEmail);
        etPassword = (EditText) view.findViewById(R.id.etPassword);

        // Sets the user email to the TextView if previously
        // was save in SharedPreferences
        sharedPreferences = getActivity().getSharedPreferences(getString(R.string.email_pref_key), Context.MODE_PRIVATE);
        if (sharedPreferences.contains(getString(R.string.email_pref_key))) {
            String defaultEmail = "";
            mEmail = sharedPreferences.getString(getString(R.string.user_email), defaultEmail);
            etEmail.setText(mEmail);
        }

        setOnClickListeners(view);

        return view;
    }

    private void setOnClickListeners(View view) {
        // Start MainActivity if user presses login button
        btnLogin = (Button) view.findViewById(R.id.login_btn);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get values from the EditTexts
                mEmail = etEmail.getText().toString();
                mPassword = etPassword.getText().toString();

                boolean isInputValid = isValuesValid(mEmail, mPassword);
                if (isInputValid) {
                    // Save email in SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(getString(R.string.user_email), mEmail);
                    editor.commit();
                    mListener.onLoginPrompted(mEmail, mPassword, btnLogin);
                }
            }
        });

        btnRegister = (Button) view.findViewById(R.id.register_btn);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onCreateRegistrationClicked();
            }
        });
    }


    private boolean isValuesValid(String email, String password) {
        // is email and password valid
        if (email == null || email.length() == 0) {
            Toast toast = Toast.makeText(getActivity(), "Не сте въвели е-мейл", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if (!isEmailValid(email)) {
            // if mail is not valid
            Toast toast = Toast.makeText(getActivity(), "Невалиден е-мейл", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if (password == null || password.length() == 0) {
            Toast toast = Toast.makeText(getActivity(), "Полето 'парола' е празно", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if (password.length() < 6) {
            Toast toast = Toast.makeText(getActivity(), "Паролата ви трябва да е поне 6 символа дълга", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        // Input is valid
        return true;
    }

    private boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    public interface LoginRegisterListener {
        void onLoginPrompted(String email, String password, Button btnLogin);

        void onCreateRegistrationClicked();
    }
}
