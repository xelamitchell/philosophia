package org.bugz.philosophia.server;

import static io.netty.util.concurrent.GlobalEventExecutor.INSTANCE;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import org.springframework.stereotype.Component;

/**
 *
 * @author bugz
 */
@Component
public class Session {
    
    private static final ChannelGroup CHANNELS = new DefaultChannelGroup(INSTANCE);
    
    public Boolean exists(Channel channel) {
        return CHANNELS.contains(channel);
    }
    
    public Boolean add(Channel channel) {
        return CHANNELS.add(channel);
    }
    
    public Boolean remove(Channel channel) {
        return CHANNELS.remove(channel);
    }
    
}
