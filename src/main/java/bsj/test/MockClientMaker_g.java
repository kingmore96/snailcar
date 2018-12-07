package bsj.test;

import bsj.code.ConverTools_g;

/**
 * 创建n个线程执行MockClient的run方法
 */
public class MockClientMaker_g {
    private int count;
    private int port;
    private String host;
    private int heattime;

    public MockClientMaker_g(int count, int port, String host, int heattime) {
        this.count = count;
        this.port = port;
        this.host = host;
        this.heattime = heattime;
    }

    public void run(){
        for (int i = 0; i < count; i++) {
            Long simno = 13311110000L + i;
            String s = ConverTools_g.num2HexIp(simno);
            new Thread(new MockClient_g(port,host,s));
        }
    }
    public static void main(String[] args) {
        MockClientMaker_g maker = new MockClientMaker_g(1,8014,"127.0.0.1",30);
        maker.run();
    }
}
