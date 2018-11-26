package bsj.message.center;

import bsj.message.CenterMessage_g;

/**
 * 设置点火时（ACC档开），车辆定时上传位置信息时间间隔:3-65535秒
 */
public class Message34_g extends CenterMessage_g {

    private byte[] contents;

    @Override
    public byte[] getMsgType() {
        return new byte[]{0x34};
    }

    @Override
    public byte[] getLength() {
        return new byte[]{0x00,0x08};
    }

    @Override
    public byte[] getContents() {
        return this.contents;
    }

    public Message34_g(int interval){
        this.contents = new byte[]{(byte)((interval >> 8) & 0x0f),(byte)interval};
    }
}
