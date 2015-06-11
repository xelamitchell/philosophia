package org.bugz.philosophia.server.telnet;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import javax.inject.Inject;
import org.bugz.philosophia.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*
 * (non-javadoc)
 * 
 * TODO
 * Add a few commands to the startup:
 * 
 * Connect:
 * connect (-u) <username> (-p) <password>
 *   If user exists then attempt a login
 *   If user does not exist then add CreationHandler
 * connect guest
 *   Connect the user as a guest
 * 
 * Guests have a randomised generic description, are Tetrahedral and cannot
 * Iterate.
 */
/**
 * Handles Telnet messaging.
 * 
 * @author bugz
 */
@Sharable
@Component
public class TelnetHandler extends SimpleChannelInboundHandler<String> {

    private static final Logger logger = LoggerFactory.getLogger(TelnetHandler.class);
    
    private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private final Parser parser;
    
    @Inject
    public TelnetHandler(Parser parser) {
        this.parser = parser;
    }
    
    
    @Override
    public void channelActive(ChannelHandlerContext context) throws Exception {
        
        String splash = new StringBuilder()
            .append(System.lineSeparator())
            .append("Welcome to Philosophia").append(System.lineSeparator()).append(System.lineSeparator())
            .append("What do you wish to do?").append(System.lineSeparator())
            .append("connect, exit or help").append(System.lineSeparator())
            .append("> ")
            .toString();
        
        context.channel().writeAndFlush(splash);
        
//        logger.info("Channel active");
//        
//        Channel incoming = context.channel();
//        for(Channel channel : channels) {
//            
//            if(channel != incoming) {
//                channel.writeAndFlush(incoming.remoteAddress() + " has joined.\n");
//            }
//        }
//        
//        incoming.writeAndFlush("Welcome to Philosophia at " + InetAddress.getLocalHost().getHostAddress() + ".\n");
//        channels.add(incoming);
        
    }

    @Override
    public void channelInactive(ChannelHandlerContext context) throws Exception {
        
        Channel incoming = context.channel();
        
        for(Channel channel : channels) {
            if(channel != incoming) {
                channel.writeAndFlush(incoming.remoteAddress() + " has left.\n");
            }
        }
        
        channels.remove(incoming);
        
    }
    
    @Override
    protected void messageReceived(ChannelHandlerContext context, String message) throws Exception {
        
        parser.parse(context.channel(), message);
        
//        Channel incoming = context.channel();
//        for(Channel channel : channels) {
//            
//            if(channel != incoming) {
//                channel.writeAndFlush("[" + channel.remoteAddress() + "]: " + message + "\n");
//            }
//        }
        
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) throws Exception {
        logger.warn("{}", cause);
        context.channel().close();
    }
    
    private Boolean quit(String message) {
        message = message.toLowerCase();
        return "q".equals(message) || "quit".equals(message) || "exit".equals(message);
    }

}
