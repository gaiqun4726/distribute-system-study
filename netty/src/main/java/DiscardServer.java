import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class DiscardServer {
    private int port;

    public DiscardServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        new DiscardServer(8080).run();
        System.out.println("Start server.");
    }

    public void run() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        System.out.println("prepare to run.");
        try {
            ServerBootstrap sb = new ServerBootstrap();
            sb = sb.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new DiscardServerHandler())
                    .option(ChannelOption.SO_BACKLOG, 128);
            ChannelFuture future = sb.bind(this.port).sync();
            ChannelFuture future2 = sb.bind(8081).sync();
            future.channel().closeFuture().sync();
            future2.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
