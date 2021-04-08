package com.tanvircodder.bdhotel.util;

public class Utility {
    private String location ;
    private String hotelName;
    private String hotelStarCount;

    public Utility(String location, String hotelName, String hotelStarCount) {
        this.location = location;
        this.hotelName = hotelName;
        this.hotelStarCount = hotelStarCount;
    }

    public String getLocation() {
        return location;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String  getHotelStarCount() {
        return hotelStarCount;
    }
}
