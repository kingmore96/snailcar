package bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 客户端，请求Server，返回当前时间
 */
public class TimeClient {
    /**
     * 客户端主线程
     * @param args
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = null;
        PrintWriter writer = null;
        Socket client = null;

        try {
            client = new Socket("127.0.0.1",8080);
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            writer = new PrintWriter(client.getOutputStream());

            while(true){
                writer.println("GET CURRENT TIME");
                writer.flush();

                String response = reader.readLine();
                System.out.println("Current time:"+response);
                Thread.sleep(2000);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            writer.close();
            reader.close();
            client.close();
        }
    }
}
