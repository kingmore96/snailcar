package bsj.message.center;

import bsj.message.CenterMessage_g;

/**
 * 查询电子围栏
 */
public class Message48_g extends CenterMessage_g {

    @Override
    public byte[] getMsgType() {
        return new byte[]{0x48};
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
