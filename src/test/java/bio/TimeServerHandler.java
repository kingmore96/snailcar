package bio;

import java.io.*;
import java.net.Socket;
import java.util.Calendar;

/**
 * 处理客户端任务
 */
public class TimeServerHandler implements Runnable {
    private Socket client;

    public TimeServerHandler(Socket client) {
        this.client = client;
    }

    public void run() {
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            writer = new PrintWriter(client.getOutputStream());
            while(true){
                String request = reader.readLine();
                if(!("GET CURRENT TIME").equals(request)){
                    writer.println("BAD REQUEST!");
                }else{
                    writer.println(Calendar.getInstance().getTime().toLocaleString());
                }
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            writer.close();
            try {
                reader.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
