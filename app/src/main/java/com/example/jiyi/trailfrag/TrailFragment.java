package com.example.jiyi.trailfrag;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jiyi.R;

import android.graphics.Color;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.CustomMapStyleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrailFragment extends Fragment {


    public TrailFragment() {
        // Required empty public constructor
    }

    MapView mMapView = null;
    AMap aMap;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_trail, container, false);

        View view = inflater.inflate(R.layout.fragment_trail, container, false);
        //获取地图空间引用
        mMapView = (MapView) view.findViewById(R.id.map);
        // 在acitivity执行OnCreate时执行mMapView.onCreate(savedInstanceState);
        mMapView.onCreate(savedInstanceState);// 此方法必须重写

        if (aMap == null){
            aMap = mMapView.getMap();
        }

        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。

//aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。

        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.mipmap.location));
        myLocationStyle.radiusFillColor(Color.argb(0,0,0,0));
        myLocationStyle.strokeColor(Color.TRANSPARENT);
        //定位
        float u = (float) 121.47;
        float v = (float) 31.231706;
        //地图样式
//        aMap.setCustomMapStyle(
//                new com.amap.api.maps.model.CustomMapStyleOptions()
//                        .setEnable(true)
//                        .setStyleDataPath("/mnt/style.data")
//                        .setStyleExtraPath("/mnt/style_extra.data")
//
//        );

        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style


        LatLng latLng = new LatLng(39.906901,116.397972);
        //final Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title("北京").snippet("天气很好"));

        //MarkerOptions markerOption = new MarkerOptions();
        //markerOption.position(latLng);
        //markerOption.title("北京").snippet("今天很开心");

        //markerOption.draggable(true);//设置Marker可拖动
        //markerOption.icon(BitmapDescriptorFactory.fromResource(R.mipmap.jidan));
        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
        //markerOption.setFlat(true);//设置marker平贴地图效果
        return view;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }


}
