package bsj.message.client;

import bsj.message.LocationMessage_g;

/**
 * 上发申请校时
 */
public class MessageD6_g extends LocationMessage_g {
    /**
     * 构造函数，对属性赋值
     *
     * @param bytes
     */
    public MessageD6_g(byte[] bytes) {
        super(bytes);
    }

    @Override
    public byte[] getMsgType() {
        return new byte[]{(byte) 0xD6};
    }
}
