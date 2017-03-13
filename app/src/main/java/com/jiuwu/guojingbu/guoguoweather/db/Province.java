package com.jiuwu.guojingbu.guoguoweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by guojingbu on 2017/3/13.
 */

public class Province extends DataSupport {
    /**
     * 省id
     */
    private int id;
    /**
     * 省的名字
     */
    private String provinceName;
    /**
     * 省代码
     */
    private int provinceCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }
}
