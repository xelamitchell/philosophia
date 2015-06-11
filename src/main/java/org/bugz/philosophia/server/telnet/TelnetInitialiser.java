package org.bugz.philosophia.server.telnet;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslContext;
import javax.inject.Inject;
import org.bugz.philosophia.server.AuthenticationHandler;
import org.springframework.stereotype.Component;

/**
 * Initialises Telnet handling.
 * 
 * @author bugz
 */
@Component
public class TelnetInitialiser extends ChannelInitializer<SocketChannel> {

    private static final StringDecoder DECODER = new StringDecoder();
    private static final StringEncoder ENCODER = new StringEncoder();
    
    private final AuthenticationHandler authentication;
    private final TelnetHandler telnet;
    
    private SslContext ssl = null;
    
    @Inject
    public TelnetInitialiser(AuthenticationHandler authentication, TelnetHandler handler) {
        this.authentication = authentication;
        this.telnet = handler;
    }

    public void setSsl(SslContext ssl) {
        this.ssl = ssl;
    }
    
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        
        ChannelPipeline pipeline = channel.pipeline();
        if(ssl != null) {
            pipeline.addLast(ssl.newHandler(channel.alloc()));
        }
        
        pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast(DECODER);
        pipeline.addLast(ENCODER);
        
        // Add handlers
//        pipeline.addLast(authentication);
        pipeline.addLast(telnet);
        
    }
    
}
