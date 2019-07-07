package com.lo.netty.cs;

import com.alibaba.fastjson.JSONObject;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.AttributeKey;
import lombok.Data;

import java.nio.charset.Charset;

public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        // 首先，netty通过Bootstrap启动客户端
        Bootstrap client = new Bootstrap();

        //第1步 定义线程组，处理读写和链接事件，没有了accept事件
        EventLoopGroup group = new NioEventLoopGroup();
        client.group(group );

        //第2步 绑定客户端通道
        client.channel(NioSocketChannel.class);

        //第3步 给NIoSocketChannel初始化handler， 处理读写事件
        client.handler(new ChannelInitializer<NioSocketChannel>() {  //通道是NioSocketChannel
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                //字符串编码器，一定要加在SimpleClientHandler 的上面
                ch.pipeline().addLast(new StringEncoder());
                ch.pipeline().addLast(new DelimiterBasedFrameDecoder(
                        Integer.MAX_VALUE, Delimiters.lineDelimiter()[0]));
                //找到他的管道 增加他的handler
                ch.pipeline().addLast(new SimpleClientHandler());
            }
        });

        //连接服务器
        ChannelFuture future = client.connect("localhost", 8080).sync();

        //发送数据给服务器
        User user = new User();
        user.setAge(12);
        user.setId(1);
        user.setName("sssss");
        future.channel().writeAndFlush(JSONObject.toJSONString(user)+"\r\n");

        for(int i=0;i<5;i++){
            String msg = "ssss"+i+"\r\n";
            future.channel().writeAndFlush(msg);
        }

        //当通道关闭了，就继续往下走
        future.channel().closeFuture().sync();

        //接收服务端返回的数据
        AttributeKey<String> key = AttributeKey.valueOf("ServerData");
        Object result = future.channel().attr(key).get();
        System.out.println(result.toString());
    }

    @Data
    public static class User{
        private int age;
        private int id;
        private String name;
    }
    public static class SimpleClientHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            if (msg instanceof ByteBuf) {
                String value = ((ByteBuf) msg).toString(Charset.defaultCharset());
                System.out.println("服务器端返回的数据:" + value);
            }

            AttributeKey<String> key = AttributeKey.valueOf("ServerData");
            ctx.channel().attr(key).set("客户端处理完毕");

            //把客户端的通道关闭
            ctx.channel().close();
        }

    }
}
