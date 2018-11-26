package bsj.message;

/**
 * 矩形围栏信息
 */
public class IncloseMessage_g {
    //小纬度
    private String minLat;
    //小经度
    private String minPre;
    //大纬度
    private String maxLat;
    //大经度
    private String maxPre;
    //围栏编号
    private int index;
    //报警方式
    private int type;

    public IncloseMessage_g(String minLat, String minPre, String maxLat, String maxPre, int index, int type) {
        this.minLat = minLat;
        this.minPre = minPre;
        this.maxLat = maxLat;
        this.maxPre = maxPre;
        this.index = index;
        this.type = type;
    }

    public String getMinLat() {
        return minLat;
    }

    public String getMinPre() {
        return minPre;
    }

    public String getMaxLat() {
        return maxLat;
    }

    public String getMaxPre() {
        return maxPre;
    }

    public int getIndex() {
        return index;
    }

    public int getType() {
        return type;
    }
}
