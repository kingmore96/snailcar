package bsj.message;

import bsj.code.BufferParser_g;

/**
 * 客户端消息层，初始化length和校验和，并获取ip
 */
public abstract class ClientMessage_g extends BaseMessage_g implements Message_g {
    private byte[] contents;
    private byte[] length;
    private byte checkNumber;

    protected byte[] bytes;

    @Override
    public byte[] getContents() {
        return contents;
    }

    @Override
    public byte[] getLength() {
        return length;
    }

    public byte getCheckNumber() {
        return checkNumber;
    }

    public byte[] getBytes() {
        return bytes;
    }

    /**
     * 构造函数，对属性赋值
     * @param bytes
     */
    public ClientMessage_g(byte[] bytes){
        this.bytes = bytes;
        BufferParser_g bf = new BufferParser_g(this.bytes);
        byte[] header = bf.readBytes(2);
        byte[] msgType = bf.readBytes(1);
        this.length = bf.readBytes(2);
        this.ip = bf.readBytes(4);
        this.contents = bf.readByLength(9,bytes.length -11,bytes);
        this.checkNumber = bf.readByLength(9+bytes.length-11+1,1,bytes)[0];
    }
}
