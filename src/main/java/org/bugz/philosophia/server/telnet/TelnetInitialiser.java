package org.bugz.philosophia.server.telnet;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslContext;

/**
 *
 * @author bugz
 */
public class TelnetInitialiser extends ChannelInitializer<SocketChannel> {

    private static final StringDecoder DECODER = new StringDecoder();
    private static final StringEncoder ENCODER = new StringEncoder();
    
    private static final TelnetHandler HANDLER = new TelnetHandler();
    
    private final SslContext ssl;
    
    public TelnetInitialiser(SslContext ssl) {
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
        pipeline.addLast(HANDLER);
        
    }
    
}
