package org.bugz.philosophia.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.bugz.philosophia.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author bugz
 */
@Component
public class AuthenticationHandler extends SimpleChannelInboundHandler<String> {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationHandler.class);
    
    private final Session session;
    private final UserService service;
    
    private String username = null;
    private String password = null;

    @Inject
    public AuthenticationHandler(Session session, UserService service) {
        this.session = session;
        this.service = service;
    }
    
    @Override
    public void channelActive(ChannelHandlerContext context) throws Exception {
        context.channel().writeAndFlush("username: ");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext context) throws Exception {
        
        if(StringUtils.isEmpty(password)) {
            context.channel().writeAndFlush("password: ");
        }
        else if(StringUtils.isNoneEmpty(username, password)) {
            
            Boolean logged = service.login(username, password);
            logger.info("{} {}.", username, (logged) ? "successfully logged in" : "invalid username or password");
            
        }
    }
    
    @Override
    protected void messageReceived(ChannelHandlerContext context, String message) throws Exception {
        
        logger.info("{}", message);
        
        Channel channel = context.channel();
        if(StringUtils.isEmpty(username)) {
            username = message;
        }
        else if(StringUtils.isEmpty(password)) {
            password = message;
        }
        
        if(quit(message)) {
            channel.writeAndFlush("Goodbye.\n");
            channel.close();
        }
        
    }
    
    private Boolean quit(String message) {
        message = message.toLowerCase();
        return "q".equals(message) || "quit".equals(message) || "exit".equals(message);
    }
    
}
