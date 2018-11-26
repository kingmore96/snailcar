package bsj.message.center;

import bsj.message.CenterMessage_g;

/**
 * 行驶里程初始化
 */
public class Message66_g extends CenterMessage_g {
    private byte[] contents;

    @Override
    public byte[] getMsgType() {
        return new byte[]{0x66};
    }

    @Override
    public byte[] getLength() {
        return new byte[]{0x00,0x09};
    }

    @Override
    public byte[] getContents() {
        return new byte[0];
    }

    public Message66_g(int mileage){
        this.contents = new byte[]{(byte)((mileage >> 16) & 0x0f),(byte)((mileage >> 8) & 0x0f),(byte)mileage};
    }
}
