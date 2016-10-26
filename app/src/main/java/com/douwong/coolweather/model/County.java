package com.douwong.coolweather.model;

/**
 * Created by Zds .
 * on 2016/10/26  14:19
 * 描述:
 * 包名: com.douwong.coolweather.model
 */

public class County {
    private int mId;
    private String mCountyName;
    private String mCountyCode;
    private int mCityId;


    public int getCityId() {
        return mCityId;
    }

    public County setCityId(int cityId) {
        mCityId = cityId;
        return this;
    }

    public String getCountyCode() {
        return mCountyCode;
    }

    public County setCountyCode(String countyCode) {
        mCountyCode = countyCode;
        return this;
    }

    public String getCountyName() {
        return mCountyName;
    }

    public County setCountyName(String countyName) {
        mCountyName = countyName;
        return this;
    }

    public int getId() {
        return mId;
    }

    public County setId(int id) {
        mId = id;
        return this;
    }
}
