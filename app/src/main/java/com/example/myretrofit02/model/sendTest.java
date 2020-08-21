package com.example.myretrofit02.model;

public class sendTest {
    String currentDT;
    String authCode;
    //String moblie;

    public sendTest(String currentDT, String authCode) {
        this.currentDT = currentDT;
        this.authCode = authCode;

    }

    public String getCurrentDT() {
        return currentDT;
    }

    public void setCurrentDT(String currentDT) {
        this.currentDT = currentDT;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

}
