package com.example.kosyo.carserviceproject.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.kosyo.carserviceproject.R;
import com.example.kosyo.carserviceproject.adapters.DetailArrayAdapter;
import com.example.kosyo.carserviceproject.models.Vehicle;
import com.example.kosyo.carserviceproject.models.VehicleAttribute;

import java.util.Calendar;
import java.util.List;

/**
 * Created by kosyo on 20.11.15.
 */
public class DetailAutoFragment extends Fragment implements DetailArrayAdapter.UpdatingListListener {
    public static final String TAG = DetailAutoFragment.class.getSimpleName();
    public DetailArrayAdapter mDetailArrayAdapter;
    public DataInteractionListener dataInteractionListener;
    private ListView lvDetailAuto;
    private Vehicle vehicleObj;
    int size;

    // Singleton implementation
    private static DetailAutoFragment instance;
    public static DetailAutoFragment getInstance() {
        if (instance == null) {
            instance = new DetailAutoFragment();
        }
        return instance;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            dataInteractionListener = (DataInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            dataInteractionListener = (DataInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detailauto, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvDetailAuto = (ListView) view.findViewById(R.id.lvDetailAuto);

        // This will only show if there is no data in the listview
        lvDetailAuto.setEmptyView(view.findViewById(R.id.tvEmpty));

        mDetailArrayAdapter = new DetailArrayAdapter(getActivity(), vehicleObj.getVehicleAttributesList());

        // Setting
        mDetailArrayAdapter.setUpdatingListener(this);

        lvDetailAuto.setAdapter(mDetailArrayAdapter);
    }


    public void setVehicleObj(Vehicle vehicleObj) {
        this.vehicleObj = vehicleObj;
    }


    /**
     * Method for displaying Data Picker
     */
    @Override
    public void showSimpleDatePicker(final int position, String currentDate, final DetailArrayAdapter.DataUpdatingListener listener) {
        DatePickerDialog.OnDateSetListener mDatePickerCallback = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String mDay = getZeroPaddedNum(dayOfMonth);
                String mMonth = getZeroPaddedNum(monthOfYear + 1);
                String mYear = Integer.toString(year);
                String newDateString = mDay + "-" + mMonth + "-" + mYear;
                listener.dataUpdated(newDateString);

                List<VehicleAttribute> vehicleAttributeList = vehicleObj.getVehicleAttributesList();

                // Logs
                String val;
                for(int i=0; i< vehicleAttributeList.size(); i++){
                    val =  vehicleAttributeList.get(i).getmValue();
                    Log.v(TAG, "Val: "+ i + " :" + val);
                }
                String oldDate = vehicleAttributeList.get(position).getmValue();
                Log.i(TAG, "OLD DATA :" + oldDate);


                vehicleAttributeList.get(position).setmValue(newDateString);
                //TODO Update on server
                //then update the list
                vehicleObj.updateVehicleByAttributes(vehicleAttributeList);

                // Logs
                String str2;
                for(int i=0; i< vehicleAttributeList.size(); i++){
                    str2 =  vehicleAttributeList.get(i).getmValue();
                    Log.v(TAG, " New Val: "+ i + " :" + str2);
                }

                dataInteractionListener.saveData(vehicleObj);
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

    public interface DataInteractionListener {
        void saveData(Vehicle vehicle);
    }

}
