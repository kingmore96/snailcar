package bsj.message.center;

import bsj.message.CenterMessage_g;

/**
 * 修改UDP的IP和端口
 * 格式为：“xxx.xxx.xxx.xxx”,xxxxx ip不足3位要在前面补0，端口最大65535，需要ascii编码
 */
public class Message69_g extends CenterMessage_g {

    private byte[] contents;

    @Override
    public byte[] getMsgType() {
        return new byte[]{0x69};
    }

    @Override
    public byte[] getLength() {
        int length = 6 + this.contents.length;
        return new byte[]{(byte)((length >> 8) & 0x0f),(byte)length};
    }

    @Override
    public byte[] getContents() {
        return this.contents;
    }

    //构造ip格式：“xxx.xxx.xxx.xxx”,xxxxx
    // TODO
    public Message69_g(String ip,int port){
        String[] strArr = ip.split("\\.");
        StringBuilder sb = new StringBuilder();
        sb.append("\"");

        for (int i = 0; i < strArr.length; i++) {

        }
    }





}
