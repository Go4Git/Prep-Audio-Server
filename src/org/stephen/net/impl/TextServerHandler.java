package org.stephen.net.impl;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * An implementation of a server handler which deals with
 * the sending of text between the server and its clients.
 * @author Stephen Andrews
 */
public class TextServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		
		/* Incoming data is in the form of a {@link ByteBuf} object */
		ByteBuf in = (ByteBuf) msg;
		
		/* Try reading incoming data */
		try {
			while (in.isReadable()) {
				System.out.println((char) in.readByte());
				System.out.flush();
			}
		} finally {
			in.release();
		}
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable ex) {
		ex.printStackTrace();
		ctx.close();
	}
}
