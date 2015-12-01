package com.awesome.kosyo.carserviceproject.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.awesome.kosyo.carserviceproject.R;

import java.util.ArrayList;

/**
 * Created by kosyo on 19.11.15.
 */
public class CurrentAutosFragment extends Fragment {
    public static final String TAG = CurrentAutosFragment.class.getSimpleName();
    private OnCarSelectedListener mListener;
    private ArrayList<String> ownedCarsDetails;
    private ListView ownedAutosListView;

    // Singleton implementation
    private static CurrentAutosFragment instance;

    public static CurrentAutosFragment getInstance() {
        if (instance == null) {
            instance = new CurrentAutosFragment();
        }
        return instance;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnCarSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnCarSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currentautos, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ownedAutosListView = (ListView) view.findViewById(R.id.lvCurrentAutos);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                ownedCarsDetails
        );
        ownedAutosListView.setAdapter(arrayAdapter);

        setOnClickListeners();
    }

    public void setOnClickListeners() {
        ownedAutosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.onCarClickedInCurrentAutosFragmentList(position);
            }
        });
    }

    // old wrong way
//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
//        mListener.onCarClickedInCurrentAutosFragmentList(position);
//    }

    public void setCars(ArrayList<String> cars) {
        this.ownedCarsDetails = cars;
    }


    public interface OnCarSelectedListener {
        void onCarClickedInCurrentAutosFragmentList(int positon);
    }
}
