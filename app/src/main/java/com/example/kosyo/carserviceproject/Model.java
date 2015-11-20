package com.example.kosyo.carserviceproject;

/**
 * Created by kosyo on 19.11.15.
 * <p/>
 * Model class to save all the information about every automobile
 * the user add
 */
public class Model {
    private String mRegistrationNum;
    private int mCurrentKm;
    private int mKmToNextService;
    private DateInner nextServiceDate;
    private DateInner nextInsuranceDate;
    private DateInner nextMotorCasco;


    public void setmRegistrationNum(String mRegistrationNum) {
        this.mRegistrationNum = mRegistrationNum;
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

    public DateInner getNextServiceDate() {
        return nextServiceDate;
    }

    public void setNextServiceDate(DateInner nextServiceDate) {
        this.nextServiceDate = nextServiceDate;
    }

    public DateInner getNextInsuranceDate() {
        return nextInsuranceDate;
    }

    public void setNextInsuranceDate(DateInner nextInsuranceDate) {
        this.nextInsuranceDate = nextInsuranceDate;
    }

    public DateInner getNextMotorCasco() {
        return nextMotorCasco;
    }

    public void setNextMotorCasco(DateInner nextMotorCasco) {
        this.nextMotorCasco = nextMotorCasco;
    }

    public String getmRegistrationNum() {

        return mRegistrationNum;
    }


    private class DateInner {
        int year;
        int month;
        int day;

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }
    } // end inner class

}
