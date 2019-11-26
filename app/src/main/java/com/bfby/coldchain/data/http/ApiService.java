package com.bfby.coldchain.data.http;

import com.bfby.coldchain.Bean.BaseBean;
import com.bfby.coldchain.Bean.BaseBeans;
import com.bfby.coldchain.Bean.CarOrderFeeBean;
import com.bfby.coldchain.Bean.DriverInfoBean;
import com.bfby.coldchain.Bean.EquipmentNolistBean;
import com.bfby.coldchain.Bean.LoginBean;
import com.bfby.coldchain.Bean.EquipmentBean;
import com.bfby.coldchain.Bean.EquipmentDetailsBean;
import com.bfby.coldchain.Bean.EquipmentFeeBean;
import com.bfby.coldchain.Bean.VehicleType;
import com.bfby.coldchain.Bean.HistoryDetailBean;
import com.bfby.coldchain.Bean.HistoryListBean;
import com.bfby.coldchain.Bean.userInfoBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    //登录用户
    @GET("/api/Auth/Login")
    Observable<BaseBean<LoginBean>> Login(@Query("username") String username, @Query("userpwd") String userpwd);

    //获取设备编号
    @POST("/api/Auth/GetDeviceList")
    Observable<BaseBeans<EquipmentNolistBean>> GetDeviceList(@Query("UserId") String UserId);

    //获取设备编号
    @GET("getDeviceInfor")
    Observable<String> getDeviceInfor(@Query("deviceNumber") String deviceNumber);
}


