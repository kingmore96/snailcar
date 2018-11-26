package bsj.message.center;

import bsj.message.CenterMessage_g;

/**
 *TCP心跳时间间隔：每隔n分钟通过TCP上报一次一般位置信息（1-255分钟）
 */
public class Message7A_g extends CenterMessage_g {
    private byte[] contents;

    @Override
    public byte[] getMsgType() {
        return new byte[]{0x7A};
    }

    @Override
    public byte[] getLength() {
        return new byte[]{0x00,0x07};
    }

    @Override
    public byte[] getContents() {
        return this.contents;
    }

    public Message7A_g(int interval) {
        this.contents = new byte[]{(byte)interval};
    }
}
