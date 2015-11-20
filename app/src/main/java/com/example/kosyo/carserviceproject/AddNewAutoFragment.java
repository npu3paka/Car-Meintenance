package com.example.kosyo.carserviceproject;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

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
    private String mDay;
    private String mMonth;
    private String mYear;
    private String mBirthDataText;


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

        btnNextService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSimpleDatePicker(v);
            }
        });

        btnInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSimpleDatePicker(v);
            }
        });

        btnMotorCasco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSimpleDatePicker(v);
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

    public void showSimpleDatePicker(View v) {
        DatePickerDialog.OnDateSetListener mDatePickerCallback = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mDay = getZeroPaddedNum(dayOfMonth);
                mMonth = getZeroPaddedNum(monthOfYear + 1);
                mYear = Integer.toString(year);
                //  mBirthDataText.setText(mYear + "-" + mMonth + "-" + mDay);
            }
        };

        DatePickerDialog mDatePickerDialog = new DatePickerDialog(getActivity(),
                mDatePickerCallback,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        // mDatePickerDialog.getDatePicker().setMaxDate(new Date().getTime() - MILLISECONDS_IN_YEAR * MIN_USER_YEARS);
        mDatePickerDialog.show();

    }

}
