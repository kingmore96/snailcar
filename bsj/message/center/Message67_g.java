package bsj.message.center;

import bsj.message.CenterMessage_g;

/**
 * 开门指令
 */
public class Message67_g extends CenterMessage_g {
    @Override
    public byte[] getMsgType() {
        return new byte[]{0x67};
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
