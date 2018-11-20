package bsj.message.center;

import bsj.code.BCDCode_g;
import bsj.code.BufferParser_g;
import bsj.message.CenterMessage_g;
import bsj.message.IncloseMessage_g;

import java.util.ArrayList;
import java.util.List;

/**
 * 下发矩形电子围栏给指定车辆
 */
public class Message46_g extends CenterMessage_g {

    private byte[] contents;
    private List<IncloseMessage_g> msgList;

    @Override
    public byte[] getMsgType() {
        return new byte[]{0x46};
    }

    @Override
    public byte[] getLength() {
        int length = 6 + this.contents.length;
        return new byte[]{(byte)((length >> 8) & 0x0f),(byte)length};
    }

    @Override
    public byte[] getContents() {
        if(this.contents != null){
            return this.contents;
        }
        BufferParser_g bf = new BufferParser_g();
        bf.writeByte((byte)msgList.size());

        for (int i = 0; i < msgList.size(); i++) {
            IncloseMessage_g msg = msgList.get(i);
            bf.writeBytes(BCDCode_g.str2Bcd(msg.getMinLat()));
            bf.writeBytes(BCDCode_g.str2Bcd(msg.getMinPre()));
            bf.writeBytes(BCDCode_g.str2Bcd(msg.getMaxLat()));
            bf.writeBytes(BCDCode_g.str2Bcd(msg.getMaxPre()));
            bf.writeByte((byte) msg.getIndex());
            bf.writeByte((byte) msg.getType());
        }
        this.contents = bf.getBufferOutPut();
        return this.contents;
    }

    public Message46_g() {

    }
    /**
     * 添加方法，添加信息
     * @param msg
     */
    public void add(IncloseMessage_g msg){
        if( msgList == null ){
            msgList = new ArrayList<IncloseMessage_g>();
        }
        msgList.add(msg);
    }
}
