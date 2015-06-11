package org.bugz.philosophia.parser;

import static java.lang.Compiler.command;

import io.netty.channel.Channel;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.bugz.philosophia.parser.command.Connect;
import org.bugz.philosophia.parser.command.Quit;
import org.springframework.stereotype.Component;

/**
 * Command parser.
 * 
 * @author bugz
 */
@Component
public class Parser {
    
    private final Set<Command> commands = new HashSet<>();
    
    private final Connect connect;
    private final Quit quit;
    
    @Inject
    public Parser(Connect connect, Quit quit) {
        this.connect = connect;
        this.quit = quit;
    }
    
    @PostConstruct
    public void initialise() {
        commands.add(connect);
        commands.add(quit);
    }
    
    public void parse(Channel channel, String incoming) {
        
        Message message = Message.valueOf(incoming);
        
        Boolean executed = false;
        for(Command command : commands.toArray(new Command[0])) {
            if(command.isCommand(message.getCommand())) {
                command.execute(channel, message);
                executed = true;
                break;
            }
        }
        
        if(!executed) {
            
            String unknown = new StringBuilder()
                .append("I don't understand")
                .append(System.lineSeparator())
                .append("> ")
                .toString();
            
            channel.writeAndFlush(unknown);
        }
        
    }
    
}
