package bsj.message.center;

import bsj.message.CenterMessage_g;

/**
 * 查看指定车辆当前设置相关参数
 */
public class Message31_g extends CenterMessage_g {
    @Override
    public byte[] getMsgType() {
        return new byte[]{0x31};
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
