package com.awesome.kosyo.carserviceproject.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import com.awesome.kosyo.carserviceproject.R;
import com.awesome.kosyo.carserviceproject.fragments.LoginFragment;
import com.awesome.kosyo.carserviceproject.fragments.RegisterFragment;
import com.awesome.kosyo.carserviceproject.interfaces.ApiKeys;
import com.awesome.kosyo.carserviceproject.models.Vehicle;

import java.util.ArrayList;

public class LoginActivity extends BaseActivity implements LoginFragment.LoginRegisterListener, RegisterFragment.OnBtnRegisterListener, ApiKeys {
    private ArrayList<Vehicle> ownedUserVehicles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.login_framelayout, LoginFragment.getInstance(), LoginFragment.TAG)
                .commit();
    }

    @Override
    public void onLoginPrompted(String username, String password) {
        ownedUserVehicles = new ArrayList<>();

        /*String url = "http://blablabla";
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(url).build();
        ApiInterface restInterface = adapter.create(ApiInterface.class);

        restInterface.setStoreAlarm(username, token, true, new Callback<JsonObject>() {
            @Override
            public void success(JsonObject jsonObject, Response response) {
                JsonArray jsonArray = new JsonArray();
                jsonArray = jsonObject.getAsJsonArray(DATA_ARRAY_KEY);

                for (int i = 0; i < jsonArray.size(); i++) {
                    JsonObject jObj = jsonArray.get(i).getAsJsonObject();

                    // Get the values from the JsonObject
                    String registrationNum = jObj.get(REGISTRATION_NUM_KEY).getAsString();
                    int currentKm = jObj.get(CURRENT_KM_KEY).getAsInt();
                    int kmToNextService = jObj.get(KM_TO_NEXT_SERVICE_KEY).getAsInt();
                    String nextServiceDate = jObj.get(NEXT_SERVICE_KEY).getAsString();
                    String nextInsuranceDate = jObj.get(NEXT_INSURANCE_KEY).getAsString();
                    String nextMotorCascoDate = jObj.get(NEXT_MOTOR_CASCO_KEY).getAsString();
                    String nextAnnualTechnicalInspectionDate = jObj.get(NEXT_ANNUAL_TECHNICAL_INSPECTION_KEY).getAsString();
                    String nextRoadTaxDate = jObj.get(NEXT_ROAD_TAX_KEY).getAsString();

                    // Create a new vehicle obj and add it to the arrayList of type Vehicle
                    Vehicle vehicle = new Vehicle(registrationNum, currentKm, kmToNextService, nextServiceDate,
                            nextInsuranceDate, nextMotorCascoDate, nextAnnualTechnicalInspectionDate, nextRoadTaxDate);
                    ownedUserVehicles.add(vehicle);
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });*/

//        RestClient.GitApiInterface service = RestClient.getClient();
//        service.createUser(email, password, new Callback<JSONObject>() {
//        });
//        Call<GitResult> call = service.getUsersNamedTom("tom");
//        call.enqueue(new Callback<GitResult>() {
//            @Override
//            public void onResponse(Response<GitResult> response) {
//                dialog.dismiss();
//                Log.d("MainActivity", "Status Code = " + response.code());
//                if (response.isSuccess()) {
//                    // request successful (status code 200, 201)
//                    GitResult result = response.body();
//                    Log.d("MainActivity", "response = " + new Gson().toJson(result));
//                    Users = result.getItems();
//                    Log.d("MainActivity", "Items = " + Users.size());
//                    adapter = new UserAdapter(MainActivity.this, Users);
//                    listView.setAdapter(adapter);
//                } else {
//                    // response received but request not successful (like 400,401,403 etc)
//                    //Handle errors
//
//                }
//            }

//            @Override
//            public void onFailure(Throwable t) {
//                dialog.dismiss();
//            }
//        });


        // TODO: pass the data from server to Main Activity

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);


/*
        String baseUrl = "http://insert your url here";
        OkHttpClient okClient = new OkHttpClient();
        okClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                return response;
            }
        });

        *//*******//*
        Retrofit client = new Retrofit().Builder()
                .baseUrl(baseUrl)
                .client(okClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();




        ApiInterface service = client.create(ApiInterface.class);
        Call<Vehicle> call = service.getUsersNamedTom("tom");
        call.enqueue(new Callback<Vehicle>() {
            @Override
            public void onResponse(Response<GitResult> response) {
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    GitResult result = response.body();
                } else {
                    //request not successful (like 400,401,403 etc)
                    //Handle errors
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });*/

        // In order not to show this activity if user clicks the back button
        finish();
    }

    @Override
    public void onCreateRegisterationClicked() {
        RegisterFragment registerFragment = new RegisterFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction
                .addToBackStack(null)
                .replace(R.id.login_framelayout, registerFragment, RegisterFragment.TAG)
                .commit();
    }

    @Override
    public void onBtnRegisterClicked() {
        // TODO: add the new user registration to database
    }
}