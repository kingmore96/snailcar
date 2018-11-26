package bsj.message.center;

import bsj.code.Conver_g;
import bsj.message.CenterMessage_g;

import java.io.UnsupportedEncodingException;

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

    /**
     * 构造格式，初始化contents
     * @param ip
     * @param port
     * @throws UnsupportedEncodingException
     */
    public Message69_g(String ip,int port) throws UnsupportedEncodingException {
        String[] strArr = ip.split("\\.");
        StringBuilder sb = new StringBuilder();
        sb.append("\"");

        for (int i = 0; i < strArr.length; i++) {
            String ip1 = strArr[i];
            sb.append(addZero(ip1,3));
            if(i < strArr.length - 1){
                sb.append(".");
            }
        }

        sb.append("\",");
        sb.append(port);
        this.contents = sb.toString().getBytes("US-ASCII");
    }

    /**
     * 左补0
     * @param str
     * @param length
     * @return
     */
    private String addZero(String str, int length) {
        while(str.length() < length){
            StringBuilder sb = new StringBuilder();
            sb.append("0");
            sb.append(str);
            str = sb.toString();
        }
        return str;
    }

    /**
     * 测试用例
     * @param args
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        Message69_g message69_g = new Message69_g("219.232.191.073",8890);
        message69_g.setIp("00000000");
        String s = Conver_g.bytes2HexString(message69_g.getContents());
        System.out.println(s);
        String s1 = Conver_g.bytes2HexString(message69_g.buildMessage());
        System.out.println(s1);
    }
}
