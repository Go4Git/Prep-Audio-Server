package org.stephen.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is the core class of the program which
 * creates a server to handle all incoming client connections.
 * @author Stephen Andrews
 */
public class Network {

	/**
	 * The {@link Logger} instance.
	 */
	private static Logger logger = Logger.getLogger(Network.class.getName());
	
	/**
	 * The {@link Network} instance.
	 */
	public static Network instance;
	
	/**
	 * The {@link ServerBootstrap} instance.
	 */
	private ServerBootstrap bootstrap;
	
	/**
	 * The maximum size of pending incoming connections the network will handle.
	 * Any additional connection will be refused until pending connections are
	 * removed from the queue.
	 */
	private final int QUEUE_SIZE = 100;
	
	/**
	 * The port to bind the {@link Network} to.
	 */
	private final int PORT = 1337;
	
	/**
	 * Gets the {@link Network} instance.
	 * @return The {@link Network} instance.
	 */
	public static Network getInstance() {
		if (instance == null) {
			try {
				instance = new Network();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		return instance;
	}
	
	/**
	 * Binds the {@link NetworkPipeline} to allow incoming connections.
	 */
	public void bind() {
		logger.info("Attempting to open the network on port: " + PORT + "...");
		
		/* Helper class to setup a network */
		bootstrap = new ServerBootstrap();
		
		/* Handles the incoming connections */
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		
		/* Handles the connections after they have been accepted */
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		/* Configure network */
		bootstrap.group(bossGroup, workerGroup);
		bootstrap.channel(NioServerSocketChannel.class);
		bootstrap.option(ChannelOption.SO_BACKLOG, QUEUE_SIZE);
		bootstrap.childOption(ChannelOption.TCP_NODELAY, true);
		bootstrap.childHandler(new NetworkPipeline());
		try {
			bootstrap.localAddress(PORT).bind().sync();
		} catch (InterruptedException ex) {
			logger.log(Level.SEVERE, "Error binding the network.");
		} finally {
			logger.info("Network successfully bound to port: " + PORT + "!");
		}
	}
	
}
