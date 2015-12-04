package com.awesome.kosyo.carserviceproject.interfaces;

import com.awesome.kosyo.carserviceproject.helpers.ApplicationConfiguration;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by kosyo on 30.11.15.
 */
public interface ApiInterface {

    @FormUrlEncoded
    @POST("/auth")
    void getAuthorization(@Header(ApplicationConfiguration.VERSION) int versionValue,
                          @Header(ApplicationConfiguration.ACCEPT) String acceptValue,
                          @Field("email") String email,
                          @Field("password") String password,
                          Callback<JsonObject> cb);


    @FormUrlEncoded
    @POST("/users")
    void addUser(@Header(ApplicationConfiguration.VERSION) int versionValue,
                 @Header(ApplicationConfiguration.ACCEPT) String acceptValue,
                 @Field("email") String email,
                 @Field("password") String password,
                 @Field("password_confirmation") String password2, //TODO remove this param  when changed on server
                 Callback<JsonArray> cb);


    @GET("/vehicles{token}")
    void getVehicles(@Path(ApplicationConfiguration.TOKEN) String tokenGCM,
                     @Header(ApplicationConfiguration.VERSION) int versionValue,
                     @Header(ApplicationConfiguration.ACCEPT) String acceptValue,
                     @Header(ApplicationConfiguration.AUTHORIZATION) String token,
                     Callback<JsonObject> cb);

    @FormUrlEncoded
    @POST("/vehicles{token}")
    void addVehicle(@Path(ApplicationConfiguration.TOKEN) String tokenGCM,
                    @Header(ApplicationConfiguration.VERSION) int versionValue,
                    @Header(ApplicationConfiguration.ACCEPT) String acceptValue,
                    @Header(ApplicationConfiguration.AUTHORIZATION) String token,
                    @Field(ApplicationConfiguration.REGISTRATION_NUM_KEY) String registration_plate,
                    @Field(ApplicationConfiguration.CURRENT_KM_KEY) int run,
                    @Field(ApplicationConfiguration.KM_TO_NEXT_SERVICE_KEY) int kilometers_next_attendance,
                    @Field(ApplicationConfiguration.NEXT_SERVICE_KEY) String date_next_attendance,
                    @Field(ApplicationConfiguration.NEXT_INSURANCE_KEY) String civil_liability,
                    @Field(ApplicationConfiguration.NEXT_MOTOR_CASCO_KEY) String casco,
                    @Field(ApplicationConfiguration.NEXT_ANNUAL_TECHNICAL_INSPECTION_KEY) String annual_technical_inspection,
                    @Field(ApplicationConfiguration.NEXT_ROAD_TAX_KEY) String vignette,
                    Callback<JsonArray> cb);


    @FormUrlEncoded
    @PUT("/vehicles{token}")
    void updateVehicle(@Path(ApplicationConfiguration.TOKEN) String tokenGCM,
                       @Header(ApplicationConfiguration.VERSION) int versionValue,
                       @Header(ApplicationConfiguration.ACCEPT) String acceptValue,
                       @Header(ApplicationConfiguration.AUTHORIZATION) String token,
                       @Field(ApplicationConfiguration.REGISTRATION_NUM_KEY) String registration_plate,
                       @Field(ApplicationConfiguration.CURRENT_KM_KEY) int run,
                       @Field(ApplicationConfiguration.KM_TO_NEXT_SERVICE_KEY) int kilometers_next_attendance,
                       @Field(ApplicationConfiguration.NEXT_SERVICE_KEY) String date_next_attendance,
                       @Field(ApplicationConfiguration.NEXT_INSURANCE_KEY) String civil_liability,
                       @Field(ApplicationConfiguration.NEXT_MOTOR_CASCO_KEY) String casco,
                       @Field(ApplicationConfiguration.NEXT_ANNUAL_TECHNICAL_INSPECTION_KEY) String annual_technical_inspection,
                       @Field(ApplicationConfiguration.NEXT_ROAD_TAX_KEY) String vignette,
                       Callback<JsonObject> cb);

    // TODO: ADD for logout


    // TODO get token from GCM


//    @FormUrlEncoded
//    @POST(ApplicationConfiguration.API_SET_STORE_ALARM)
//    void setStoreAlarm(@Header(ApplicationConfiguration.mToken) String token,
//                       @Field(ApplicationConfiguration.mStoreId) int storeId,
//                       @Field(ApplicationConfiguration.mAlarm) boolean alarm,
//                       Callback<JsonObject> cb);
}
