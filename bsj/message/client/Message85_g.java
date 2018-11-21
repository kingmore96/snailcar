package bsj.message.client;

import bsj.message.LocationMessage_g;

/**
 * 车载设备确认应答包：包含一般位置信息
 */
public class Message85_g extends LocationMessage_g {

    /**
     * 构造函数，对属性赋值
     *
     * @param bytes
     */
    public Message85_g(byte[] bytes) {
        super(bytes);
    }

    @Override
    public byte[] getMsgType() {
        return new byte[]{(byte) 0x85};
    }
}
