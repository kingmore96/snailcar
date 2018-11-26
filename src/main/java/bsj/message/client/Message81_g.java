package bsj.message.client;

import bsj.message.LocationMessage_g;

/**
 * 当终端收到点名查看指令时，上报一般位置信息
 */
public class Message81_g extends LocationMessage_g {

    /**
     * 构造函数，对属性赋值
     *
     * @param bytes
     */
    public Message81_g(byte[] bytes) {
        super(bytes);
    }

    @Override
    public byte[] getMsgType() {
        return new byte[]{(byte) 0x81};
    }
}
