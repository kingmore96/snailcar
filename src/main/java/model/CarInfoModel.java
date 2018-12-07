package model;

import java.io.Serializable;

/**
 * 车辆信息，可用于车辆管理,对应line_car_info表
 */
public class CarInfoModel extends BaseEntity implements Serializable {
    private String engineNo;
    private Integer interiorOrder;
    private String plateNumber;
    private String brand;
    private String model;
    private Integer engineNumber;
    private String lotNumber;
    private String oneKeyStart;
    private String keyNumber;
    private String remark;

    public CarInfoModel() {
    }

    public CarInfoModel(String engineNo, Integer interiorOrder, String plateNumber, String brand, String model, Integer engineNumber, String lotNumber, String oneKeyStart, String keyNumber, String remark) {
        this.engineNo = engineNo;
        this.interiorOrder = interiorOrder;
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.model = model;
        this.engineNumber = engineNumber;
        this.lotNumber = lotNumber;
        this.oneKeyStart = oneKeyStart;
        this.keyNumber = keyNumber;
        this.remark = remark;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public Integer getInteriorOrder() {
        return interiorOrder;
    }

    public void setInteriorOrder(Integer interiorOrder) {
        this.interiorOrder = interiorOrder;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(Integer engineNumber) {
        this.engineNumber = engineNumber;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getOneKeyStart() {
        return oneKeyStart;
    }

    public void setOneKeyStart(String oneKeyStart) {
        this.oneKeyStart = oneKeyStart;
    }

    public String getKeyNumber() {
        return keyNumber;
    }

    public void setKeyNumber(String keyNumber) {
        this.keyNumber = keyNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
