package bsj.message.client;

import bsj.message.ClientMessage_g;

/**
 * 上发版本或状态信息<br>
 * [功 能] 向中心上发版本或状态信息<br>
 * [中心应答] 中心确认包 （TCP方式上发时，不需中心确认包下发）
 *
 */
public class MessageB3_g extends ClientMessage_g {
    /**
     * 构造函数，对属性赋值
     *
     * @param bytes
     */
    public MessageB3_g(byte[] bytes) {
        super(bytes);
    }

    @Override
    public byte[] getMsgType() {
        return new byte[]{(byte) 0xB3};
    }
}
