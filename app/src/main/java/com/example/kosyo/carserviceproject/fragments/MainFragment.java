package com.example.kosyo.carserviceproject.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.kosyo.carserviceproject.R;

import java.util.ArrayList;

/**
 * Created by kosyo on 19.11.15.
 */
public class MainFragment extends Fragment {
    public static final String TAG = MainFragment.class.getSimpleName();
    private newFragmentListener mListener;
    private LinearLayout mCurrentAutosLayout;
    private LinearLayout mAddNewAutoLayout;
    // All current ownedCarsDetails the user have. Use as database
    private ArrayList<String> regNumOwnedCarsList;

    // Singleton implementation
    private static MainFragment instance;

    public static MainFragment getInstance() {
        if (instance == null) {
            instance = new MainFragment();
        }
        return instance;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (newFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (newFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ListView overdueItemsListView = (ListView) view.findViewById(R.id.overdue_items_listview);
        ArrayAdapter<String> overduePartsArrayAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                this.regNumOwnedCarsList
        );
        overdueItemsListView.setAdapter(overduePartsArrayAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeLayoutElements(view);
        setOnClickListeners();
    }

    private void initializeLayoutElements(View view) {
        mCurrentAutosLayout = (LinearLayout) view.findViewById(R.id.current_autos_layout);
        mAddNewAutoLayout = (LinearLayout) view.findViewById(R.id.add_automobile_layout);
    }

    // TODO: set listenrs for the listview
    private void setOnClickListeners() {
        mAddNewAutoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onAddNewAutoFragmentClicked();
            }
        });
        mCurrentAutosLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onCurrentAutoFragmentClicked();
            }
        });
    }

    public void setRegNumOwnedCarsList(ArrayList<String> regNumOwnedCarsList) {
        this.regNumOwnedCarsList = regNumOwnedCarsList;
    }

    public interface newFragmentListener {
        void onAddNewAutoFragmentClicked();

        void onCurrentAutoFragmentClicked();
    }
}
