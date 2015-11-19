package com.example.kosyo.carserviceproject;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by kosyo on 19.11.15.
 */
public class LoginFragment extends Fragment {

    // Singleton implementation
    private static LoginFragment instance;

    public static LoginFragment getInstance() {
        if (instance == null){
            instance = new LoginFragment();
        }
        return instance;
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
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

                // In order not to be show this activity if user clicks the back button
                getActivity().finish();
            }
        });
        return view;
    }
}
