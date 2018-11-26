package bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端：每接收一个连接，就为这个连接创建一个新的线程
 */
public class TimeServer {

    /**
     * 接收连接线程
     * @param args
     */
    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(8080);
            System.out.println("Server start at 8080");
            while(true){
                Socket client = server.accept();
                new Thread(new TimeServerHandler(client)).start();
            }
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
