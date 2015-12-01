package com.awesome.kosyo.carserviceproject.fragments;

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
import android.widget.Toast;

import com.awesome.kosyo.carserviceproject.R;
import com.awesome.kosyo.carserviceproject.models.Vehicle;

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
    private String mNextYearlyTechnicalServiceDate;
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
                //Create new vehicle object and pass it to the activity

                boolean isInputValid = isInputValid();
                // TODO: Uncomment before putting in production
                // if (isInputValid) {
                mVehicle = new Vehicle(mRegistrationNum, mCurrentKm, mKmToNextService,
                        mInsurance, mNextService, mMotorCasco, mNextYearlyTechnicalServiceDate, mRoadTax);

                mListener.onAddVehicleClicked(mVehicle);
                // }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onCancelClicked();
            }
        });
    }

    private boolean isInputValid() {
        // Get input from fragment
        mRegistrationNum = etRegistrationNum.getText().toString();
        mInsurance = tvInsurance.getText().toString();
        mNextService = tvNextService.getText().toString();
        mMotorCasco = tvMotorCasco.getText().toString();
        mNextYearlyTechnicalServiceDate = tvCarService.getText().toString();
        mRoadTax = tvRoadTax.getText().toString();

        if (mRegistrationNum == null || mRegistrationNum.length() == 0) {
            Toast toast = Toast.makeText(getActivity(), "Registration number filed is empty", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }

        // Check first if user set values in etCurrentKm and tvKmToNextService
        // before trying to parse them to int
        String valueCurrentKmString = etCurrentKm.getText().toString();
        if (valueCurrentKmString == null || valueCurrentKmString.length() == 0) {
            Toast toast = Toast.makeText(getActivity(), "Enter the number of km your car is", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        } else {
            try {
                // User inputted some value. Get it and parcs it to int
                mCurrentKm = Integer.parseInt(valueCurrentKmString);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        String valueKmToNextServiceString = tvKmToNextService.getText().toString();
        if (valueKmToNextServiceString == null || valueKmToNextServiceString.length() == 0) {
            Toast toast = Toast.makeText(getActivity(), "Enter on what km you car needs to be serviced", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        } else {
            try {
                // User inputted some value. Get it and parcs it to int
                mKmToNextService = Integer.parseInt(valueKmToNextServiceString);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        if (mCurrentKm < 0)

        {
            Toast toast = Toast.makeText(getActivity(), "Km must be positive", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }

        if (mKmToNextService < mCurrentKm)

        {
            Toast toast = Toast.makeText(getActivity(), "Km for next service must be greater than  Current killometers", Toast.LENGTH_LONG);
            toast.show();
            return false;
        }

        if (mNextService == null || mNextService.length() == 0)

        {
            Toast toast = Toast.makeText(getActivity(), "Set next service data", Toast.LENGTH_LONG);
            toast.show();
            return false;
        }

        if (mInsurance == null || mInsurance.length() == 0)

        {
            Toast toast = Toast.makeText(getActivity(), "Set next insurance data", Toast.LENGTH_LONG);
            toast.show();
            return false;
        }

        if (mMotorCasco == null || mMotorCasco.length() == 0)

        {
            Toast toast = Toast.makeText(getActivity(), "Set next motor casco data", Toast.LENGTH_LONG);
            toast.show();
            return false;
        }

        if (mNextYearlyTechnicalServiceDate == null || mNextYearlyTechnicalServiceDate.length() == 0)

        {
            Toast toast = Toast.makeText(getActivity(), "Set next yearly technical service data", Toast.LENGTH_LONG);
            toast.show();
            return false;
        }

        if (mRoadTax == null || mRoadTax.length() == 0)

        {
            Toast toast = Toast.makeText(getActivity(), "Set next road tax data", Toast.LENGTH_LONG);
            toast.show();
            return false;
        }
        // Input is valid
        return true;
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

        // Set the available dates to be after today
        Calendar rightNow = Calendar.getInstance();
        mDatePickerDialog.getDatePicker().setMinDate(rightNow.getTimeInMillis() - 1000);

        mDatePickerDialog.show();
    }

    public interface OnCreateVehicleClicked {
        void onAddVehicleClicked(Vehicle vehicle);

        void onCancelClicked();
    }

}
