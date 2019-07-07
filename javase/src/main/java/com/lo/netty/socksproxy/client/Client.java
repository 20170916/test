package com.lo.netty.socksproxy.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class Client {


    /*public static void start(){
        ClientChannelHandler channelHandler = new ClientChannelHandler();
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        try{
            Bootstrap b = new Bootstrap();
            b.group(bossGroup).channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress("127.0.0.1",8000))
                    .handler(new ChannelInitializer<Channel>() {
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addLast(channelHandler);

                        };
                    });
            ChannelFuture f = b.connect().sync();
            f.channel().closeFuture().sync();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
        }
    }*/
}
