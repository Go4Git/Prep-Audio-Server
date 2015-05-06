package org.stephen.net;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * Sets up the {@link ChannelPipeline} for all incoming {@link Channel}s.
 * @author Stephen Andrews
 */
public class DefaultChannelInitializer extends ChannelInitializer<SocketChannel> {

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
		pipeline.addLast("encoder", new ObjectEncoder());
		pipeline.addLast("decoder", new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
		pipeline.addLast("chunkedWriter", new ChunkedWriteHandler());
		pipeline.addLast("channel-handler", new OpusChannelHandler());
		pipeline.addLast("timeout", new ReadTimeoutHandler(TIMEOUT));
	}

}
