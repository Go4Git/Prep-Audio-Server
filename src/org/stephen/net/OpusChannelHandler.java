package org.stephen.net;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.logging.Logger;

import org.stephen.swing.ControlPanel;

/**
 * Handles individuals {@link Channel}s on the {@link DefaultChannelInitializer}.
 * @author Stephen Andrews
 */
public class OpusChannelHandler extends ChannelInboundHandlerAdapter {

	/**
	 * The {@link Logger} instance.
	 */
	private static Logger logger = Logger.getLogger(OpusChannelHandler.class.getName());
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		logger.info("The channel " + ctx.channel().remoteAddress() + " has been added to the pipleline!");
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		logger.info("The channel " + ctx.channel().remoteAddress() + " has been removed from the pipeline.");
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		logger.info("The channel " + ctx.channel().remoteAddress() + " is now active.");
		ControlPanel.getInstance().getChannels().addElement("" + ctx.channel().remoteAddress());
		//OpusFile f = new OpusFile(new File("./data/sample.opus"));
		//ctx.writeAndFlush(0);
		//ctx.writeAndFlush(new ChunkedFile(f.getRaf()));
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		logger.info("The channel "  + ctx.channel().remoteAddress() + " is now inactive.");
		ControlPanel.getInstance().getChannels().removeElement("" + ctx.channel().remoteAddress());
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		logger.info("The channel " + ctx.channel().remoteAddress() + " has been registered.");
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		logger.info("The channel " + ctx.channel().remoteAddress() + " has been unregistered.");
	}
}
