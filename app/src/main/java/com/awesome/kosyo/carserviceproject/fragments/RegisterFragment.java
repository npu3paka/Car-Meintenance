package com.awesome.kosyo.carserviceproject.fragments;

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

import com.awesome.kosyo.carserviceproject.R;

/**
 * Created by kosyo on 24.11.15.
 */
public class RegisterFragment extends Fragment {
    public final static String TAG = RegisterFragment.class.getSimpleName();
    EditText mEmail;
    EditText mPassword;
    EditText mPasswordConfirm;
    Button mBtnRegister;
    private OnBtnRegisterListener mListener;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeLayoutElements(view);

        setOnClickListeners(view);
    }

    private void initializeLayoutElements(View view) {
        mEmail = (EditText) view.findViewById(R.id.etEmail);
        mPassword = (EditText) view.findViewById(R.id.etPassword);
        mPasswordConfirm = (EditText) view.findViewById(R.id.password_et_second);
        mBtnRegister = (Button) view.findViewById(R.id.btnRegister);
    }

    void setOnClickListeners(View view) {
        mBtnRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get values from EditTexts
                final String email = mEmail.getText().toString();
                final String password = mPassword.getText().toString();
                final String passwordConfirm = mPasswordConfirm.getText().toString();

                boolean isValid = isValuesValid(email, password, passwordConfirm);


                if (isValid) {
                    mListener.onBtnRegisterClicked(email, password, passwordConfirm, mBtnRegister);
                }
            }
        });
    }

    private boolean isValuesValid(String email, String password, String passwordSecondTime) {
        // is email and password valid
        if (email == null || email.length() == 0) {
            Toast toast = Toast.makeText(getActivity(), "Your e-mail field is empty", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if (!isEmailValid(email)) {
            // if mail is not valid
            Toast toast = Toast.makeText(getActivity(), "Invalid e-mail", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if (password == null || password.length() == 0) {
            Toast toast = Toast.makeText(getActivity(), "Your password field is empty", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if (password.length() < 6) {
            Toast toast = Toast.makeText(getActivity(), "Your password must be atleast 6 characters", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if (!password.equals(passwordSecondTime)) {
            Toast toast = Toast.makeText(getActivity(), "You passwords doesn't match", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        return true;
    }

    private boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public interface OnBtnRegisterListener {

        void onBtnRegisterClicked(String email, String password, String password_confirmation, Button btnRegister);
    }
}
