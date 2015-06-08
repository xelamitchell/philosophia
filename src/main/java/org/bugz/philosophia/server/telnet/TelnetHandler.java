package org.bugz.philosophia.server.telnet;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import java.net.InetAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bugz
 */
@Sharable
public class TelnetHandler extends SimpleChannelInboundHandler<String> {

    private static final Logger logger = LoggerFactory.getLogger(TelnetHandler.class);
    
    private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelActive(ChannelHandlerContext context) throws Exception {
        
        Channel incoming = context.channel();
        for(Channel channel : channels) {
            
            if(channel != incoming) {
                channel.writeAndFlush(incoming.remoteAddress() + " has joined.\n");
            }
        }
        
        incoming.writeAndFlush("Welcome to Philosophia at " + InetAddress.getLocalHost().getHostAddress() + ".\n");
        channels.add(incoming);
        
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
        
        
        
        Channel incoming = context.channel();
        for(Channel channel : channels) {
            
            if(channel != incoming) {
                channel.writeAndFlush("[" + channel.remoteAddress() + "]: " + message + "\n");
            }
        }
        
        if(quit(message)) {
            incoming.writeAndFlush("Goodbye.\n");
            incoming.close();
        }
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
