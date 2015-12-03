package com.awesome.kosyo.carserviceproject.interfaces;

import com.awesome.kosyo.carserviceproject.models.ApplicationConfiguration;
import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Query;

/**
 * Created by kosyo on 30.11.15.
 */
public interface ApiInterface {


    // TODO: Replace with the correct values

    //@Headers("User-Agent: Retrofit2.0Tutorial-App")
    @GET("/auth")
    void getUserVehicles(@Query("email") String email,
                         @Query("password") String password,
                         Callback<JsonObject> cb);


    @FormUrlEncoded
    @POST("/users")
    void addUser(@Part("email") String email,
                 @Part("password") String password,
                 @Part("password_confirmation") String password_confirmation,
                 Callback<JsonObject> cb);


    @FormUrlEncoded
    @POST("/users")
    void addVehicle(@Field(ApplicationConfiguration.REGISTRATION_NUM_KEY) String registration_plate,
                    @Field(ApplicationConfiguration.CURRENT_KM_KEY) int run,
                    @Field(ApplicationConfiguration.KM_TO_NEXT_SERVICE_KEY) int kilometers_next_attendance,
                    @Field(ApplicationConfiguration.NEXT_SERVICE_KEY) int date_next_attendance,
                    @Field(ApplicationConfiguration.NEXT_INSURANCE_KEY) String civil_liability,
                    @Field(ApplicationConfiguration.NEXT_MOTOR_CASCO_KEY) String casco,
                    @Field(ApplicationConfiguration.NEXT_ANNUAL_TECHNICAL_INSPECTION_KEY) String annual_technical_inspection,
                    @Field(ApplicationConfiguration.NEXT_ROAD_TAX_KEY) String vignette,
                    Callback<JsonObject> cb);

    // TODO: add for updaating a vehicle

    // TODO: ADD for logout


//    @FormUrlEncoded
//    @POST(ApplicationConfiguration.API_SET_STORE_ALARM)
//    void setStoreAlarm(@Header(ApplicationConfiguration.mToken) String token,
//                       @Field(ApplicationConfiguration.mStoreId) int storeId,
//                       @Field(ApplicationConfiguration.mAlarm) boolean alarm,
//                       Callback<JsonObject> cb);

//        @PUT("/user/{id}/update")
//        void updateUser(@Path("id") String id , @Body Item user);


//    @GET("/search/users")
//    Call<Vehicle> getUsersNamedTom(@Query("q") String name);
//
//    @POST("/user/create")
//    Call<Vehicle> createUser(@Body String name, @Body String email);

    // @PUT("/user/{id}/update")
    //Call<Item> updateUser(@Path("id") String id , @Body Item user);
}
