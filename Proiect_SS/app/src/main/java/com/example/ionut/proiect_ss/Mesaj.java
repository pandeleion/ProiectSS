package com.example.ionut.proiect_ss;

import java.util.Date;

/**
 * Created by Ionut on 22.05.2017.
 */

public class Mesaj {

    private String mesajText;
    private String mesajUser;
    private long mesajTimp;

    public Mesaj() {
    }

    public Mesaj(String mesajText, String mesajUser) {
        this.mesajText = mesajText;
        this.mesajUser = mesajUser;
        this.mesajTimp = new Date().getTime();
    }

    public String getMesajText() {
        return mesajText;
    }

    public void setMesajText(String mesajText) {
        this.mesajText = mesajText;
    }

    public String getMesajUser() {
        return mesajUser;
    }

    public void setMesajUser(String mesajUser) {
        this.mesajUser = mesajUser;
    }

    public long getMesajTimp() {
        return mesajTimp;
    }

    public void setMesajTimp(long mesajTimp) {
        this.mesajTimp = mesajTimp;
    }

}
