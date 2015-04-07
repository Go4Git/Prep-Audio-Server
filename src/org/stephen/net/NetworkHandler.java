package org.stephen.net;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;

import java.util.logging.Logger;

import org.stephen.audio.OpusFile;
import org.stephen.audio.OpusPlayer;

/**
 * Handles individuals {@link Channel}s on the {@link NetworkPipeline}.
 * @author Stephen Andrews
 */
public class NetworkHandler implements ChannelInboundHandler {

	/**
	 * The {@link Logger} instance.
	 */
	private static Logger logger = Logger.getLogger(NetworkHandler.class.getName());
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		logger.info(ctx.name() + " has been added to the pipleline!");
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		logger.info(ctx.name() + " has been removed from the pipeline...");
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		logger.info("The channel is now active from " + ctx.channel().remoteAddress());
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		logger.info("The channel is now inactive from " + ctx.channel().remoteAddress());
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		if (msg instanceof OpusFile) {
			OpusFile opusFile = (OpusFile) msg;
			new OpusPlayer(opusFile.getOggFile()).play();
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx)
			throws Exception {
		ctx.flush();
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		logger.info("The channel is now registered from " + ctx.channel().remoteAddress());
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx)
			throws Exception {
		logger.info("The channel is now unregistered from " + ctx.channel().remoteAddress());
	}

	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable arg1)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object arg1)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
