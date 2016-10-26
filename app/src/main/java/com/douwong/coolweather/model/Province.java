package com.douwong.coolweather.model;

/**
 * Created by Zds .
 * on 2016/10/26  14:16
 * 描述: 省的实体类
 * 包名: com.douwong.coolweather.model
 */

public class Province {
    private int mId;
    private String mProvinceName;
    private String mProvinceCode;


    public int getId() {
        return mId;
    }

    public Province setId(int id) {
        mId = id;
        return this;
    }

    public String getProvinceCode() {
        return mProvinceCode;
    }

    public Province setProvinceCode(String provinceCode) {
        mProvinceCode = provinceCode;
        return this;
    }

    public String getProvinceName() {
        return mProvinceName;
    }

    public Province setProvinceName(String provinceName) {
        mProvinceName = provinceName;
        return this;
    }
}
