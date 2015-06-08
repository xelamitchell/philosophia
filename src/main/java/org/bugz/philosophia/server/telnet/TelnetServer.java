package org.bugz.philosophia.server.telnet;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Telnet server bean.
 * 
 * @author bugz
 */
@Component
public class TelnetServer {
    
    private static final Logger logger = LoggerFactory.getLogger(TelnetServer.class);
    
    @Value("${quill.port:0}")
    private Integer port;
    
    @PostConstruct
    public void initialise() {
        this.run();
    }
    
    public void run() {
        
        EventLoopGroup producer = new NioEventLoopGroup(1);
        EventLoopGroup consumer = new NioEventLoopGroup();
        
        try {
            
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(producer, consumer)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new TelnetInitialiser());
        
            bootstrap.bind(port).sync().channel().closeFuture().sync();
        } catch (InterruptedException ie) {
            logger.warn("Interrupted.", ie);
        } finally {
            producer.shutdownGracefully();
            consumer.shutdownGracefully();
        }
        
    }
    
}
