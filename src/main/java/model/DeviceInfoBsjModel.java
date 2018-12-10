package model;

import java.io.Serializable;

/**
 * 博实结设备
 */
public class DeviceInfoBsjModel extends DeviceInfoBaseModel implements Serializable {
    private String deviceSn;
    private String ipSimNo;

    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
    }

    public String getIpSimNo() {
        return ipSimNo;
    }

    public void setIpSimNo(String ipSimNo) {
        this.ipSimNo = ipSimNo;
    }
}
