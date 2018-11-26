package bsj.message.center;

import bsj.message.CenterMessage_g;

/**
 * 单次点名查车，查看指定车辆当前位置
 */
public class Message30_g extends CenterMessage_g {

    @Override
    public byte[] getMsgType() {
        return new byte[]{0x30};
    }

    @Override
    public byte[] getLength() {
        return new byte[]{0x00,0x06};
    }

    @Override
    public byte[] getContents() {
        return null;
    }
}
