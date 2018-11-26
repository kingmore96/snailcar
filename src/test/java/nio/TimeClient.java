package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 使用SocketChannel的客户端
 */
public class TimeClient {
    private static int connectTimeOut = 3000;
    private static ByteBuffer buffer = ByteBuffer.allocate("GET CURRENT TIME".length());

    public static void main(String[] args) {
        SocketChannel socketChannel = null;
        Long start = System.currentTimeMillis();
        try {
            socketChannel = SocketChannel.open(new InetSocketAddress(8080));
            socketChannel.configureBlocking(false);

            while(!socketChannel.finishConnect()){
                if(System.currentTimeMillis() - start > connectTimeOut){
                    throw new RuntimeException("尝试连接超过三秒");
                }
            }

            while(true){
                //发送请求
                buffer.put("GET CURRENT TIME".getBytes());
                buffer.flip();
                socketChannel.write(buffer);
                buffer.clear();

                //接收响应
                if(socketChannel.read(buffer) > 0){
                    buffer.flip();
                    byte[] response = new byte[buffer.remaining()];
                    buffer.get(response);
                    System.out.println("response:"+new String(response));
                    buffer.clear();
                }
                Thread.sleep(2000);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
