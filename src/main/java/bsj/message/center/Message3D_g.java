package bsj.message.center;

import bsj.message.CenterMessage_g;

/**
 * [功能] 查看指定车辆的版本信息，要求返回车载软件版本信息，硬件版本信息，程序校验及通讯模块版本信息等
 */
public class Message3D_g extends CenterMessage_g {

    @Override
    public byte[] getMsgType() {
        return new byte[]{0x3D};
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
