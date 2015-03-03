package com.lambda.weibo.fields;

/**
 * Created by LU Tianming on 15-3-2.
 */
public class Geo {
    public static final String LONGITUDE = "longitude";
    public static final String LATITUDE = "latitude";
    public static final String CITY = "city";
    public static final String PROVINCE = "province";
    public static final String CITY_NAME = "city_name";
    public static final String PROVINCE_NAME = "province_name";
    public static final String ADDRESS = "address";
    public static final String PINYIN = "pinyin";
    public static final String MORE = "more";

    private String longitude;
    private String latitude;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    private String city;
    private String province;
    private String city_name;
    private String province_name;
    private String address;
    private String pinyin;
    private String more;
}
