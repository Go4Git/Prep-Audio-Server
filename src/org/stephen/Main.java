package org.stephen;

import org.stephen.net.OpusServer;

/**
 * The main class of the audio server. This class starts the server
 * and additionally disposes the main window for controlling the server.
 * @author Stephen Andrews
 */
public class Main {
	
	/**
	 * Starts the {@link OpusServer}.
	 * @param args The arguments, if any.
	 */
	public static void main(String[] args) {
		OpusServer.getInstance().bind();
	}
}
