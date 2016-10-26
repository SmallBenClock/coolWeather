package com.douwong.coolweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.douwong.coolweather.model.City;
import com.douwong.coolweather.model.County;
import com.douwong.coolweather.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zds .
 * on 2016/10/26  14:21
 * 描述:
 * 包名: com.douwong.coolweather.db
 */

public class CoolWeatherDB {


    /**
     * 数据库名
     */
    public static final String sDB_NAME = "cool_weather";


    public static final int VERSION = 1;

    //单例
    private static CoolWeatherDB sCoolWeatherDB;

    private SQLiteDatabase mDb;

    private CoolWeatherDB(Context context) {
        CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context, sDB_NAME, null, VERSION);
        this.mDb = dbHelper.getWritableDatabase();
    }

    /**
     * 获取CoolWeatherDB的实例。
     */
    public synchronized static CoolWeatherDB getInstance(Context context) {
        if (sCoolWeatherDB == null) {
            sCoolWeatherDB = new CoolWeatherDB(context);
        }
        return sCoolWeatherDB;
    }

    /**
     * 从数据库读取全国所有省份的信息
     */
    public List<Province> loadProvince() {
        List<Province> list = new ArrayList<Province>();
        Cursor cursor = mDb.query("Province", null, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {

            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));

                list.add(province);
            } while (cursor.moveToFirst());

        }
        return list;

    }

    /**
     * 插入一个城市
     *
     * @param city
     */
    public void saveCity(City city) {

        if (city != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("city_name", city.getCityName());
            contentValues.put("city_code", city.getCityCode());
            contentValues.put("province_id", city.getProvinceId());
            mDb.insert("City", null, contentValues);
        }

    }


    /**
     * 从数据库读取某个省所以的城市
     *
     * @param provinceId
     * @return
     */
    public List<City> loadCities(int provinceId) {

        List<City> list = new ArrayList<>();

        //寻找City这张表中province_id相同
        Cursor cursor = mDb.query("City", null, "province_id = ?", new String[]{provinceId + ""}, null, null, null);

        while (cursor.moveToFirst()) {

            City city = new City();
            city.setId(cursor.getInt(cursor.getColumnIndex("id")));
            city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
            city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
            city.setProvinceId(provinceId);
            list.add(city);

        }
        return list;
    }

    /**
     * 保存县
     *
     * @param county
     */
    public void saveCounty(County county) {
        if (county != null) {
            ContentValues values = new ContentValues();
            values.put("county_name", county.getCountyName());
            values.put("county_code", county.getCountyCode());
            values.put("city_id", county.getCityId());
            mDb.insert("County", null, values);
        }
    }


    /**
     * 读取某城市所有的县的信息
     *
     * @param cityId
     * @return
     */
    public List<County> loadCounties(int cityId) {
        List<County> lists = new ArrayList<>();
        Cursor cursor = mDb.query("County", null, "city_id=?", new String[]{cityId + ""}, null, null, null);
        while (cursor.moveToFirst()) {
            County county = new County();
            county.setId(cursor.getInt(cursor.getColumnIndex("id")));
            county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
            county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
            county.setCityId(cityId);
            lists.add(county);
        }

        return lists;
    }


}
