package com.test.weather.model;


//mode for weatherDetails
public class weatherDetails {
        private Weather weather;
        private float moonrise_ts;
        private String wind_cdir;
        private float rh;
        private float pres;
        private float high_temp;
        private float sunset_ts;
        private float ozone;
        private float moon_phase;
        private float wind_gust_spd;
        private float snow_depth;
        private float clouds;
        private float ts;
        private float sunrise_ts;
        private float app_min_temp;
        private float wind_spd;
        private float pop;
        private String wind_cdir_full;
        private float moon_phase_lunation;
        private float slp;
        private float app_max_temp;
        private String valid_date;
        private float vis;
        private float snow;
        private float dewpt;
        private float uv;
        private float wind_dir;
        private String max_dhi = null;
        private float clouds_hi;
        private float precip;
        private float low_temp;
        private float max_temp;
        private float moonset_ts;
        private String datetime;
        private float temp;
        private float min_temp;
        private float clouds_mid;
        private float clouds_low;


        // Getter Methods

    public Weather getWeather() {
        return this.weather;
    }


        public float getMoonrise_ts() {
            return moonrise_ts;
        }

        public String getWind_cdir() {
            return wind_cdir;
        }

        public float getRh() {
            return rh;
        }

        public float getPres() {
            return pres;
        }

        public float getHigh_temp() {
            return high_temp;
        }

        public float getSunset_ts() {
            return sunset_ts;
        }

        public float getOzone() {
            return ozone;
        }

        public float getMoon_phase() {
            return moon_phase;
        }

        public float getWind_gust_spd() {
            return wind_gust_spd;
        }

        public float getSnow_depth() {
            return snow_depth;
        }

        public float getClouds() {
            return clouds;
        }

        public float getTs() {
            return ts;
        }

        public float getSunrise_ts() {
            return sunrise_ts;
        }

        public float getApp_min_temp() {
            return app_min_temp;
        }

        public float getWind_spd() {
            return wind_spd;
        }

        public float getPop() {
            return pop;
        }

        public String getWind_cdir_full() {
            return wind_cdir_full;
        }

        public float getMoon_phase_lunation() {
            return moon_phase_lunation;
        }

        public float getSlp() {
            return slp;
        }

        public float getApp_max_temp() {
            return app_max_temp;
        }

        public String getValid_date() {
            return valid_date;
        }

        public float getVis() {
            return vis;
        }

        public float getSnow() {
            return snow;
        }

        public float getDewpt() {
            return dewpt;
        }

        public float getUv() {
            return uv;
        }


        public float getWind_dir() {
            return wind_dir;
        }

        public String getMax_dhi() {
            return max_dhi;
        }

        public float getClouds_hi() {
            return clouds_hi;
        }

        public float getPrecip() {
            return precip;
        }

        public float getLow_temp() {
            return low_temp;
        }

        public float getMax_temp() {
            return max_temp;
        }

        public float getMoonset_ts() {
            return moonset_ts;
        }

        public String getDatetime() {
            return datetime;
        }

        public float getTemp() {
            return temp;
        }

        public float getMin_temp() {
            return min_temp;
        }

        public float getClouds_mid() {
            return clouds_mid;
        }

        public float getClouds_low() {
            return clouds_low;
        }

        // Setter Methods

    public void setWeather(Weather weatherObject) {
        this.weather = weatherObject;
    }

        public void setMoonrise_ts( float moonrise_ts ) {
            this.moonrise_ts = moonrise_ts;
        }

        public void setWind_cdir( String wind_cdir ) {
            this.wind_cdir = wind_cdir;
        }

        public void setRh( float rh ) {
            this.rh = rh;
        }

        public void setPres( float pres ) {
            this.pres = pres;
        }

        public void setHigh_temp( float high_temp ) {
            this.high_temp = high_temp;
        }

        public void setSunset_ts( float sunset_ts ) {
            this.sunset_ts = sunset_ts;
        }

        public void setOzone( float ozone ) {
            this.ozone = ozone;
        }

        public void setMoon_phase( float moon_phase ) {
            this.moon_phase = moon_phase;
        }

        public void setWind_gust_spd( float wind_gust_spd ) {
            this.wind_gust_spd = wind_gust_spd;
        }

        public void setSnow_depth( float snow_depth ) {
            this.snow_depth = snow_depth;
        }

        public void setClouds( float clouds ) {
            this.clouds = clouds;
        }

        public void setTs( float ts ) {
            this.ts = ts;
        }

        public void setSunrise_ts( float sunrise_ts ) {
            this.sunrise_ts = sunrise_ts;
        }

        public void setApp_min_temp( float app_min_temp ) {
            this.app_min_temp = app_min_temp;
        }

        public void setWind_spd( float wind_spd ) {
            this.wind_spd = wind_spd;
        }

        public void setPop( float pop ) {
            this.pop = pop;
        }

        public void setWind_cdir_full( String wind_cdir_full ) {
            this.wind_cdir_full = wind_cdir_full;
        }

        public void setMoon_phase_lunation( float moon_phase_lunation ) {
            this.moon_phase_lunation = moon_phase_lunation;
        }

        public void setSlp( float slp ) {
            this.slp = slp;
        }

        public void setApp_max_temp( float app_max_temp ) {
            this.app_max_temp = app_max_temp;
        }

        public void setValid_date( String valid_date ) {
            this.valid_date = valid_date;
        }

        public void setVis( float vis ) {
            this.vis = vis;
        }

        public void setSnow( float snow ) {
            this.snow = snow;
        }

        public void setDewpt( float dewpt ) {
            this.dewpt = dewpt;
        }

        public void setUv( float uv ) {
            this.uv = uv;
        }


        public void setWind_dir( float wind_dir ) {
            this.wind_dir = wind_dir;
        }

        public void setMax_dhi( String max_dhi ) {
            this.max_dhi = max_dhi;
        }

        public void setClouds_hi( float clouds_hi ) {
            this.clouds_hi = clouds_hi;
        }

        public void setPrecip( float precip ) {
            this.precip = precip;
        }

        public void setLow_temp( float low_temp ) {
            this.low_temp = low_temp;
        }

        public void setMax_temp( float max_temp ) {
            this.max_temp = max_temp;
        }

        public void setMoonset_ts( float moonset_ts ) {
            this.moonset_ts = moonset_ts;
        }

        public void setDatetime( String datetime ) {
            this.datetime = datetime;
        }

        public void setTemp( float temp ) {
            this.temp = temp;
        }

        public void setMin_temp( float min_temp ) {
            this.min_temp = min_temp;
        }

        public void setClouds_mid( float clouds_mid ) {
            this.clouds_mid = clouds_mid;
        }

        public void setClouds_low( float clouds_low ) {
            this.clouds_low = clouds_low;
        }
    }
