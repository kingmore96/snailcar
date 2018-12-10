package model;

import java.io.Serializable;

/**
 * 思锐设备
 */
public class DeviceInfoSiruiModel extends DeviceInfoBaseModel implements Serializable {
    private String siruiImei;
    private String siruiAgServer;
    private String siruiAsServer;
    private String siruiBluetoothMac;
    private String siruiBluetoothId;
    private String siruiBluetoothKey;

    public String getSiruiImei() {
        return siruiImei;
    }

    public void setSiruiImei(String siruiImei) {
        this.siruiImei = siruiImei;
    }

    public String getSiruiAgServer() {
        return siruiAgServer;
    }

    public void setSiruiAgServer(String siruiAgServer) {
        this.siruiAgServer = siruiAgServer;
    }

    public String getSiruiAsServer() {
        return siruiAsServer;
    }

    public void setSiruiAsServer(String siruiAsServer) {
        this.siruiAsServer = siruiAsServer;
    }

    public String getSiruiBluetoothMac() {
        return siruiBluetoothMac;
    }

    public void setSiruiBluetoothMac(String siruiBluetoothMac) {
        this.siruiBluetoothMac = siruiBluetoothMac;
    }

    public String getSiruiBluetoothId() {
        return siruiBluetoothId;
    }

    public void setSiruiBluetoothId(String siruiBluetoothId) {
        this.siruiBluetoothId = siruiBluetoothId;
    }

    public String getSiruiBluetoothKey() {
        return siruiBluetoothKey;
    }

    public void setSiruiBluetoothKey(String siruiBluetoothKey) {
        this.siruiBluetoothKey = siruiBluetoothKey;
    }
}
