package bsj.message;

import bsj.code.BufferParser_g;

/**
 * 一般位置数据信息，特殊化上一层的contents
 */
public abstract class LocationMessage_g extends ClientMessage_g {
    //年月日时分秒
    private byte[] dateTime;
    //纬度
    private byte[] latitude;
    //经度
    private byte[] longtitude;
    //速度
    private byte[] speed;
    //方向
    private byte[] direction;
    //状态
    private byte[] status;
    //里程
    private byte[] mileage;


    /**
     * 构造函数，对属性赋值
     *
     * @param bytes
     */
    public LocationMessage_g(byte[] bytes) {
        super(bytes);
        BufferParser_g bf = new BufferParser_g(bytes);
        byte[] header = bf.readBytes(2);
        byte[] msgType = bf.readBytes(1);
        byte[] length = bf.readBytes(2);
        this.ip = bf.readBytes(4);
        this.dateTime = bf.readBytes(6);
        this.latitude = bf.readBytes(4);
        this.longtitude = bf.readBytes(4);
        this.speed = bf.readBytes(2);
        this.direction = bf.readBytes(2);
        this.status = bf.readBytes(1);
        this.mileage = bf.readBytes(3);
    }

    public byte[] getDateTime() {
        return dateTime;
    }

    public void setDateTime(byte[] dateTime) {
        this.dateTime = dateTime;
    }

    public byte[] getLatitude() {
        return latitude;
    }

    public void setLatitude(byte[] latitude) {
        this.latitude = latitude;
    }

    public byte[] getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(byte[] longtitude) {
        this.longtitude = longtitude;
    }

    public byte[] getSpeed() {
        return speed;
    }

    public void setSpeed(byte[] speed) {
        this.speed = speed;
    }

    public byte[] getDirection() {
        return direction;
    }

    public void setDirection(byte[] direction) {
        this.direction = direction;
    }

    public byte[] getStatus() {
        return status;
    }

    public void setStatus(byte[] status) {
        this.status = status;
    }

    public byte[] getMileage() {
        return mileage;
    }

    public void setMileage(byte[] mileage) {
        this.mileage = mileage;
    }
}
