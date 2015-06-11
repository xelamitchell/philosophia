package org.bugz.philosophia.server.telnet;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import java.net.InetSocketAddress;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Netty TCP Spring configuration.
 * 
 * @author bugz
 */
@Configuration
public class TelnetConfig {
    
    @Inject
    private TelnetInitialiser initialiser;
    
    @Value("${tcp.port:0}")
    private Integer port;
    
    @Bean(name = "serverBootstrap")
    public ServerBootstrap bootstrap() {
        
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(producer(), consumer())
            .channel(NioServerSocketChannel.class)
            .handler(new LoggingHandler(LogLevel.INFO))
            .childHandler(initialiser);
        
        return bootstrap;
    }
    
    @Bean(destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup producer() {
        return new NioEventLoopGroup(1);
    }
    
    @Bean(destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup consumer() {
        return new NioEventLoopGroup();
    }
    
    @Bean
    public InetSocketAddress port() {
        return new InetSocketAddress(port);
    }
    
}
