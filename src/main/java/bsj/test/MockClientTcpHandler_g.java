package bsj.test;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * tcp规则，接收30和67 68指令，回复80和85指令
 */
public class MockClientTcpHandler_g extends SimpleChannelInboundHandler<byte[]> {
    protected void channelRead0(ChannelHandlerContext ctx, byte[] msg) throws Exception {
        if(msg[2] == 0x30){
            byte[] returnMsg = MessageBuilder_g.createMsg(msg, 81);
            ctx.channel().writeAndFlush(returnMsg);
        }else if(msg[2] == 0x67 || msg[2] == 0x68){
            byte[] returnMsg = MessageBuilder_g.createMsg(msg, 85);
            ctx.channel().writeAndFlush(returnMsg);
        }else{
            throw new RuntimeException("未实现");
        }
    }
}
