package com.example.metinatac.customscreen;

public class Key {

    private String id;
    private String serialnumber;
    private String devicename;
    private String username;

    public Key(String id, String serialnumber, String devicename, String username) {

        this.id = id;
        this.serialnumber = serialnumber;
        this.devicename = devicename;
        this.username = username;

    }


    public Key() {


    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
