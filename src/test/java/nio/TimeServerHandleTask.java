package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Calendar;

/**
 * 实际处理请求的类
 */
public class TimeServerHandleTask implements Runnable {
    private SocketChannel socketChannel;

    public TimeServerHandleTask(SocketChannel sc) {
        this.socketChannel = sc;
    }

    @Override
    public void run() {
        try {
            ByteBuffer requestBuffer = ByteBuffer.allocate("GET CURRENT TIME".getBytes().length);
            if(socketChannel.read(requestBuffer)<=0){
                return;
            }

            //tcp粘包拆包情况，需要读全
            while(requestBuffer.hasRemaining()){
                socketChannel.read(requestBuffer);
            }

            String req = new String(requestBuffer.array());
            System.out.println("req:"+req);

            ByteBuffer responseBuffer = null;
            if(!("GET CURRENT TIME".equals(req))){
                String rep = "BAD REQUEST!";
                responseBuffer = ByteBuffer.allocate(rep.length());
                responseBuffer.put(rep.getBytes());
                responseBuffer.flip();
                socketChannel.write(responseBuffer);
            }else{
                String s = Calendar.getInstance().getTime().toLocaleString();
                responseBuffer = ByteBuffer.allocate(s.length());
                responseBuffer.put(s.getBytes());
                responseBuffer.flip();
                socketChannel.write(responseBuffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
