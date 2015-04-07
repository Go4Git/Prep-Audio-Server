package org.stephen.net;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * Manages all connected {@link Channel}s.
 * @author Stephen Andrews
 */
public class NetworkPipeline extends ChannelInitializer<SocketChannel> {

	/**
	 * The channel timeout in seconds.
	 */
	private final int TIMEOUT = 30;
	
	/**
	 * The {@link ChannelPipeline} instance.
	 */
	public ChannelPipeline pipeline;
	
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		pipeline = ch.pipeline();
		pipeline.addFirst("timeout", new ReadTimeoutHandler(TIMEOUT));
		pipeline.addLast("channel-handler", new NetworkHandler());
	}

}
