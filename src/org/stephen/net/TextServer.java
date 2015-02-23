package org.stephen.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.stephen.net.impl.TextServerHandler;

import com.sun.istack.internal.logging.Logger;

/**
 * A text server for basic testing while trying to learn
 * the fundamentals of Netty. This class is the core server
 * class which sets up the server and deals with incoming connections
 * and already connected clients.
 * @author Stephen Andrews
 */
public class TextServer {
	
	/**
	 * The port to listen on.
	 */
	private final static int PORT = 1337;
	
	/**
	 * The logger instance.
	 */
	private static Logger logger = Logger.getLogger(TextServer.class);
	
	/**
	 * Starts the text server.
	 * @throws Exception 
	 */
	public void start() throws Exception {
		
		logger.info("Attempting to open the server on port: " + PORT + "...");
		
		/* Handles the incoming connections */
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		
		/* Handles the connections after they have been accepted */
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		/* Attempt to open the server */
		try {
			
			/* Helper class provided by Netty to setup the server */
			ServerBootstrap b = new ServerBootstrap();
			
			/* Link the two groups to deal with a new channel which deals with connections */
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
			.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new TextServerHandler());
				}
				
			}).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);
			
			/* Bind the server to the specified port */
			ChannelFuture f = b.bind(PORT).sync();
			
			logger.info("Listening on port: " + PORT + "!");
			
			/* Might not need this line since the channel never closes */
			f.channel().closeFuture().sync();
		} finally {
			
			/* Terminate all connected clients */
			workerGroup.shutdownGracefully();
			
			/* Terminate any incoming connections */
			bossGroup.shutdownGracefully();
		} 
	}
}
