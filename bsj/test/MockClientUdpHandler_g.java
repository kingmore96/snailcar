package bsj.test;

import bsj.code.Conver_g;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;


/**
 * udp规则：打印msgType即可
 */
public class MockClientUdpHandler_g extends SimpleChannelInboundHandler<DatagramPacket> {

    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        ByteBuf buf = msg.copy().content();
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String msgType = Conver_g.bytes2HexString(req);
        System.out.println(msgType);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
