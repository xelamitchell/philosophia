package org.bugz.philosophia.parser;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author bugz
 */
public class Message {
    
    private final String command;
    private final String content;
    
    public Message(String command, String content) {
        this.command = command;
        this.content = content;
    }

    public String getCommand() {
        return command;
    }

    public String getContent() {
        return content;
    }
    
    public static Message valueOf(String message) {
        
        List<String> elements = Arrays.asList(message.split(" "));
        
        String command = elements.get(0);
        String content = (elements.size() > 1) ? message.replace(command, "").trim() : "";
        
        return new Message(command, content);
    }
    
}
