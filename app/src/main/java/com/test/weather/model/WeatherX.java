package com.test.weather.model;

//mode contain all weather details
public class WeatherX {
    weatherDetails[]  data ;
    private String city_name;
    private float lon;
    private String timezone;
    private float lat;
    private String country_code;
    private String state_code;


    // Getter Methods

    public weatherDetails[] getData() {
        return data;
    }

    public String getCity_name() {
        return city_name;
    }

    public float getLon() {
        return lon;
    }

    public String getTimezone() {
        return timezone;
    }

    public float getLat() {
        return lat;
    }

    public String getCountry_code() {
        return country_code;
    }

    public String getState_code() {
        return state_code;
    }

    // Setter Methods

    public void setData( weatherDetails[]  Data) {
        this.data = Data;
    }

    public void setCity_name( String city_name ) {
        this.city_name = city_name;
    }

    public void setLon( float lon ) {
        this.lon = lon;
    }

    public void setTimezone( String timezone ) {
        this.timezone = timezone;
    }

    public void setLat( float lat ) {
        this.lat = lat;
    }

    public void setCountry_code( String country_code ) {
        this.country_code = country_code;
    }

    public void setState_code( String state_code ) {
        this.state_code = state_code;
    }
}