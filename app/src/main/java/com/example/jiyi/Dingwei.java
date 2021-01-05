package com.example.jiyi;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.example.jiyi.R;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dingwei extends AppCompatActivity{
    private MapView _mapView = null;
    private AMap _aMap = null;
    private AMapLocationClient _aMapLocationClient = null;
    private AMapLocationClientOption _aMapLocationClientOption = null;
    private ImageButton search_dingwei = null;
    private EditText et_key = null;
    private TextView cancel = null;
    private ListView pois = null;
    private ArrayList<Map<String,Object>> poifound;//存放POI用的
    private SimpleAdapter adapter = null;
    private String initAdress = null;

    private String _city = "null";//当前定位所在城市
    private Marker _selfMarker = null;
    private boolean isAddSelfMarker = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingwei);

        initUI();
        createMap(savedInstanceState);
        doLocation();
        doSearchPOI();

        //创建SimpleAdapter适配器将数据绑定到item显示控件上
        adapter = new SimpleAdapter(this, poifound, R.layout.item_dingwei,
                new String[]{"title", "province", "city", "district", "detail"}, new int[]{R.id.title, R.id.province, R.id.city, R.id.district, R.id.detail});
        //启用适配器
        pois.setAdapter(adapter);
        pois.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView pois = (ListView) parent;
                HashMap<String,Object> poifound = (HashMap<String, Object>) pois.getItemAtPosition(position);
                String title = poifound.get("title").toString();

                Intent intent = new Intent(Dingwei.this, NewNote.class);
                intent.putExtra("title",title);
                setResult(1001,intent);
                Dingwei.this.finish();
            }
        });
    }

    public void initUI() {//初始化界面
        _mapView = (MapView) findViewById(R.id.bmapView);
        search_dingwei = (ImageButton) findViewById(R.id.search_dingwei);
        et_key = (EditText) findViewById(R.id.et_key);
        cancel = (TextView) findViewById(R.id.chat_publish_complete_cancle);//返回上一级按钮
        cancel.setOnClickListener(new TextView.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dingwei.this, NewNote.class);
                setResult(1001,intent);
                Dingwei.this.finish();
            }
        });
        pois = (ListView)findViewById(R.id.lv_location_nearby);
        poifound =new ArrayList<>();
    }

    public void moveMap(double latitude, double longtitude) {
        LatLng lagLng = new LatLng(latitude, longtitude);
        _aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lagLng, _aMap.getCameraPosition().zoom));
    }

    public void addMarkerToMap(double latitude, double longtitude) {
        _selfMarker = _aMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longtitude))
                .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.position))));
    }

    public void createMap(Bundle savedInstanceState) {
        //展示地图容器
        _mapView.onCreate(savedInstanceState);
        //得到amap对象
        _aMap = _mapView.getMap();
    }

    //启动定位服务器
    public void doLocation() {
        _aMapLocationClient = new AMapLocationClient(getApplicationContext());
        _aMapLocationClientOption = new AMapLocationClientOption();
        _aMapLocationClientOption.setInterval(3000);//定位时间间隔

        _aMapLocationClient.setLocationOption(_aMapLocationClientOption);
        _aMapLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        Log.e("Amap", "location succ address=" + aMapLocation.getAddress());
                        Log.e("Amap", "city=" + aMapLocation.getCity());
                        Log.e("Amap", "longtitude=" + aMapLocation.getLongitude());
                        Log.e("Amap", "latitude=" + aMapLocation.getLatitude());
                        Log.e("Amap", "adname=" + aMapLocation.getAddress());
                        initAdress = aMapLocation.getAddress().toString();

                        if (isAddSelfMarker == false) {
                            addMarkerToMap(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                            isAddSelfMarker = true;

                            moveMap(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                        }
                        //et_key.setText(aMapLocation.getAddress());
                        _city = aMapLocation.getCity();
                    } else {
                        Log.e("Amap", "location error, code=" + aMapLocation.getErrorCode() + ".info = "
                                + aMapLocation.getErrorInfo());
                    }
                }
            }
        });
        _aMapLocationClient.startLocation();
    }

    public void doSearchPOI() {
        search_dingwei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Amap", "button click");//开始搜索POI
                String gjz = et_key.getText().toString();//输入框里的关键字
                PoiSearch.Query query = new PoiSearch.Query(gjz, "",_city);
                PoiSearch poiSearch = new PoiSearch(getApplicationContext(),query);
                poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {
                    @Override
                    public void onPoiSearched(PoiResult poiResult, int i) {
                        if(i != 1000){//搜索成功
                            Log.e("Amap","poi search error code = "+i);
                            return;
                        }
                        List<PoiItem> poiList = poiResult.getPois();

                        for(int index = 0;index<poiList.size();index++){
                            //处理搜索到的兴趣点
                            Log.e("Amap","搜索到的兴趣点有：");
                            PoiItem item = poiList.get(index);

                            Log.e("Amap","poi title="+item.getTitle()+
                                            "latitude="+item.getLatLonPoint().getLatitude()+
                                            "longtitude="+item.getLatLonPoint().getLongitude());
                            if(index <= 10) {//写入前十个POI
                                String temp1 = item.getTitle();
                                String temp2 = item.getProvinceName();
                                String temp3 = item.getCityName();
                                String temp4 = item.getAdName();
                                String temp5 = item.getSnippet();

                                Map<String, Object> map = new HashMap<>();
                                map.put("title", temp1);
                                map.put("province", temp2);
                                map.put("city", temp3);
                                map.put("district", temp4);
                                map.put("detail", temp5);

                                poifound.add(map);
                                adapter.notifyDataSetChanged();
                            }
                            addMarkerToMap(item.getLatLonPoint().getLatitude(),item.getLatLonPoint().getLongitude());
                        }
                    }

                    @Override
                    public void onPoiItemSearched(PoiItem poiItem, int i) {

                    }
                });
                poiSearch.searchPOIAsyn();
            }
        });
    }
}