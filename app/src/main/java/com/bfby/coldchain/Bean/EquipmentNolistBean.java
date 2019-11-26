package com.bfby.coldchain.Bean;

import java.io.Serializable;

public class EquipmentNolistBean implements Serializable {


    /**
     * ID : 0198459E-2034-4533-B843-5D227AD207423
     * data_ID : null
     * deviceNumber : HRT15091600003
     * name : null
     * type : null
     * uploadPeriod : null
     * state : 1
     * isOffline : null
     * workStatus : null
     * dataTime : null
     * WenDu : 1.0
     * ShiDu : 2.0
     * DianLiang : 3.0
     * XinHaoQiangDu : 4.0
     * JingDu : 5.0
     * WeiDu : 6.0
     */

    private String ID;
    private Object data_ID;
    private String deviceNumber;
    private String name;
    private String type;
    private String uploadPeriod;
    private int state;
    private String isOffline;
    private String workStatus;
    private String dataTime;
    private float WenDu;
    private float ShiDu;
    private float DianLiang;
    private float XinHaoQiangDu;
    private float JingDu;
    private float WeiDu;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Object getData_ID() {
        return data_ID;
    }

    public void setData_ID(Object data_ID) {
        this.data_ID = data_ID;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUploadPeriod() {
        return uploadPeriod;
    }

    public void setUploadPeriod(String uploadPeriod) {
        this.uploadPeriod = uploadPeriod;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getIsOffline() {
        return isOffline;
    }

    public void setIsOffline(String isOffline) {
        this.isOffline = isOffline;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public float getWenDu() {
        return WenDu;
    }

    public void setWenDu(float wenDu) {
        WenDu = wenDu;
    }

    public float getShiDu() {
        return ShiDu;
    }

    public void setShiDu(float shiDu) {
        ShiDu = shiDu;
    }

    public float getDianLiang() {
        return DianLiang;
    }

    public void setDianLiang(float dianLiang) {
        DianLiang = dianLiang;
    }

    public float getXinHaoQiangDu() {
        return XinHaoQiangDu;
    }

    public void setXinHaoQiangDu(float xinHaoQiangDu) {
        XinHaoQiangDu = xinHaoQiangDu;
    }

    public float getJingDu() {
        return JingDu;
    }

    public void setJingDu(float jingDu) {
        JingDu = jingDu;
    }

    public float getWeiDu() {
        return WeiDu;
    }

    public void setWeiDu(float weiDu) {
        WeiDu = weiDu;
    }
}
