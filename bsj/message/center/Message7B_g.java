package bsj.message.center;

import bsj.message.CenterMessage_g;

/**
 * 设置定时定次通过UDP上报一般位置信息
 * interval:2-65535
 * count:1-65535
 */
public class Message7B_g extends CenterMessage_g {

    private byte[] contents;

    @Override
    public byte[] getMsgType() {
        return new byte[]{0x7B};
    }

    @Override
    public byte[] getLength() {
        return new byte[]{0x00,0x0A};
    }

    @Override
    public byte[] getContents() {
        return this.contents;
    }

    public Message7B_g(int interval,int count) {
        this.contents = new byte[]{(byte)((interval >> 8) & 0x0f),(byte)interval,
                (byte)((count >> 8) & 0x0f),(byte)count};
    }
}
