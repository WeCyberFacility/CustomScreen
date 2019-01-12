package com.example.metinatac.customscreen;

public class Eintrag {

    private String id;
    private String text;
    private String date;

    public Eintrag(String text, String date, String id) {

        this.text = text;
        this.date = date;
        this.id = id;

    }


    public Eintrag () {}


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
