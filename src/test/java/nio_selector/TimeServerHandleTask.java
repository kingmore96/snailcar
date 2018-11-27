package nio_selector;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Calendar;

/**
 * 带SelectionKey的
 */
public class TimeServerHandleTask implements Runnable{
    private SelectionKey selectionKey;

    public TimeServerHandleTask(SelectionKey selectionKey) {
        this.selectionKey = selectionKey;
    }

    @Override
    public void run() {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer buffer = ByteBuffer.allocateDirect("GET CURRENT TIME".getBytes().length);
        ByteBuffer buffer1 = ByteBuffer.allocateDirect(1024);
        try {
            int count = 0;
            while((count = socketChannel.read(buffer)) > 0){
                buffer.flip();
                byte[] req = new byte[buffer.remaining()];
                buffer.get(req);
                buffer.clear();
                String request = new String(req);
                if(!"GET CURRENT TIME".equals(request)){
                    String rep = "BAD REQUEST!";
                    buffer1.put(rep.getBytes());
                    buffer1.flip();
                    socketChannel.write(buffer1);
                }else{
                    String rep = Calendar.getInstance().getTime().toLocaleString();
                    System.out.println(rep);
                    buffer1.put(rep.getBytes());
                    buffer1.flip();
                    socketChannel.write(buffer1);
                }
                buffer1.clear();
            }
        }catch (Exception e){
            e.printStackTrace();
            try {
                socketChannel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
