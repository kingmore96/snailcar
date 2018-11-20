package bsj.message.center;

import bsj.message.CenterMessage_g;

/**
 * 关门指令
 */
public class Message68_g extends CenterMessage_g {
    @Override
    public byte[] getMsgType() {
        return new byte[]{0x68};
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
