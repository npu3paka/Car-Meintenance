package com.example.kosyo.carserviceproject.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kosyo.carserviceproject.R;

/**
 * Created by kosyo on 24.11.15.
 */
public class RegisterFragment extends Fragment {
    public final static String TAG = RegisterFragment.class.getSimpleName();
    private OnBtnRegisterListener mListener;
    EditText mUsername;
    EditText mPassword;
    EditText mPasswordConfirm;
    Button mBtnRegister;

    // Singleton implementation
    private static RegisterFragment instance;

    public static RegisterFragment getInstance() {
        if (instance == null) {
            instance = new RegisterFragment();
        }
        return instance;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnBtnRegisterListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnBtnRegisterListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeLayoutElements(view);

        final String username = mUsername.getText().toString();
        final String password = mPassword.getText().toString();
        final String passwordConfirm = mPasswordConfirm.getText().toString();

        mBtnRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isValid = isValuesValid(username, password, passwordConfirm);
                if (isValid) {
                    mListener.onBtnRegisterClicked();
                }
            }
        });
    }

    private boolean isValuesValid(String email, String password, String passwordSecondTime) {
        // is email and password valid
        if (email == null && email.length() == 0) {
            Toast toast = Toast.makeText(getActivity(), "Your e-mail field is empty", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        } else if (isEmailValid(email)) {
            // if mail is not valid
            Toast toast = Toast.makeText(getActivity(), "Invalid e-mail", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        } else if (password == null && password.length() == 0) {
            Toast toast = Toast.makeText(getActivity(), "Your password field is empty", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        } else if (password.length() < 6) {
            Toast toast = Toast.makeText(getActivity(), "Your password must be atleast 6 characters", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        } else if (password.equals(passwordSecondTime)) {
            // All data is valid
            return true;
        }
        return false;
    }

    private void initializeLayoutElements(View view) {
        mUsername = (EditText) view.findViewById(R.id.user_et);
        mPassword = (EditText) view.findViewById(R.id.password_et);
        mPasswordConfirm = (EditText) view.findViewById(R.id.password_et_second);
        mBtnRegister = (Button) view.findViewById(R.id.btnRegister);
    }

    private boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;
    }

    public interface OnBtnRegisterListener {
        void onBtnRegisterClicked();
    }
}
