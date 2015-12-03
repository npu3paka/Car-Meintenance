package com.awesome.kosyo.carserviceproject.helpers;

/**
 * Created by kosyo on 01.12.15.
 */
public class ApplicationConfiguration {
    public final static String BASE_URL = "http://smartpril.158.bg/public/api/e";

    public final static String DATA_ARRAY_KEY = "data";

    public final static String VERSION = "VERSION";
    public final static String  ACCEPT =  "application/json";
    public final static int VERSION_VALUE = 1;

    // Vehicle attributes
    public final static String REGISTRATION_NUM_KEY = "registration_plate";
    public final static String CURRENT_KM_KEY = "run";
    public final static String KM_TO_NEXT_SERVICE_KEY = "kilometers_next_attendance";
    public final static String NEXT_SERVICE_KEY = "date_next_attendance";
    public final static String NEXT_INSURANCE_KEY = "civil_liability";
    public final static String NEXT_MOTOR_CASCO_KEY = "casco";
    public final static String NEXT_ANNUAL_TECHNICAL_INSPECTION_KEY = "annual_technical_inspection"; // yearly technical service
    public final static String NEXT_ROAD_TAX_KEY = "vignette";
}
