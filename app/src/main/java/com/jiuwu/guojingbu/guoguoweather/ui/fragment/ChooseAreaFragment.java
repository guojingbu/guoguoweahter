package com.jiuwu.guojingbu.guoguoweather.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jiuwu.guojingbu.guoguoweather.R;
import com.jiuwu.guojingbu.guoguoweather.db.City;
import com.jiuwu.guojingbu.guoguoweather.db.County;
import com.jiuwu.guojingbu.guoguoweather.db.Province;
import com.jiuwu.guojingbu.guoguoweather.utils.HttpAPIUtils;
import com.jiuwu.guojingbu.guoguoweather.utils.HttpClient;
import com.jiuwu.guojingbu.guoguoweather.utils.JsonCallback;
import com.jiuwu.guojingbu.guoguoweather.utils.LogUtil;
import com.jiuwu.guojingbu.guoguoweather.utils.Utility;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author guojingbu
 */

public class ChooseAreaFragment extends BaseFragment {
    private static final int LEVEL_PROVICE = 0;
    private static final int LEVEL_CITY = 1;
    private static final int LEVEL_COUNTY = 2;


    private ListView mListView;
    private TextView mTile;
    private Button mBack;
    private List<String> datalist = new ArrayList<>();
    /**
     * 省列表
     */
    private List<Province> provinceList;
    /**
     * 市列表
     */
    private List<City> cityList;
    /**
     * 县区列表
     */
    private List<County> countyList;
    /**
     * 选中的省份
     */
    private Province selectedProvince;
    /**
     * 选中的市
     */
    private City selectedCity;

    private ArrayAdapter<String> adapter;
    /**
     * 当前选中的级别
     */
    private int currentLevel;

    @Override
    public View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.choose_area, null);
        mListView = (ListView) view.findViewById(R.id.list_view);
        mTile = (TextView) view.findViewById(R.id.title);
        mBack = (Button) view.findViewById(R.id.back_button);
        return view;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, datalist);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (currentLevel == LEVEL_PROVICE) {
                    selectedProvince = provinceList.get(i);
                    queryCitys();
                } else if (currentLevel == LEVEL_CITY) {
                    selectedCity = cityList.get(i);
                    queryCountys();
                }
            }
        });
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentLevel == LEVEL_COUNTY) {
                    queryCitys();
                } else if (currentLevel == LEVEL_CITY) {
                    queryProvinces();
                }
            }
        });

        queryProvinces();
    }


    private void queryProvinces() {
        mTile.setText(getString(R.string.china));
        mBack.setVisibility(View.GONE);
        provinceList = DataSupport.findAll(Province.class);
        if (provinceList.size() > 0) {
            datalist.clear();
            for (Province province : provinceList) {
                datalist.add(province.getProvinceName());
            }
            adapter.notifyDataSetChanged();
            mListView.setSelection(0);
            currentLevel = LEVEL_PROVICE;

        } else {
            queryfromserver(HttpAPIUtils.BASE_URL, "province");
        }
    }

    private void queryfromserver(String address, final String type){
        showProgressDialog();
        HttpClient.getInstance().sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 通过runOnUiThread()方法回到主线程处理逻辑
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dismissProgressDialog();
                        Toast.makeText(getContext(), "加载失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();

                boolean result = false;
                if ("province".equals(type)) {
                    result = Utility.handleProvinceResponse(responseText);
                } else if ("city".equals(type)) {
                    result = Utility.handleCityResponse(responseText, selectedProvince.getId());
                } else if ("county".equals(type)) {
                    LogUtil.i("guojingbu","data = "+ responseText);
                    result = Utility.handleCountyResponse(responseText, selectedCity.getId());
                }
                if (result) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dismissProgressDialog();
                            if ("province".equals(type)) {
                                queryProvinces();
                            } else if ("city".equals(type)) {
                                queryCitys();
                            } else if ("county".equals(type)) {
                                queryCountys();
                            }
                        }
                    });
                }
            }
        });


    }

//    /**
//     * 根据传入的类型查询省市县数据
//     *
//     * @param address
//     * @param type
//     */
//    private void queryfromServer(String address, final String type) {
//        showProgressDialog();
//        HttpClient.getInstance().SendOkHttpRequest(address, new JsonCallback() {
//            @Override
//            public void failure(String s, IOException e) {
//                dismissProgressDialog();
//                Toast.makeText(mContext, getString(R.string.load_failure), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void success(final String responseText) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        boolean result = false;
//                        if ("province".equals(type)) {
//                            result = Utility.handleProvinceResponse(responseText);
//                        } else if ("city".equals(type)) {
//                            result = Utility.handleCityResponse(responseText, selectedProvince.getId());
//                        } else if ("county".equals(type)) {
//                            result = Utility.handleCountyResponse(responseText, selectedCity.getId());
//                        }
//                        if (result) {
//                            getActivity().runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    dismissProgressDialog();
//                                    if ("province".equals(type)) {
//                                        queryProvinces();
//                                    } else if ("city".equals(type)) {
//                                        queryCitys();
//                                    } else if ("county".equals(type)) {
//                                        queryCountys();
//                                    }
//                                }
//                            });
//
//                        }
//                    }
//                }).start();
//            }
//        });
//    }

    private void queryCitys() {
        mTile.setText(selectedProvince.getProvinceName());
        mBack.setVisibility(View.VISIBLE);
        cityList = DataSupport.where("provinceid = ?", String.valueOf(selectedProvince.getId())).find(City.class);
        if (cityList.size() > 0) {
            datalist.clear();
            for (City city : cityList) {
                datalist.add(city.getCityName());
            }
            adapter.notifyDataSetChanged();
            mListView.setSelection(0);
            currentLevel = LEVEL_CITY;

        } else {
            int provinceCode = selectedProvince.getProvinceCode();
            String address = HttpAPIUtils.BASE_URL + "/" + provinceCode;
            queryfromserver(address, "city");
        }
    }

    private void queryCountys() {
        mTile.setText(selectedCity.getCityName());
        mBack.setVisibility(View.VISIBLE);
        countyList = DataSupport.where("cityid = ?", String.valueOf(selectedCity.getId())).find(County.class);
        if (countyList.size() > 0) {
            datalist.clear();
            for (County county : countyList) {
                LogUtil.i("guojingbu","county = "+county.getCountyName());
                datalist.add(county.getCountyName());
            }
            adapter.notifyDataSetChanged();
            mListView.setSelection(0);
            currentLevel = LEVEL_COUNTY;

        } else {
            int provinceCode = selectedProvince.getProvinceCode();
            int cityCode = selectedCity.getCityCode();
            String address = HttpAPIUtils.BASE_URL + "/" + provinceCode + "/" + cityCode;
            LogUtil.i("guojingbu","address = "+address);
            queryfromserver(address, "county");
        }
    }
}
