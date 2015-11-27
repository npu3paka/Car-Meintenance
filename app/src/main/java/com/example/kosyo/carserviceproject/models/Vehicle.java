package com.example.kosyo.carserviceproject.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kosyo on 19.11.15.
 * <p/>
 * Vehicle class to save all the information about every automobile
 * the user add
 */
public class Vehicle {
    private static ArrayList<String> vehicleAttributesNames; // = new ArrayList<>();
//    = Arrays.asList(
//            CarServiceApplication.getAppContext().getString(R.string.service_validity),
//            CarServiceApplication.getAppContext().getString(R.string.insurance),
//            CarServiceApplication.getAppContext().getString(R.string.casco),
//            CarServiceApplication.getAppContext().getString(R.string.vehicle_service),
//            CarServiceApplication.getAppContext().getString(R.string.road_tax));

    private String mRegistrationNum;
    private int mCurrentKm;
    private int mKmToNextService;
    private String mNextServiceDate;
    private String mNextInsuranceDate;
    private String mNextMotorCascoDate;
    private String mNextYearlyTechnicalServiceDate; // yearly technical service
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
        this.mNextYearlyTechnicalServiceDate = mNextCarServiceDate;
        this.mNextRoadTaxDate = mNextRoadTaxDate;

        vehicleAttributesNames = new ArrayList<>();
        vehicleAttributesNames.add("Service valid to: ");
        vehicleAttributesNames.add("Insurance valid to: ");
        vehicleAttributesNames.add("Motor casco valid to: ");
        vehicleAttributesNames.add("Next Technical Service: ");
        vehicleAttributesNames.add("Next Road Tax: ");
    }

    public String getmNextYearlyTechnicalServiceDate() {
        return mNextYearlyTechnicalServiceDate;
    }

    public void setmNextYearlyTechnicalServiceDate(String mNextYearlyTechnicalServiceDate) {
        this.mNextYearlyTechnicalServiceDate = mNextYearlyTechnicalServiceDate;
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

    public void getClassMap() {
        this.getClass().getFields();
    }

    public ArrayList<VehicleAttribute> getVehicleAttributesList() {
        ArrayList<String> vehicleAttributesValues = new ArrayList<String>(Arrays.asList(
                mNextServiceDate,
                mNextInsuranceDate,
                mNextMotorCascoDate,
                mNextYearlyTechnicalServiceDate,
                mNextRoadTaxDate));


        // Create arraylist of object of type VehivleAttribute to later return the arraylist
        ArrayList<VehicleAttribute> vehicleAttributesList = new ArrayList<VehicleAttribute>();

        // For every vehicle attribute create vehicle attribute object
        for (int i = 0; i < vehicleAttributesNames.size(); i++) {
            VehicleAttribute vehicleAttribute = new VehicleAttribute();
            vehicleAttribute.setmValue(vehicleAttributesValues.get(i));
            vehicleAttribute.setmName(vehicleAttributesNames.get(i));
            vehicleAttributesList.add(vehicleAttribute);
        }
        return vehicleAttributesList;
    }

    public void updateVehicleByAttributes(List<VehicleAttribute> vehicleAttributeList) {
        mNextServiceDate = vehicleAttributeList.get(0).getmValue();
        mNextInsuranceDate = vehicleAttributeList.get(1).getmValue();
        mNextMotorCascoDate = vehicleAttributeList.get(2).getmValue();
        mNextYearlyTechnicalServiceDate = vehicleAttributeList.get(3).getmValue();
        mNextRoadTaxDate = vehicleAttributeList.get(4).getmValue();
    }
}
