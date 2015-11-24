package com.example.kosyo.carserviceproject.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.kosyo.carserviceproject.R;
import com.example.kosyo.carserviceproject.activities.MainActivity;
import com.example.kosyo.carserviceproject.adapters.DetailArrayAdapter;
import com.example.kosyo.carserviceproject.models.VehicleAtribute;

import java.util.ArrayList;

/**
 * Created by kosyo on 20.11.15.
 */
public class DetailAutoFragment extends Fragment {
    public static final String TAG = DetailAutoFragment.class.getSimpleName();
    public DetailArrayAdapter mDetailArrayAdapter;
    private ListView lvDetailAuto;
    // Use as database
    private ArrayList<VehicleAtribute> mCarInsuranceList;
    private ArrayList<String> vehicleAttributesValues;

    // Singleton implementation
    private static DetailAutoFragment instance;

    public static DetailAutoFragment getInstance() {
        if (instance == null) {
            instance = new DetailAutoFragment();
        }
        return instance;
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

        // Create a list of objects to later pass them in the arrayadapter
        int size = MainActivity.vehicleAttributesNames.size();
        mCarInsuranceList = new ArrayList<VehicleAtribute>();
        for (int i = 0; i < size; i++) {
            VehicleAtribute carInsurance = new VehicleAtribute();
            carInsurance.setmName(MainActivity.vehicleAttributesNames.get(i));
            carInsurance.setmValue(vehicleAttributesValues.get(i));
            mCarInsuranceList.add(carInsurance);
        }
        //Log.d(TAG, "List : " + mCarInsuranceList.toString());
        mDetailArrayAdapter = new DetailArrayAdapter(getActivity(), mCarInsuranceList);
        lvDetailAuto.setAdapter(mDetailArrayAdapter);
    }

    public void setVehicleAttributesValues(ArrayList<String> vehicleAttributesValues) {
        this.vehicleAttributesValues = vehicleAttributesValues;
    }

    // TODO: Repair
//    public void onListItemClick(ListView l, View v, int position, long id) {
//       // super.onListItemClick(l, v, position, id);
//        // Todo: implement event of list item selected
//    }
}
