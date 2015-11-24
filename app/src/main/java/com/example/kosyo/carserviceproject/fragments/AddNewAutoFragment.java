package com.example.kosyo.carserviceproject.fragments;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.kosyo.carserviceproject.R;
import com.example.kosyo.carserviceproject.models.Vehicle;

import java.util.Calendar;

/**
 * Created by kosyo on 19.11.15.
 */
public class AddNewAutoFragment extends Fragment {
    public static final String TAG = AddNewAutoFragment.class.getSimpleName();
    private static AddNewAutoFragment instance;
    Vehicle mVehicle;
    private EditText etRegistrationNum;
    private EditText etCurrentKm;
    private EditText etKmToNextService;
    private EditText etNextService;
    private EditText etInsurance;
    private EditText etMotorCasco;
    private EditText etCarService;
    private EditText etRoadTax;
    private String mDay;
    private String mMonth;
    private String mYear;

    // TODO: change type of datepicker to textview
    // TODO: add btn to save ne car data and add it to fake database

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
            if (!etRegistrationNum.toString().equals("") &&
                    !etCurrentKm.getText().toString().equals("") &&
                    !etKmToNextService.getText().toString().equals("")) {
                mVehicle.setmRegistrationNum(etRegistrationNum.getText().toString());
                mVehicle.setmCurrentKm(Integer.parseInt(etCurrentKm.getText().toString()));
                mVehicle.setmKmToNextService(Integer.parseInt(etKmToNextService.getText().toString()));
            }
        }
    }

    private void setOnClickListeners() {

        etNextService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSimpleDatePicker(v, etNextService);
                mVehicle.setmNextServiceDate(etKmToNextService.getText().toString());
            }
        });

        etInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSimpleDatePicker(v, etInsurance);
                mVehicle.setmNextInsuranceDate(etInsurance.getText().toString());
            }
        });

        etMotorCasco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSimpleDatePicker(v, etMotorCasco);
                mVehicle.setmNextMotorCascoDate(etMotorCasco.getText().toString());
            }
        });
        etCarService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSimpleDatePicker(v, etCarService);
                mVehicle.setmNextCarServiceDate(etCarService.getText().toString());
            }
        });

        etRoadTax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSimpleDatePicker(v, etRoadTax);
                mVehicle.setmNextRoadTaxDate(etRoadTax.getText().toString());
            }
        });
    }

    private void initializeLayoutElements(View view) {
        mVehicle = new Vehicle();
        etRegistrationNum = (EditText) view.findViewById(R.id.etRegisrationNum);
        etCurrentKm = (EditText) view.findViewById(R.id.etCurrentKm);
        etKmToNextService = (EditText) view.findViewById(R.id.etKmToNextService);
        etNextService = (EditText) view.findViewById(R.id.etNextService);
        etInsurance = (EditText) view.findViewById(R.id.etInsurance);
        etMotorCasco = (EditText) view.findViewById(R.id.etMotorCasco);
        etCarService = (EditText) view.findViewById(R.id.etCarService);
        etRoadTax = (EditText) view.findViewById(R.id.etRoadTax);
    }

    /**
     * method for taking the correct value of date picker
     */
    public static String getZeroPaddedNum(int num) {
        String str;
        if (num < 10 && num >= 0) {
            str = "0" + num;
        } else {
            str = String.valueOf(num);
        }
        return str;
    }

    public void showSimpleDatePicker(View v, final EditText editText) {
        DatePickerDialog.OnDateSetListener mDatePickerCallback = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mDay = getZeroPaddedNum(dayOfMonth);
                mMonth = getZeroPaddedNum(monthOfYear + 1);
                mYear = Integer.toString(year);
                editText.setText(mDay + "-" + mMonth + "-" + mYear);
            }
        };

        DatePickerDialog mDatePickerDialog = new DatePickerDialog(getActivity(),
                mDatePickerCallback,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        mDatePickerDialog.show();
    }
}
