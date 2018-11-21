package bsj.test;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * tcp规则，接收30和67 68指令，回复80和85指令
 */
public class MockClientTcpHandler extends SimpleChannelInboundHandler<byte[]> {
    protected void channelRead0(ChannelHandlerContext ctx, byte[] msg) throws Exception {
          
    }
}
