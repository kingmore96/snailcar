package model;

/**
 * ip 车架码 sim_no
 */
public abstract class DeviceInfoBaseModel extends BaseEntity {

    protected String ip;
    protected String engineNo;
    protected String simNo;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getSimNo() {
        return simNo;
    }

    public void setSimNo(String simNo) {
        this.simNo = simNo;
    }
}
