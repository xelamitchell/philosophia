package org.bugz.philosophia.parser;

import io.netty.channel.Channel;

/**
 *
 * @author bugz
 */
public interface Command {
    
    public Boolean isCommand(String command);
    
    public void execute(Channel channel, Message message);
    
}
