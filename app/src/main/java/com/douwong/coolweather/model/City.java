package com.douwong.coolweather.model;

/**
 * Created by Zds .
 * on 2016/10/26  14:18
 * 描述:城市的实体bean
 * 包名: com.douwong.coolweather.model
 */

public class City {

    private int mId;
    private String mCityName;
    private String mCityCode;
    private int mProvinceId;


    public String getCityCode() {
        return mCityCode;
    }

    public City setCityCode(String cityCode) {
        mCityCode = cityCode;
        return this;
    }

    public String getCityName() {
        return mCityName;
    }

    public City setCityName(String cityName) {
        mCityName = cityName;
        return this;
    }

    public int getId() {
        return mId;
    }

    public City setId(int id) {
        mId = id;
        return this;
    }

    public int getProvinceId() {
        return mProvinceId;
    }

    public City setProvinceId(int provinceId) {
        mProvinceId = provinceId;
        return this;
    }
}
