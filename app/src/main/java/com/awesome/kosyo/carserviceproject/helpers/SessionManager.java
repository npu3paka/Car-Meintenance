package com.awesome.kosyo.carserviceproject.helpers;

/**
 * Created by kosyo on 03.12.15.
 */
public class SessionManager {
    private  String LOGIN_TOKEN;


    // Singleton implementation
    private static SessionManager instance;

    public static  SessionManager getInstance(){
        if (instance == null ){
            instance = new SessionManager();
        }
        return instance;
    }


    public String getLOGIN_TOKEN() {
        return LOGIN_TOKEN;
    }

    public void setLOGIN_TOKEN(String LOGIN_TOKEN) {
        this.LOGIN_TOKEN = LOGIN_TOKEN;
    }


}
