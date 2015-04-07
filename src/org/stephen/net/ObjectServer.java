package org.stephen.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import org.stephen.Main;
import org.stephen.net.impl.ObjectHandlerServer;

import com.sun.istack.internal.logging.Logger;

/**
 * A object server for basic testing while trying to learn
 * the fundamentals of Netty. This class is the core server
 * class which sets up the server and deals with incoming connections
 * and already connected clients.
 * @author Stephen Andrews
 */
public class ObjectServer {
	
	/**
	 * The port to listen on.
	 */
	private final static int PORT = 1337;
	
	/**
	 * The logger instance.
	 */
	private static Logger logger = Logger.getLogger(ObjectServer.class);
	
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
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			
			/* Link the two groups to deal with a new channel which deals with connections */
			serverBootstrap.group(bossGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(
							new ObjectEncoder(),
							new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
							new ObjectHandlerServer());
				}
				
			}).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);
			
			/* Bind the server to the specified port */
			ChannelFuture f = serverBootstrap.bind(PORT).sync();
			
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
