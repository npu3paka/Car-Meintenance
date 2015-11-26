package com.example.kosyo.carserviceproject.activities;

import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.kosyo.carserviceproject.R;
import com.example.kosyo.carserviceproject.fragments.AddNewAutoFragment;
import com.example.kosyo.carserviceproject.fragments.CurrentAutosFragment;
import com.example.kosyo.carserviceproject.fragments.DetailAutoFragment;
import com.example.kosyo.carserviceproject.fragments.MainFragment;
import com.example.kosyo.carserviceproject.models.Vehicle;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by kosyo on 17.11.15.
 */
public class MainActivity extends BaseActivity implements MainFragment.OnNewFragmentListener,
        CurrentAutosFragment.OnCarSelectedListener, AddNewAutoFragment.OnCreateVehicleClicked, DetailAutoFragment.DataInteractionListener {

    // Dummy data - all detail car information
    private ArrayList<Vehicle> ownedCarsDetails;
    // Dummy data - list of all reg numbers of owned ownedCarsDetails
    private ArrayList<String> regNumOwnedVehicleList;
    // Hardcoded values of the vehicle attributes
    public static ArrayList<String> vehicleAttributesNames;
    private int vehiclePositionInList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ownedCarsDetails = new ArrayList<Vehicle>();
        // set values of car
        Vehicle vehicle1 = new Vehicle("C9999CC", 120000, 124000, "5-12-2016", "5-8-2016", "5-7-2016", "5-7-2016", "5-12-2016");
        Vehicle vehicle2 = new Vehicle("B7777BB", 840000, 96000, "14-03-2018", "5-12-2018", "1-1-2018", "1-1-2018", "1-1-2018");
        ownedCarsDetails.add(vehicle1);
        ownedCarsDetails.add(vehicle2);

        // set values of the
//        vehicleAttributesNames = new ArrayList<String>();
//        vehicleAttributesNames.add("Service valid to: ");
//        vehicleAttributesNames.add("Insurance valid to: ");
//        vehicleAttributesNames.add("Motor casco valid to: ");
//        vehicleAttributesNames.add("Next Vehicle Service: ");
//        vehicleAttributesNames.add("Next Road Tax: ");

        // set values of regNumOwnedVehicleList
        regNumOwnedVehicleList = new ArrayList<>(Arrays.asList("A 0001 AA", "A 0002 AA"));

        // Pass the dummy data to the fragments that need it
        MainFragment mainFragment = MainFragment.getInstance();
        mainFragment.setRegNumOwnedCarsList(regNumOwnedVehicleList);

        CurrentAutosFragment currentAutosFragment = CurrentAutosFragment.getInstance();
        currentAutosFragment.setCars(regNumOwnedVehicleList);

        // Load the default fragment
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_framelayout, mainFragment, MainFragment.TAG)
                .commit();
    }

    @Override
    public void onAddNewAutoFragmentClicked() {
        AddNewAutoFragment addNewAutoFragment = new AddNewAutoFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction
                .addToBackStack(null)
                .replace(R.id.main_framelayout, addNewAutoFragment, AddNewAutoFragment.TAG)
                .commit();
    }

    @Override
    public void onCurrentAutoFragmentClicked() {
        CurrentAutosFragment currentAutosFragment = CurrentAutosFragment.getInstance();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction
                .addToBackStack(null)
                .replace(R.id.main_framelayout, currentAutosFragment, CurrentAutosFragment.TAG)
                .commit();
    }

    @Override
    public void onOverdueListItemClicked(int position) {
        vehiclePositionInList = position;
        // Make an arraylist with the attributes of the Vehicle object.
        Vehicle userSelectedVehicle = ownedCarsDetails.get(vehiclePositionInList);
        // Call the fragment
        DetailAutoFragment detailAutoFragment = DetailAutoFragment.getInstance();
        // Pass object to fragment
        detailAutoFragment.setVehicle(userSelectedVehicle);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction
                .addToBackStack(null)
                .replace(R.id.main_framelayout, detailAutoFragment, DetailAutoFragment.TAG)
                .commit();
    }

    @Override
    public void onCarClicked(int position) {
        vehiclePositionInList = position;
        // Get the selected by user object
        Vehicle userSelectedVehicle = ownedCarsDetails.get(vehiclePositionInList);
        // Call the fragment
        DetailAutoFragment detailAutoFragment = DetailAutoFragment.getInstance();
        // Pass the object to fragment
        detailAutoFragment.setVehicle(userSelectedVehicle);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction
                .addToBackStack(null)
                .replace(R.id.main_framelayout, detailAutoFragment, DetailAutoFragment.TAG)
                .commit();
    }

    @Override
    public void onAddVehicleClicked(Vehicle vehicle) {
        // Add new user vehicle to database
        ownedCarsDetails.add(vehicle);
        regNumOwnedVehicleList.add(vehicle.getmRegistrationNum());

        // Close AddNewAutoFragment and open Main Fragment again
        MainFragment mainFragment = MainFragment.getInstance();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.main_framelayout, mainFragment, MainFragment.TAG).commit();
    }

    @Override
    public void onCancelClicked() {
        MainFragment mainFragment = MainFragment.getInstance();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.main_framelayout, mainFragment, MainFragment.TAG).commit();
    }

    @Override
    public void saveData(Vehicle vehicle) {
        ownedCarsDetails.set(vehiclePositionInList, vehicle);
    }

}
