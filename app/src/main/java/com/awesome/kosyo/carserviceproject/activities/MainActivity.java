package com.awesome.kosyo.carserviceproject.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.awesome.kosyo.carserviceproject.R;
import com.awesome.kosyo.carserviceproject.fragments.AddNewAutoFragment;
import com.awesome.kosyo.carserviceproject.fragments.CurrentAutosFragment;
import com.awesome.kosyo.carserviceproject.fragments.DetailAutoFragment;
import com.awesome.kosyo.carserviceproject.fragments.MainFragment;
import com.awesome.kosyo.carserviceproject.models.Vehicle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

/**
 * Created by kosyo on 17.11.15.
 */
public class MainActivity extends BaseActivity implements MainFragment.OnNewFragmentListener,
        CurrentAutosFragment.OnCarSelectedListener, AddNewAutoFragment.OnCreateVehicleClicked,
        DetailAutoFragment.DataInteractionListener {

    private ArrayList<Vehicle> ownedUserVehicles;
    // Dummy data - list of all reg numbers of owned ownedUserVehicles
    private ArrayList<String> regNumOverdueList;
    // Hardcoded values of the vehicle attributes
    public static ArrayList<String> vehicleAttributesNames;
    private int vehiclePositionInList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Fleet care pro");

        ownedUserVehicles = new ArrayList<Vehicle>();
        // set values of car
        Vehicle vehicle1 = new Vehicle("C9999CC", 120000, 124000, "2016-12-8", "2016-8-1", "2016-7-2", "2016-7-3", "2016-12-4");
        Vehicle vehicle2 = new Vehicle("B7777BB", 840000, 96000, "2018-03-1", "2018-12-2", "2018-1-3", "2018-1-4", "2018-1-5");
        ownedUserVehicles.add(vehicle1);
        ownedUserVehicles.add(vehicle2);

        // set values of the
//        vehicleAttributesNames = new ArrayList<String>();
//        vehicleAttributesNames.add("Service valid to: ");
//        vehicleAttributesNames.add("Insurance valid to: ");
//        vehicleAttributesNames.add("Motor casco valid to: ");
//        vehicleAttributesNames.add("Next Vehicle Service: ");
//        vehicleAttributesNames.add("Next Road Tax: ");

        // set values of regNumOverdueList
        regNumOverdueList = new ArrayList<>(Arrays.asList("A 0001 AA", "A 0002 AA"));

        String dueDate;
        for (int i=0; i<ownedUserVehicles.size(); i++){
            // Get every Vehicle object
            Vehicle vehicle = ownedUserVehicles.get(i);

            dueDate = vehicle.getmNextServiceDate();
            boolean isServiceDateOverdue = isVehicleAttributeOverdue(dueDate);

            // TODO: Make this check for the other attributes and remeber the ones that are overdue
        }


        // Pass the dummy data to the fragments that need it
        MainFragment mainFragment = MainFragment.getInstance();
        mainFragment.setRegNumOverdueList(regNumOverdueList);

        //CurrentAutosFragment currentAutosFragment = CurrentAutosFragment.getInstance();
        //   currentAutosFragment.setCars(regNumOverdueList);

        // Load the default fragment
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_framelayout, mainFragment, MainFragment.TAG)
                .commit();
    }


    private boolean isVehicleAttributeOverdue(String dueDate){
        int dueDay;
        int dueMonth;
        int dueYear;

        // Get the current day
        Boolean lenient = true;
        Calendar currentDateCalendar =  Calendar.getInstance();
        currentDateCalendar.setLenient(lenient);

        // Parse date string to day, month and year
        // Example 16-11-2015 to 16, 11, 2015
        String tokens[] = dueDate.split("-");
        dueYear = Integer.parseInt(tokens[2]);
        // if Due year is greater then the current there is no need to check month or day
        if(dueYear <= currentDateCalendar.YEAR){
            dueMonth = Integer.parseInt(tokens[1]);
            if (dueMonth <= currentDateCalendar.MONTH){
                dueDay = Integer.parseInt(tokens[0]);
                if (dueDay <= currentDateCalendar.DAY_OF_MONTH){
                    // The Vehicle attribute is overdue
                    return true;
                }
            }
        }
        return false;
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
        currentAutosFragment.setCars(regNumOverdueList);
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
        Vehicle userSelectedVehicle = ownedUserVehicles.get(vehiclePositionInList);
        // Call the fragment
        DetailAutoFragment detailAutoFragment = DetailAutoFragment.getInstance();
        // Pass object to fragment
        detailAutoFragment.setVehicleObj(userSelectedVehicle);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction
                .addToBackStack(null)
                .replace(R.id.main_framelayout, detailAutoFragment, DetailAutoFragment.TAG)
                .commit();
    }

    @Override
    public void onCarClickedInCurrentAutosFragmentList(int position) {
        vehiclePositionInList = position;
        // Get the selected by user object
        Vehicle userSelectedVehicle = ownedUserVehicles.get(vehiclePositionInList);
        // Call the fragment
        DetailAutoFragment detailAutoFragment = DetailAutoFragment.getInstance();
        // Pass the object to fragment
        detailAutoFragment.setVehicleObj(userSelectedVehicle);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction
                .addToBackStack(null)
                .replace(R.id.main_framelayout, detailAutoFragment, DetailAutoFragment.TAG)
                .commit();
    }

    @Override
    public void onAddVehicleClicked(Vehicle vehicle) {
        // TODO add to server
//        RestAdapter adapter = new RestAdapter.Builder()
//                .setEndpoint(ApplicationConfiguration.BASE_URL).build();
//        ApiInterface restInterface = adapter.create(ApiInterface.class);
//
//        restInterface.addVehicle();

        // Add new user vehicle to database
        ownedUserVehicles.add(vehicle);
        regNumOverdueList.add(vehicle.getmRegistrationNum());

        // Open Main Fragment again
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack();
    }

    @Override
    public void onCancelClicked() {
        // Open Main Fragment again
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack();
    }

    @Override
    public void saveData(Vehicle vehicle) {
        ownedUserVehicles.set(vehiclePositionInList, vehicle);
    }


    // Converts DAY-MONTH-YEAR to YEAR-MONTH-DAY
    public static final String toApiFormatDate(String date) {
        String tokens[] = date.split("-");
        String day = tokens[0];
        String month = tokens[1];
        String year = tokens[2];

        String apiFormatString = year + "-" + month + "-" + day;
        return apiFormatString;
    }

    // Converts YEAR-MONTH-DAY to DAY-MONTH-YEAR
    public static final String toUserFriendlyFormatDate(String date) {
        String tokens[] = date.split("-");
        String year = tokens[0];
        String month = tokens[1];
        String day = tokens[2];

        String userFriendlyString = day + "-" + month + "-" + year;
        return userFriendlyString;
    }
}
