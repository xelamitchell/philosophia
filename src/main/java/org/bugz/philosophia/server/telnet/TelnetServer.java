package org.bugz.philosophia.server.telnet;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.util.internal.logging.InternalLoggerFactory;
import io.netty.util.internal.logging.Slf4JLoggerFactory;
import java.net.InetSocketAddress;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Telnet server bean.
 * 
 * @author bugz
 */
@Component
public class TelnetServer {
    
    private static final Logger logger = LoggerFactory.getLogger(TelnetServer.class);
    
    private final ServerBootstrap bootstrap;
    private final InetSocketAddress address;
    
    private Channel server;
    
    @Inject
    public TelnetServer(ServerBootstrap bootstrap, InetSocketAddress address) {
        this.bootstrap = bootstrap;
        this.address = address;
    }
    
    @PostConstruct
    public void start() throws InterruptedException {
        
        InternalLoggerFactory.setDefaultFactory(new Slf4JLoggerFactory());
        
        logger.info("Starting telnet...");
        server = bootstrap.bind(address).sync().channel().closeFuture().sync().channel();
    }
    
    @PreDestroy
    public void stop() {
        logger.info("Stopping telnet...");
        server.close();
    }
    
}
