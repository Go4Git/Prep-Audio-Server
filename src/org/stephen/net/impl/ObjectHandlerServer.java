package org.stephen.net.impl;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.logging.Logger;

import org.stephen.model.Person;

/**
 * Initialized when a client connects to the server.
 * Sends a dummy object to the client and waits for it to be retured.
 * @author Stephen Andrews
 */
public class ObjectHandlerServer extends ChannelInboundHandlerAdapter {

	/**
	 * Logger instance.
	 */
	public static final Logger logger = Logger.getLogger(ObjectHandlerServer.class.getName());
	
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
    	logger.info("Reading data sent from the client...");
    	
    	/* Get our modified dummy object back */
    	Person dummy = (Person) msg;
    	
    	/* Make sure the object returned is valid */
    	if (dummy != null) {
    		System.out.println(dummy.toString());
    	}
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }
	
	@Override
    public void channelActive(final ChannelHandlerContext ctx) {
		logger.info("Sending dummy object to client...");
		
		/* Create the dummy object */
		Person dummy = new Person("John", 38, 50000.00);
		
		ctx.writeAndFlush(dummy);
    }
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable ex) {
		ex.printStackTrace();
		ctx.close();
	}
	
}
