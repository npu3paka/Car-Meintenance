package com.example.kosyo.carserviceproject;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by kosyo on 19.11.15.
 */
public class MainFragment extends Fragment {
    private LinearLayout mCurrentAutosLayout;
    private ArrayList<String> carPartsOverdue;

    // Singleton implementation
    private static MainFragment instance;

    public static MainFragment getInstance() {
        if (instance == null) {
            instance = new MainFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        carPartsOverdue = new ArrayList<String>(Arrays.asList("A 0001 AA", "A 0002 AA", "A 0003 AA",
                "A 0004 AA", "A 0005 AA", "A 0006 AA", "A 0007 AA", "A 0008 AA", "A 0009 AA", "A 0010 AA"));

        ListView overdueItemsListView = (ListView) view.findViewById(R.id.overdue_items_listview);
        ArrayAdapter<String> overduePartsArrayAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                carPartsOverdue
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
    }

    private void setOnClickListeners() {
        mCurrentAutosLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getActivity(), "Clicked current autos linear layout", Toast.LENGTH_LONG);
                toast.show();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.hide(getFragmentManager().findFragmentByTag("MainFragment"));
                fragmentTransaction.add(R.id.main_framelayout, new CurrentAutosFragment(), "CurrentAutosFragment")
                        .commit();
            }
        });
    }
}
