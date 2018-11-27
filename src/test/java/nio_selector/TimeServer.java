package nio_selector;

import com.sun.security.ntlm.Server;
import nio.TimeServerHandleTask;

import java.awt.print.Pageable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 包含选择器的服务端
 */
public class TimeServer {
    private static ExecutorService executorService;
    static{
        executorService = new ThreadPoolExecutor(5,10,10,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(1000));
    }

    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.socket().bind(new InetSocketAddress(8080));
        Selector selector = Selector.open();
        ssc.register(selector,ssc.validOps());
        
        while(true){
            int readyCount = selector.select(1000);
            if(readyCount == 0){
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while(iterator.hasNext()){
                SelectionKey key = iterator.next();
                //针对ServerSocketChannel
                if(key.isAcceptable()){
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
                    SocketChannel sc = serverSocketChannel.accept();
                    sc.configureBlocking(false);
                    sc.register(selector,SelectionKey.OP_READ|SelectionKey.OP_WRITE);
                }
                //针对客户端的请求
                if(key.isReadable()){
                    executorService.submit(new nio_selector.TimeServerHandleTask(key));
                }
                iterator.remove();
            }
        }
    }
}
