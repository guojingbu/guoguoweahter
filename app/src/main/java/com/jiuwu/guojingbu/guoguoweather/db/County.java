package com.jiuwu.guojingbu.guoguoweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by guojingbu on 2017/3/13.
 */

public class County extends DataSupport {
    /**
     *当前县的id
     */
    private int id;
    /**
     *县的名字
     */
    private String countyName;
    /**
     *县所对应的天气id
     */
    private String weahterId;
    /**
     *当前县所属市的id
     */
    private int cityId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getWeahterId() {
        return weahterId;
    }

    public void setWeahterId(String weahterId) {
        this.weahterId = weahterId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
