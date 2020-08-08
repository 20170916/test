package com.lo.netty.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;

public class Server {

    public static void main(String[] args) throws InterruptedException {
        Bootstrap b = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        b.group(group)
                .channel(NioDatagramChannel.class)
                .handler(new MyServerHandler());

        // 服务端监听在9999端口
        b.bind(9999).sync().channel().closeFuture().await();
    }

    public static class MyServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet)
                throws Exception
        {
            // 读取收到的数据
            ByteBuf buf = (ByteBuf) packet.copy().content();
            byte[] req = new byte[buf.readableBytes()];
            buf.readBytes(req);
            String body = new String(req, CharsetUtil.UTF_8);
            System.out.println("【9999】>>>>>> 收到客户端的数据："+body);

            final String hostAddress = packet.sender().getAddress().getHostAddress();
            final int port = packet.sender().getPort();
            System.out.println(hostAddress +"   "+port);


            // 回复一条信息给客户端
            final ByteBuf byteBufResponse = Unpooled.copiedBuffer(
                    "9999 Hello，我是Server，我的时间戳是" + System.currentTimeMillis()
                    , CharsetUtil.UTF_8);
            final DatagramPacket datagramPacketResponse = new DatagramPacket(
                    byteBufResponse
                    , packet.sender());
            ctx.writeAndFlush(datagramPacketResponse)
                    .sync();
        }
    }
}
