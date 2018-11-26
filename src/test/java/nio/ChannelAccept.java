package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 服务端，用于接收连接，并给连接以固定的响应。可用telnet loacalhost 1234 测试
 */
public class ChannelAccept {
    private static final String greeting = "Hello,I'm coming!";

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(1234));
        ByteBuffer buffer = ByteBuffer.wrap(greeting.getBytes());
        ssc.configureBlocking(false);

        while(true){
            System.out.println("waiting for connections!");
            SocketChannel sc = ssc.accept();

            if(sc == null){
                System.out.println("no connection!sleep 2s");
                Thread.sleep(2000);
            }else{
                sc.configureBlocking(false);
                System.out.println("Incoming connection from:"+ sc.socket().getRemoteSocketAddress());
                sc.write(buffer);
                sc.close();
            }
        }
    }

}
