package com.example.kosyo.carserviceproject.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kosyo.carserviceproject.R;
import com.example.kosyo.carserviceproject.models.Vehicle;

import java.util.Calendar;

/**
 * Created by kosyo on 19.11.15.
 */
public class AddNewAutoFragment extends Fragment {
    public static final String TAG = AddNewAutoFragment.class.getSimpleName();
    private OnCreateVehicleClicked mListener;
    private Vehicle mVehicle;

    private Button btnCreateVehicle;
    private Button btnCancel;
    private EditText etRegistrationNum;
    private EditText etCurrentKm;
    private TextView tvKmToNextService;
    private TextView tvNextService;
    private TextView tvInsurance;
    private TextView tvMotorCasco;
    private TextView tvCarService;
    private TextView tvRoadTax;
    private String mDay;
    private String mMonth;
    private String mYear;

    private String mRegistrationNum;
    private int mCurrentKm;
    private int mKmToNextService;
    private String mNextService;
    private String mInsurance;
    private String mMotorCasco;
    private String mCarService;
    private String mRoadTax;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnCreateVehicleClicked) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnCreateVehicleClicked) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
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

    }

    //    private boolean validateInput() {
//        if (etRegistrationNum != null && etCurrentKm != null && tvKmToNextService != null) {
//            if (!etRegistrationNum.toString().equals("") &&
//                    !etCurrentKm.getText().toString().equals("") &&
//                    !tvKmToNextService.getText().toString().equals("")) {
//            }
//            return true;
//        }
//        return false;
//    }

    private void initializeLayoutElements(View view) {
        btnCreateVehicle = (Button) view.findViewById(R.id.btnCreateVehicle);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        etRegistrationNum = (EditText) view.findViewById(R.id.etRegisrationNum);
        etCurrentKm = (EditText) view.findViewById(R.id.etCurrentKm);
        tvKmToNextService = (EditText) view.findViewById(R.id.etKmToNextService);
        tvNextService = (TextView) view.findViewById(R.id.etNextService);
        tvInsurance = (TextView) view.findViewById(R.id.etInsurance);
        tvMotorCasco = (TextView) view.findViewById(R.id.etMotorCasco);
        tvCarService = (TextView) view.findViewById(R.id.etCarService);
        tvRoadTax = (TextView) view.findViewById(R.id.etRoadTax);
    }

    private void setOnClickListeners() {

        tvNextService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSimpleDatePicker(v, tvNextService);
            }
        });

        tvInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSimpleDatePicker(v, tvInsurance);
            }
        });

        tvMotorCasco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSimpleDatePicker(v, tvMotorCasco);
            }
        });
        tvCarService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSimpleDatePicker(v, tvCarService);
            }
        });

        tvRoadTax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSimpleDatePicker(v, tvRoadTax);
            }
        });

        btnCreateVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //boolean isInputValid = validateInput();
                // TODO: do smth with the validation

                //Create new vehicle object and pass it to the activity
                mRegistrationNum = etRegistrationNum.getText().toString();
                mCurrentKm = Integer.parseInt(etCurrentKm.getText().toString());
                mKmToNextService = Integer.parseInt(tvKmToNextService.getText().toString());
                mInsurance = tvInsurance.getText().toString();
                mNextService = tvNextService.getText().toString();
                mMotorCasco = tvMotorCasco.getText().toString();
                mCarService = tvCarService.getText().toString();
                mRoadTax = tvRoadTax.getText().toString();

                mVehicle = new Vehicle(mRegistrationNum, mCurrentKm, mKmToNextService,
                        mInsurance, mNextService, mMotorCasco, mCarService, mRoadTax);

                mListener.onAddVehicleClicked(mVehicle);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onCancelClicked();
            }
        });
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

    public void showSimpleDatePicker(View v, final TextView textView) {
        DatePickerDialog.OnDateSetListener mDatePickerCallback = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mDay = getZeroPaddedNum(dayOfMonth);
                mMonth = getZeroPaddedNum(monthOfYear + 1);
                mYear = Integer.toString(year);
                textView.setText(mDay + "-" + mMonth + "-" + mYear);
            }
        };

        DatePickerDialog mDatePickerDialog = new DatePickerDialog(getActivity(),
                mDatePickerCallback,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        mDatePickerDialog.show();
    }

    public interface OnCreateVehicleClicked {
        void onAddVehicleClicked(Vehicle vehicle);

        void onCancelClicked();
    }

}
