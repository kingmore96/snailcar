package bsj.test;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * 客户端发送请求，netty实现，TCP和UDP两种请求。
 */
public class MockClient_g implements Runnable{

    //用于统计发送数据的次数
    private int count;

    //连接相关信息
    private int port;
    private String host;
    private int heattime = 60;
    private String carIp;

    //UDP
    private NioDatagramChannel nioDatagramChannel;
    private Bootstrap udpBootstrap;

    //TCP
    private Channel channel;
    private Bootstrap tcpBootstrap;

    public MockClient_g(int port, String host, String carIp) {
        this.port = port;
        this.host = host;
        this.carIp = carIp;
    }

    public MockClient_g(int port, String host, int heattime, String carIp) {
        this.port = port;
        this.host = host;
        this.heattime = heattime;
        this.carIp = carIp;
    }

    /**
     * UDP启动类配置
     */
    public void udpStart(){
        udpBootstrap = new Bootstrap();
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        udpBootstrap.group(eventLoopGroup)
                .channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST,true)
                .handler(new MockClientUdpHandler_g());
        doUdpConnect();
    }

    /**
     * UDP连接方法
     */
    private void doUdpConnect() {
        if(nioDatagramChannel != null && nioDatagramChannel.isActive()){
            return;
        }
        try {
            ChannelFuture future = udpBootstrap.bind(0).sync();
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if(future.isSuccess()){
                        nioDatagramChannel = (NioDatagramChannel) future.channel();
                        System.out.println("UDP create nioDatagramChannel successful!");
                        doUdpSendData();
                    }
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * UDP发送数据，每隔60s发送一次数据
     */
    private void doUdpSendData() throws InterruptedException {
        while(true){
            if(nioDatagramChannel != null && nioDatagramChannel.isActive()){
                byte[] msg = MessageBuilder_g.createMsg(carIp, 80);
                System.out.println("UDP 发送数据"+carIp+count++);
                ByteBuf byteBuf = Unpooled.copiedBuffer(msg);
                DatagramPacket datagramPacket = new DatagramPacket(byteBuf,new InetSocketAddress(host,port));
                nioDatagramChannel.writeAndFlush(datagramPacket);
            }
            TimeUnit.SECONDS.sleep(heattime);
        }
    }

    /**
     * TCP启动类配置，连接
     */
    public void tcpStart(){
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        tcpBootstrap = new Bootstrap();
        tcpBootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,3000)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .option(ChannelOption.TCP_NODELAY,true)
                .remoteAddress(host,port)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ByteArrayEncoder());
                        ch.pipeline().addLast(new ByteArrayDecoder());
                        ch.pipeline().addLast(new MockClientTcpHandler_g());
                    }
                });
        doTcpConnect();
    }

    /**
     * Tcp连接，若不成功，每隔60秒重连一次
     */
    private void doTcpConnect() {
        if(channel != null && channel.isActive()){
            return;
        }

        try {
            ChannelFuture future = tcpBootstrap.connect().sync();
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if(future.isSuccess()){
                        channel = future.channel();
                        System.out.println("TCP 连接成功！");
                        doTcpSendData();
                    }else{
                        System.out.println("TCP 连接失败！");
                        future.channel().eventLoop().schedule(new Runnable() {
                            @Override
                            public void run() {
                                doTcpConnect();
                            }
                        },heattime,TimeUnit.SECONDS);
                    }
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tcp发送数据
     */
    private void doTcpSendData() throws InterruptedException {
        while(true){
            if(channel != null &&channel.isActive()){
                byte[] msg = MessageBuilder_g.createMsg(carIp, 80);
                System.out.println("TCP 发送数据"+carIp+count++);
                ByteBuf byteBuf = Unpooled.copiedBuffer(msg);
                channel.writeAndFlush(byteBuf);
            }
            TimeUnit.SECONDS.sleep(heattime);
        }
    }

    @Override
    public void run() {
        //tcpStart();
        //udpStart();
    }
}
