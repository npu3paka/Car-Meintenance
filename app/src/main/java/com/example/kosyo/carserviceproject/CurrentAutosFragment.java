package com.example.kosyo.carserviceproject;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by kosyo on 19.11.15.
 */
public class CurrentAutosFragment extends Fragment {
    private static CurrentAutosFragment instance;

    // Singleton implementation
    public static CurrentAutosFragment getInstance(){
        if( instance == null){
            instance = new CurrentAutosFragment();
        }
        return instance;
    }

    // use arrayList as database
    private ArrayList<String> ownedAutos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currentautos, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ownedAutos = new ArrayList<>(Arrays.asList("A 0001 AA", "A 0002 AA"));
        ListView ownedAutosListView = (ListView) view.findViewById(R.id.owned_autos_listview);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                ownedAutos
        );
        ownedAutosListView.setAdapter(arrayAdapter);
    }
}
