package com.jiuwu.guojingbu.guoguoweather.utils;

import android.text.TextUtils;

import com.jiuwu.guojingbu.guoguoweather.db.City;
import com.jiuwu.guojingbu.guoguoweather.db.County;
import com.jiuwu.guojingbu.guoguoweather.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by guojingbu on 2017/3/13.
 */

public class Utility {
    private Utility() {

    }

    /**
     * 解析和处理服务器返回的省级数据
     *
     * @param response
     * @return
     */
    public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provinceObj = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceCode(provinceObj.getInt("id"));
                    province.setProvinceName(provinceObj.getString("name"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;

    }

    /**
     * 解析处理服务器返回的市级数据
     *
     * @param response
     * @param provinceId
     * @return
     */
    public static boolean handleCityResponse(String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCitys = new JSONArray(response);
                for (int i = 0; i < allCitys.length(); i++) {
                    JSONObject cityObj = allCitys.getJSONObject(i);
                    City city = new City();
                    city.setCityCode(cityObj.getInt("id"));
                    city.setCityName(cityObj.getString("name"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        return false;
    }

    /**
     * 解析处理服务器返回的县区数据
     *
     * @param response
     * @param cityId
     * @return
     */
    public static boolean handleCountyResponse(String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCountys = new JSONArray(response);
                for (int i = 0; i < allCountys.length(); i++) {
                    JSONObject countyObj = allCountys.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObj.getString("name"));
                    county.setWeahterId(countyObj.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
