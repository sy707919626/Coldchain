package com.bfby.coldchain.Bean;

import java.io.Serializable;
import java.util.List;

public class SensorBean implements Serializable {

    /**
     * deviceNumber : HRT15091600005
     * name : 演示设备1
     * type : M300-TH
     * uploadPeriod : 5
     * state : 1
     * isOffline : 0
     * workStatus : running
     * hardwareVersion : 3.1
     * softwareVersion : 5.3.1.3
     * rs : [{"maddress":"1","aname":"temperature","bname":"温度1","min":"40","max":"1","data":"23.8","dataTime":"2018/3/22 17:49:41","state":"1","isOffline":"0"}]
     */

    private String deviceNumber;
    private String name;
    private String type;
    private String uploadPeriod;
    private String state;
    private String isOffline;
    private String workStatus;
    private String hardwareVersion;
    private String softwareVersion;
    private List<RsBean> rs;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
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

    public String getHardwareVersion() {
        return hardwareVersion;
    }

    public void setHardwareVersion(String hardwareVersion) {
        this.hardwareVersion = hardwareVersion;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public List<RsBean> getRs() {
        return rs;
    }

    public void setRs(List<RsBean> rs) {
        this.rs = rs;
    }

    public static class RsBean {
        /**
         * maddress : 1
         * aname : temperature
         * bname : 温度1
         * min : 40
         * max : 1
         * data : 23.8
         * dataTime : 2018/3/22 17:49:41
         * state : 1 设备状态（0：正常 1：报警）
         * isOffline : 0
         */

        private String maddress;
        private String aname;
        private String bname;
        private String min;
        private String max;
        private String data;
        private String dataTime;
        private String state;
        private String isOffline;

        public String getMaddress() {
            return maddress;
        }

        public void setMaddress(String maddress) {
            this.maddress = maddress;
        }

        public String getAname() {
            return aname;
        }

        public void setAname(String aname) {
            this.aname = aname;
        }

        public String getBname() {
            return bname;
        }

        public void setBname(String bname) {
            this.bname = bname;
        }

        public String getMin() {
            return min;
        }

        public void setMin(String min) {
            this.min = min;
        }

        public String getMax() {
            return max;
        }

        public void setMax(String max) {
            this.max = max;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getDataTime() {
            return dataTime;
        }

        public void setDataTime(String dataTime) {
            this.dataTime = dataTime;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getIsOffline() {
            return isOffline;
        }

        public void setIsOffline(String isOffline) {
            this.isOffline = isOffline;
        }
    }
}
