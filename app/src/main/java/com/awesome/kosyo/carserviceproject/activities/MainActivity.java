package com.awesome.kosyo.carserviceproject.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.awesome.kosyo.carserviceproject.R;
import com.awesome.kosyo.carserviceproject.adapters.DetailArrayAdapter;
import com.awesome.kosyo.carserviceproject.fragments.AddNewAutoFragment;
import com.awesome.kosyo.carserviceproject.fragments.CurrentAutosFragment;
import com.awesome.kosyo.carserviceproject.fragments.DetailAutoFragment;
import com.awesome.kosyo.carserviceproject.fragments.MainFragment;
import com.awesome.kosyo.carserviceproject.helpers.ApplicationConfiguration;
import com.awesome.kosyo.carserviceproject.helpers.SessionManager;
import com.awesome.kosyo.carserviceproject.interfaces.ApiInterface;
import com.awesome.kosyo.carserviceproject.models.Vehicle;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by kosyo on 17.11.15.
 */
public class MainActivity extends BaseActivity implements MainFragment.OnNewFragmentListener,
        CurrentAutosFragment.OnCarSelectedListener, AddNewAutoFragment.OnCreateVehicleClicked,
        DetailAutoFragment.DataInteractionListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    private ArrayList<Vehicle> ownedUserVehicles;
    private int vehiclePositionInList;
    private Vehicle vehicle;
    // Dummy data - list of all reg numbers of owned ownedUserVehicles
    private ArrayList<String> regNumOverdueList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Fleet Care Pro");

         // Get vehicles and store them in ownedUserVehicles
        getUserVehicleFromServer();

//        // set values of car
//        Vehicle vehicle1 = new Vehicle(100, "C9999CC", 120000, 124000, "2016-12-8", "2016-8-1", "2016-7-2", "2016-7-3", "2016-12-4");
//        Vehicle vehicle2 = new Vehicle(101, "B7777BB", 840000, 96000, "2018-03-1", "2018-12-2", "2018-1-3", "2018-1-4", "2018-1-5");
//        ownedUserVehicles.add(vehicle1);
//        ownedUserVehicles.add(vehicle2);

