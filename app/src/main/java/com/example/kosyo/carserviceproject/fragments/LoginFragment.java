package com.example.kosyo.carserviceproject.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.kosyo.carserviceproject.R;

/**
 * Created by kosyo on 19.11.15.
 */
public class LoginFragment extends Fragment {
    private LoginRegisterListener mListener;

    // Singleton implementation
    private static LoginFragment instance;
    public static LoginFragment getInstance() {
        if (instance == null){
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

        // Start MainActivity if user presses login button
        Button loginButton = (Button) view.findViewById(R.id.login_btn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onLoginPrompted("", "");
            }
        });

        return view;
    }


    public interface LoginRegisterListener {
        void onLoginPrompted(String username, String password);
        void onRegisterCLicked();
    }
}
