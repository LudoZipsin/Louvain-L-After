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
    protected int openmonday;
    protected int opentuesday;
    protected int openwednesday;
    protected int openthursday;
    protected int openfriday;
    protected int opensaturday;
    protected int opensunday;
    protected int closemonday;
    protected int closetuesday;
    protected int closewednesday;
    protected int closethursday;
    protected int closefriday;
    protected int closesaturday;
    protected int closesunday;

    public Place(String name,
                 double lat,
                 double lon,
                 String city,
                 String phone,
                 String address,
                 String desc,
                 int eat_state,
                 int drink_state,
                 int openmonday,
                 int opentuesday,
                 int openwednesday,
                 int openthursday,
                 int openfriday,
                 int opensaturday,
                 int opensunday,
                 int closemonday,
                 int closetuesday,
                 int closewednesday,
                 int closethursday,
                 int closefriday,
                 int closesaturday,
                 int closesunday
    )
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
        this.openmonday = openmonday;
        this.opentuesday = opentuesday;
        this.openwednesday = openwednesday;
        this.openthursday = openthursday;
        this.openfriday = openfriday;
        this.opensaturday = opensaturday;
        this.opensunday = opensunday;
        this.closemonday = closemonday;
        this.closetuesday = closetuesday;
        this.closewednesday = closewednesday;
        this.closethursday = closethursday;
        this.closefriday = closefriday;
        this.closesaturday = closesaturday;
        this.closesunday = closesunday;
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

    public int getOpenMonday() {
        return openmonday;
    }

    public void setOpenMonday(int openmonday) {
        this.openmonday = openmonday;
    }

    public int getOpenTuesday() {
        return opentuesday;
    }

    public void setOpenTuesday(int opentuesday) {
        this.opentuesday = opentuesday;
    }

    public int getOpenWednesday() {
        return openwednesday;
    }

    public void setOpenWednesday(int openwednesday) {
        this.openwednesday = openwednesday;
    }

    public int getOpenThursday() {
        return openthursday;
    }

    public void setOpenThursday(int openthursday) {
        this.openthursday = openthursday;
    }

    public int getOpenFriday() {
        return openfriday;
    }

    public void setOpenFriday(int openfriday) {
        this.openfriday = openfriday;
    }

    public int getOpenSaturday() {
        return opensaturday;
    }

    public void setOpenSaturday(int opensaturday) {
        this.opensaturday = opensaturday;
    }

    public int getOpenSunday() {
        return opensunday;
    }

    public void setOpenSunday(int opensunday) {
        this.opensunday = opensunday;
    }

    public int getCloseMonday() {
        return closemonday;
    }

    public void setCloseMonday(int closemonday) {
        this.closemonday = closemonday;
    }

    public int getCloseTuesday() {
        return closetuesday;
    }

    public void setCloseTuesday(int closetuesday) {
        this.closetuesday = closetuesday;
    }

    public int getCloseWednesday() {
        return closewednesday;
    }

    public void setCloseWednesday(int closewednesday) {
        this.closewednesday = closewednesday;
    }

    public int getCloseThursday() {
        return closethursday;
    }

    public void setCloseThursday(int closethursday) {
        this.closethursday = closethursday;
    }

    public int getCloseFriday() {
        return closefriday;
    }

    public void setCloseFriday(int closefriday) {
        this.closefriday = closefriday;
    }

    public int getCloseSaturday() {
        return closesaturday;
    }

    public void setCloseSaturday(int closesaturday) {
        this.closesaturday = closesaturday;
    }

    public int getCloseSunday() {
        return closesunday;
    }

    public void setCloseSunday(int closesunday) {
        this.closesunday = closesunday;
    }
}