//        // set values of regNumOverdueList
//        regNumOverdueList = new ArrayList<>(Arrays.asList("A 0001 AA", "A 0002 AA"));


        String dueDate;
        for (int i = 0; i < ownedUserVehicles.size(); i++) {
            // Get every Vehicle object
            Vehicle vehicle = ownedUserVehicles.get(i);

            dueDate = vehicle.getmNextOilServiceDate();
            boolean isOilServiceDateOverdue = isVehicleAttributeOverdue(dueDate);
            dueDate = vehicle.getmNextInsuranceDate();
            boolean isInsuranceOverdue = isVehicleAttributeOverdue(dueDate);
            dueDate = vehicle.getmNextMotorCascoDate();
            boolean isCascoOverdue = isVehicleAttributeOverdue(dueDate);
            dueDate = vehicle.getmNextAnnualTechnicalServiceDate();
            boolean isAnnualServiceOverdue = isVehicleAttributeOverdue(dueDate);
            dueDate = vehicle.getmNextRoadTaxDate();
            boolean isRoadTaxOverdue = isVehicleAttributeOverdue(dueDate);

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

    private void getUserVehicleFromServer() {
        ownedUserVehicles = new ArrayList<Vehicle>();
        // Get GCM token from shared preferences
        SharedPreferences sharedprefs = getApplicationContext()
                .getSharedPreferences(getString(R.string.token_from_GCM_preference_key), Context.MODE_PRIVATE);
        String token;
        // There is no need to make API request if there isn't stored token
     //   if (sharedprefs.contains(getString(R.string.token_from_GCM_preference_key)))        {
            token = sharedprefs.getString(getString(R.string.token_from_GCM_preference_key), "");
            Log.v(TAG, "GCM token:" +token);
            // Get the User Vehicles from API
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(ApplicationConfiguration.BASE_URL)
                    .build();
            ApiInterface restInterface = restAdapter.create(ApiInterface.class);
            restInterface.getVehicles(token, ApplicationConfiguration.VERSION_VALUE,
                    ApplicationConfiguration.ACCEPT_VALUE,
                    SessionManager.getInstance().getLOGIN_TOKEN(), new Callback<JsonObject>() {
                        @Override
                        public void success(JsonObject jsonObject, Response response) {
                            JsonArray jsonArray = jsonObject.getAsJsonArray(
                                    ApplicationConfiguration.DATA_ARRAY_KEY);
                            for (int i = 0; i < jsonArray.size(); i++) {
                                JsonObject jObj = jsonArray.get(i).getAsJsonObject();

                                // Get the values from the JsonObject
                                int id = jObj.get(ApplicationConfiguration.ID_KEY).getAsInt();
                                String registrationNum = jObj.get(ApplicationConfiguration.REGISTRATION_NUM_KEY).getAsString();
                                int currentKm = jObj.get(ApplicationConfiguration.CURRENT_KM_KEY).getAsInt();
                                int kmToNextService = jObj.get(ApplicationConfiguration.KM_TO_NEXT_SERVICE_KEY).getAsInt();
                                String nextServiceDate = jObj.get(ApplicationConfiguration.NEXT_SERVICE_KEY).getAsString();
                                String nextInsuranceDate = jObj.get(ApplicationConfiguration.NEXT_INSURANCE_KEY).getAsString();
                                String nextMotorCascoDate = jObj.get(ApplicationConfiguration.NEXT_MOTOR_CASCO_KEY).getAsString();
                                String nextAnnualTechnicalInspectionDate = jObj.get(ApplicationConfiguration.NEXT_ANNUAL_TECHNICAL_INSPECTION_KEY).getAsString();
                                String nextRoadTaxDate = jObj.get(ApplicationConfiguration.NEXT_ROAD_TAX_KEY).getAsString();

                                // Create a new vehicle obj and add it to the arrayList of type Vehicle
                                Vehicle vehicle = new Vehicle(id, registrationNum, currentKm, kmToNextService, nextServiceDate,
                                        nextInsuranceDate, nextMotorCascoDate, nextAnnualTechnicalInspectionDate, nextRoadTaxDate);
                                ownedUserVehicles.add(vehicle);
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });
//        } else {
//            Toast toast = Toast.makeText(getApplicationContext(), "Възникна грешка при опит за връзка със сървъра!", Toast.LENGTH_LONG);
//            toast.show();
//        }
    }

    private boolean isVehicleAttributeOverdue(String dueDate) {
        int dueDay;
        int dueMonth;
        int dueYear;

        // Get the current day
        Boolean lenient = true;
        Calendar currentDateCalendar = Calendar.getInstance();
        currentDateCalendar.setLenient(lenient);

        // Parse date string to year, month and day
        // Example 16-11-2015 to 16, 11, 2015
        String tokens[] = dueDate.split("-");
        dueYear = Integer.parseInt(tokens[1]);
        // if Due year is greater then the current there is no need to check month or day
        if (dueYear <= currentDateCalendar.YEAR) {
            dueMonth = Integer.parseInt(tokens[1]);
            if (dueMonth <= currentDateCalendar.MONTH) {
                dueDay = Integer.parseInt(tokens[2]);
                if (dueDay <= currentDateCalendar.DAY_OF_MONTH) {
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
    public void onAddVehicleClicked(final Vehicle vehicle) {
        // Get GCM token from shared preferences
        SharedPreferences sharedprefs = getApplicationContext()
                .getSharedPreferences(getString(R.string.token_from_GCM_preference_key), Context.MODE_PRIVATE);

        // There is no need to make API request if there isn't stored token
        String token;
        if (sharedprefs.contains(getString(R.string.token_from_GCM_preference_key))) {
            token = sharedprefs.getString(getString(R.string.token_from_GCM), "");

            RestAdapter adapter = new RestAdapter.Builder()
                    .setEndpoint(ApplicationConfiguration.BASE_URL).build();
            ApiInterface restInterface = adapter.create(ApiInterface.class);

            String regNum = vehicle.getmRegistrationNum();
            int currKm = vehicle.getmCurrentKm();
            int kmNextService = vehicle.getmKmToNextService();
            String oilService = vehicle.getmNextOilServiceDate();
            String insurance = vehicle.getmNextInsuranceDate();
            String casco = vehicle.getmNextMotorCascoDate();
            String anualService = vehicle.getmNextAnnualTechnicalServiceDate();
            String roadTax = vehicle.getmNextRoadTaxDate();

            restInterface.addVehicle(token, ApplicationConfiguration.VERSION_VALUE,
                    ApplicationConfiguration.ACCEPT_VALUE, ApplicationConfiguration.TOKEN,
                    regNum, currKm, kmNextService, oilService, insurance,
                    casco, anualService, roadTax, new Callback<JsonArray>() {
                        @Override
                        public void success(JsonArray jsonElements, Response response) {

                            // Add new user vehicle to local temporary storage for the life of this Activity
                            ownedUserVehicles.add(vehicle);
                            regNumOverdueList.add(vehicle.getmRegistrationNum());

                            // Open Main Fragment again
                            FragmentManager fragmentManager = getFragmentManager();
                            fragmentManager.popBackStack();
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Log.v(TAG, "In failure: " + error.toString());
                        }
                    });
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Възникна грешка! Моля, опитайте отново.", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @Override
    public void onCancelClicked() {
        // Open Main Fragment again
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack();
    }

    @Override
    public void saveUpdatedVehicle(final Vehicle vehicle, final DetailArrayAdapter.DataUpdatingListener listener,
                                   final String newUserFriendlyFormatDate) {
        // Get GCM token from shared preferences
        SharedPreferences sharedprefs = getApplicationContext()
                .getSharedPreferences(getString(R.string.token_from_GCM_preference_key), Context.MODE_PRIVATE);

        // There is no need to make API request if there isn't stored token
        String token;
        if (sharedprefs.contains(getString(R.string.token_from_GCM_preference_key))) {
            token = sharedprefs.getString(getString(R.string.token_from_GCM), "");

            // Get the new values from the Vehicle object
            String regNum = vehicle.getmRegistrationNum();
            int currKm = vehicle.getmCurrentKm();
            int kmNextService = vehicle.getmKmToNextService();
            String oilService = vehicle.getmNextOilServiceDate();
            String insurance = vehicle.getmNextInsuranceDate();
            String casco = vehicle.getmNextMotorCascoDate();
            String anualService = vehicle.getmNextAnnualTechnicalServiceDate();
            String roadTax = vehicle.getmNextRoadTaxDate();

            // Update on server
            RestAdapter adapter = new RestAdapter.Builder()
                    .setEndpoint(ApplicationConfiguration.BASE_URL)
                    .build();
            ApiInterface restInterface = adapter.create(ApiInterface.class);
            restInterface.updateVehicle(token, ApplicationConfiguration.VERSION_VALUE, ApplicationConfiguration.ACCEPT_VALUE,
                    SessionManager.getInstance().getLOGIN_TOKEN(), regNum, currKm, kmNextService, oilService,
                    insurance, casco, anualService, roadTax, new Callback<JsonObject>() {
                        @Override
                        public void success(JsonObject jsonObject, Response response) {
                            // Update locally
                            ownedUserVehicles.set(vehiclePositionInList, vehicle);

                            // Update value on the Phone Screen
                            listener.dataUpdated(newUserFriendlyFormatDate);
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Възникна грешка! Моля, опитайте отново.", Toast.LENGTH_SHORT);
                            toast.show();
                            Log.v(TAG, "In failure: " + error.toString());
                        }
                    });
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Възникна грешка! Моля, опитайте отново.", Toast.LENGTH_LONG);
            toast.show();
        }
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
