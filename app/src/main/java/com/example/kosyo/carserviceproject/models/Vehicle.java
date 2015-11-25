package com.example.kosyo.carserviceproject.models;

/**
 * Created by kosyo on 19.11.15.
 * <p/>
 * Vehicle class to save all the information about every automobile
 * the user add
 */
public class Vehicle {
    private String mRegistrationNum;
    private int mCurrentKm;
    private int mKmToNextService;
    private String mNextServiceDate;
    private String mNextInsuranceDate;
    private String mNextMotorCascoDate;
    private String mNextCarServiceDate; // yearly technical service
    private String mNextRoadTaxDate;

    public Vehicle(String mRegistrationNum, int mCurrentKm, int mKmToNextService, String mNextServiceDate,
                   String mNextInsuranceDate, String mNextMotorCascoDate,
                   String mNextCarServiceDate, String mNextRoadTaxDate) {
        this.mRegistrationNum = mRegistrationNum;
        this.mCurrentKm = mCurrentKm;
        this.mKmToNextService = mKmToNextService;
        this.mNextServiceDate = mNextServiceDate;
        this.mNextInsuranceDate = mNextInsuranceDate;
        this.mNextMotorCascoDate = mNextMotorCascoDate;
        this.mNextCarServiceDate = mNextCarServiceDate;
        this.mNextRoadTaxDate = mNextRoadTaxDate;
    }

    public String getmNextCarServiceDate() {
        return mNextCarServiceDate;
    }

    public void setmNextCarServiceDate(String mNextCarServiceDate) {
        this.mNextCarServiceDate = mNextCarServiceDate;
    }

    public String getmNextRoadTaxDate() {
        return mNextRoadTaxDate;
    }

    public void setmNextRoadTaxDate(String mNextRoadTaxDate) {
        this.mNextRoadTaxDate = mNextRoadTaxDate;
    }

    public int getmCurrentKm() {
        return mCurrentKm;
    }

    public void setmCurrentKm(int mCurrentKm) {
        this.mCurrentKm = mCurrentKm;
    }

    public int getmKmToNextService() {
        return mKmToNextService;
    }

    public void setmKmToNextService(int mKmToNextService) {
        this.mKmToNextService = mKmToNextService;
    }

    public String getmNextServiceDate() {
        return mNextServiceDate;
    }

    public void setmNextServiceDate(String mNextServiceDate) {
        this.mNextServiceDate = mNextServiceDate;
    }

    public String getmNextInsuranceDate() {
        return mNextInsuranceDate;
    }

    public void setmNextInsuranceDate(String mNextInsuranceDate) {
        this.mNextInsuranceDate = mNextInsuranceDate;
    }

    public String getmNextMotorCascoDate() {
        return mNextMotorCascoDate;
    }

    public void setmNextMotorCascoDate(String mNextMotorCascoDate) {
        this.mNextMotorCascoDate = mNextMotorCascoDate;
    }

    public String getmRegistrationNum() {

        return mRegistrationNum;
    }

    public void setmRegistrationNum(String mRegistrationNum) {
        this.mRegistrationNum = mRegistrationNum;
    }
}
