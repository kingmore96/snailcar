package bsj.message.center;

import bsj.message.CenterMessage_g;
import bsj.message.ClientMessage_g;

/**
 * 中心确认包,收到有效UDP数据包，中心下发确认包
 */
public class Message21_g extends CenterMessage_g {

    private byte[] contents;

    @Override
    public byte[] getMsgType() {
        return new byte[]{0x21};
    }

    @Override
    public byte[] getLength() {
        return new byte[]{0x00,0x05};
    }

    @Override
    public byte[] getContents() {
        return this.contents;
    }

    public Message21_g(ClientMessage_g clientMessage_g) {
        byte[] msgType = clientMessage_g.getMsgType();
        byte checkNumber = clientMessage_g.getCheckNumber();
        this.contents = new byte[]{checkNumber,msgType[0],0};
        if(msgType.length > 1){
            byte subMsgType = msgType[1];
            this.contents = new byte[]{checkNumber,msgType[0],subMsgType};
        }
    }
}
