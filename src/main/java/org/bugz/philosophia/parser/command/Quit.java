package org.bugz.philosophia.parser.command;

import io.netty.channel.Channel;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.bugz.philosophia.parser.Command;
import org.bugz.philosophia.parser.Message;
import org.springframework.stereotype.Component;

/**
 * Exits the game.
 * 
 * @author bugz
 */
@Component
public class Quit implements Command {
    
    private final Set<String> commands = new HashSet<>(3);
    
    public Quit() {
        commands.addAll(Arrays.asList("quit", "q", "exit"));
    }
    
    public Set<String> getCommands() {
        return Collections.unmodifiableSet(commands);
    }
    
    @Override
    public Boolean isCommand(String command) {
        return commands.contains(command.toLowerCase());
    }
    
    @Override
    public void execute(Channel channel, Message message) {
        
        /*
         * TODO
         * Sends a quit event
         */
        
        channel.writeAndFlush("Goodbye.\n");
        channel.close();
        
    }
    
}
