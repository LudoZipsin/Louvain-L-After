package com.example.ludovic.eatnow;

/**
 * Created by Ludovic Zipsin on 25/10/16.
 * Mail: ludovic.j.r.zipsin@gmail.com
 * Github: https://github.com/LudoZipsin
 */

public class Place {

    protected String name;
    protected double lat;
    protected double lon;
    protected String city;
    protected String phone;
    protected String address;
    protected String desc;
    protected int eat_state;
    protected int drink_state;
    protected int open;
    protected int close;

    public Place(String name,
                 double lat,
                 double lon,
                 String city,
                 String phone,
                 String address,
                 String desc,
                 int eat_state,
                 int drink_state,
                 int open,
                 int close)
    {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.city = city;
        this.phone = phone;
        this.address = address;
        this.desc = desc;
        this.eat_state = eat_state;
        this.drink_state = drink_state;
        this.open = open;
        this.close = close;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getEat_state() {
        return eat_state;
    }

    public void setEat_state(int eat_state) {
        this.eat_state = eat_state;
    }

    public int getDrink_state() {
        return drink_state;
    }

    public void setDrink_state(int drink_state) {
        this.drink_state = drink_state;
    }

    public int getOpen() {
        return open;
    }

    public void setOpen(int open) {
        this.open = open;
    }

    public int getClose() {
        return close;
    }

    public void setClose(int close) {
        this.close = close;
    }
}
