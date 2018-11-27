package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String request = (String) msg;
        String response = null;

        if("QUERY TIME ORDER".equals(msg)){
            response = new Date(System.currentTimeMillis()).toString();
        }else{
           response = "BAD REQUEST!";
        }
        response = response + System.getProperty("line.separator");
        ByteBuf byteBuf = Unpooled.copiedBuffer(response.getBytes());
        ctx.writeAndFlush(byteBuf);
    }
}
