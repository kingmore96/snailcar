package bsj.message;

import bsj.code.Conver_g;

/**
 * BaseMessage，封装了包头包尾和伪ip
 */
public abstract class BaseMessage_g {
    protected byte[] header = new byte[]{0x29,0x29};
    protected byte[] end = new byte[]{0x0D};
    protected byte[] ip;

    public byte[] getHeader() {
        return header;
    }

    public void setHeader(byte[] header) {
        this.header = header;
    }

    public byte[] getEnd() {
        return end;
    }

    public void setEnd(byte[] end) {
        this.end = end;
    }

    public byte[] getIp() {
        return ip;
    }

    public void setIp(byte[] ip) {
        this.ip = ip;
    }

    public void setIp(String ip){
        this.ip = Conver_g.hexString2Bytes(ip);
    }
}
