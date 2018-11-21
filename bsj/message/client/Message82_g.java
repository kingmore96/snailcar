package bsj.message.client;

import bsj.message.LocationMessage_g;

/**
 * 特殊报警数据 <br>
 * [功能]当车载设备产生节点警情时，向网络中心发送当前报警信息 <br>
 * [通讯方式] UDP/SMS <br>
 * [去向] 网络中心<br>
 * [中心应答] 中心确认包
 *
 */
public class Message82_g extends LocationMessage_g {
    /**
     * 构造函数，对属性赋值
     *
     * @param bytes
     */
    public Message82_g(byte[] bytes) {
        super(bytes);
    }

    @Override
    public byte[] getMsgType() {
        return new byte[]{(byte) 0x82};
    }
}
