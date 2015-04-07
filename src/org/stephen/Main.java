package org.stephen;

import org.stephen.net.Network;
import org.stephen.swing.ControlPanel;

/**
 * The main class of the audio server. This class starts the server
 * and additionally disposes the main window for controlling the server.
 * @author Stephen Andrews
 */
public class Main {

	public static ControlPanel cp = new ControlPanel();
	
	/**
	 * Starts the {@link Network}.
	 * @param args The arguments, if any.
	 */
	public static void main(String[] args) {
		Network.getInstance().bind();
	}
}
