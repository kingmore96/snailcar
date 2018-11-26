package bsj.message;

import bsj.code.BufferParser_g;
import bsj.code.ConverTools_g;

/**
 * 中心消息抽象类，用于中心消息继承，提供构建消息方法
 */
public abstract class CenterMessage_g extends BaseMessage_g implements Message_g{
    /**
     * 构建一个消息,get方法子类可以自己实现
     * @return
     */
    public byte[] buildMessage(){
        //创建两个BufferParser用于存储消息
        BufferParser_g bufferParser_g = new BufferParser_g();
        BufferParser_g bufferCheckNumber = new BufferParser_g();

        if(this != null) {
            bufferParser_g.writeBytes(this.getHeader());
            bufferCheckNumber.writeBytes(this.getHeader());

            bufferParser_g.writeBytes(this.getMsgType());
            bufferCheckNumber.writeBytes(this.getMsgType());

            bufferParser_g.writeBytes(this.getLength());
            bufferCheckNumber.writeBytes(this.getLength());

            bufferParser_g.writeBytes(this.getIp());
            bufferCheckNumber.writeBytes(this.getIp());

            if (this.getContents() != null && this.getContents().length > 0) {
                bufferParser_g.writeBytes(this.getContents());
                bufferCheckNumber.writeBytes(this.getContents());
            }

            byte checkSum = ConverTools_g.getCheckSum(bufferCheckNumber.getBufferOutPut());
            bufferParser_g.writeByte(checkSum);
            bufferParser_g.writeBytes(this.getEnd());
        }
        return bufferParser_g.getBufferOutPut();
    }
}
