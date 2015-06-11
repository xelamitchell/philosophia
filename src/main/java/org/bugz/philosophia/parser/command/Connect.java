package org.bugz.philosophia.parser.command;

import io.netty.channel.Channel;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import org.bugz.philosophia.parser.Command;
import org.bugz.philosophia.parser.Message;
import org.bugz.philosophia.user.Credentials;
import org.bugz.philosophia.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*
 * (non-javadoc)
 * 
 * TODO
 * Connect is in the form: connect (-u) <username> (-p) <password>
 */
/**
 * Connects a user to the system.
 * 
 * @author bugz
 */
@Component
public class Connect implements Command {
    
    private static final Logger logger = LoggerFactory.getLogger(Connect.class);
    
    private final Set<String> commands = new HashSet<>(1);
    
    private final UserService service;
    
    @Inject
    public Connect(UserService service) {
        commands.add("connect");
        this.service = service;
    }
    
    public Set<String> getCommands() {
        return Collections.unmodifiableSet(commands);
    }
    
    @Override
    public Boolean isCommand(String command) {
        return commands.contains(command.toLowerCase());
    }
    
    /*
     * (non-javadoc)
     * 
     * TODO
     * Replace channel injection with an event output.
     */
    @Override
    public void execute(Channel channel, Message message) {
        
        Credentials credentials = parse(message.getContent());
        
        if("guest".equals(credentials.getUsername())) {
            
                logger.info("{} is a guest.", credentials.getUsername());
                
                String welcome = new StringBuilder()
                    .append("Welcome Guest").append(System.lineSeparator())
                    .append("> ")
                    .toString();
                
                channel.writeAndFlush(welcome);
            
        }
        else if(service.exists(credentials.getUsername())) {
            
            Boolean success = service.login(credentials);
            if(success) {
                
                logger.info("{} successfully logged in.", credentials.getUsername());
                
                String welcome = new StringBuilder()
                    .append("Welcome back ")
                    .append(credentials.getUsername()).append(System.lineSeparator())
                    .append(credentials.getUsername()).append("> ")
                    .toString();
                
                channel.writeAndFlush(welcome);
                
            }
            
        }
        
        /*
         * TODO
         * If user exists then attempt to login
         * If user does not exist attempt to create a new account
         * If user attempts to connect as a guest then create a guest session
         */
        
    }
    
    private Credentials parse(String message) {
        
        List<String> elements = Arrays.asList(message.split(" "));
        
        String username = elements.get(0);
        String password = (elements.size() > 1) ? elements.get(1) : "";
        
        return new Credentials(username, password);
    }
    
}
