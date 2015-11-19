package com.example.kosyo.carserviceproject;

import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by kosyo on 19.11.15.
 */
public class AddNewAutoFragment extends Fragment {
    private static AddNewAutoFragment instance;
    private Model mAuto;
    private EditText etRegistrationNum;
    private EditText etCurrentKm;
    private EditText etKmToNextService;
    private Button btnNextService;
    private Button btnInsurance;
    private Button btnMotorCasco;
    private String registrationNum;
    private String currentService;
    private String kmToNextService;


    // Singleton implementation
    public static AddNewAutoFragment getInstance() {
        if (instance == null) {
            instance = new AddNewAutoFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addnewauto, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeLayoutElements(view);

        setOnClickListeners();

        if (etRegistrationNum != null && etCurrentKm != null && etKmToNextService != null) {
            mAuto.setmRegistrationNum(etRegistrationNum.getText().toString());
            if (!(etCurrentKm.getText().toString().equals("") &&
                    etKmToNextService.getText().toString().equals(""))) {
                mAuto.setmCurrentKm(Integer.parseInt(etCurrentKm.getText().toString()));
                mAuto.setmKmToNextService(Integer.parseInt(etKmToNextService.getText().toString()));
            }
        }
    }

    private void setOnClickListeners() {
        final DialogFragment dialogFragment = new DatePickerFragment();
        btnNextService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFragment.show(getFragmentManager(), "datePicker");
            }
        });

        btnInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFragment.show(getFragmentManager(), "datePicker");
            }
        });

        btnMotorCasco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFragment.show(getFragmentManager(), "datePicker");
            }
        });
    }

    private void initializeLayoutElements(View view) {
        mAuto = new Model();
        etRegistrationNum = (EditText) view.findViewById(R.id.etRegisrationNum);
        etCurrentKm = (EditText) view.findViewById(R.id.etCurrentKm);
        etKmToNextService = (EditText) view.findViewById(R.id.etKmToNextService);
        btnNextService = (Button) view.findViewById(R.id.btnNextService);
        btnInsurance = (Button) view.findViewById(R.id.btnInsurance);
        btnMotorCasco = (Button) view.findViewById(R.id.btnMotorCasco);
    }


}
