package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.*;

/**
 * 使用ServerSocket接收连接，使用线程池执行连接请求
 */
public class TimeServer {
    private BlockingQueue<SocketChannel> idleQueue = new LinkedBlockingQueue<SocketChannel>();
    private BlockingQueue<Future<SocketChannel>> workingQueue = new LinkedBlockingQueue<Future<SocketChannel>>();
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    /**
     * 构造代码块，开启一个线程循环执行，查看两个队列
     */
    {
        new Thread(){
            @Override
            public void run(){
                try {
                    while (true) {
                        for (int i = 0; i < idleQueue.size(); i++) {
                            SocketChannel sc = idleQueue.poll();
                            if (sc != null) {
                                Future<SocketChannel> result = executorService.submit(new TimeServerHandleTask(sc), sc);
                                workingQueue.put(result);
                            }
                        }

                        for (int i = 0; i < workingQueue.size(); i++) {
                            Future<SocketChannel> future = workingQueue.poll();
                            if(!future.isDone()){
                                workingQueue.put(future);
                                continue;
                            }
                            SocketChannel channel = null;
                            try {
                                channel = future.get();
                                idleQueue.put(channel);
                            } catch (ExecutionException e) {
                                try {
                                    channel.close();
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                                e.printStackTrace();
                            }
                        }

                    }
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 循环监听连接，发现连接加入到阻塞队列中。
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        TimeServer timeServer = new TimeServer();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.socket().bind(new InetSocketAddress(8080));

        while(true){
            SocketChannel sc = ssc.accept();
            if(sc == null){
                continue;
            }else{
                sc.configureBlocking(false);
                timeServer.idleQueue.put(sc);
            }
        }
    }
}
