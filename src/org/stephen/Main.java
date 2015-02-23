package org.stephen;

import java.awt.EventQueue;

import org.stephen.net.TextServer;
import org.stephen.swing.ControlPanel;

/**
 * The main class of the audio server. This class launches the server
 * and additionally disposes the main window for controlling the server.
 * @author Stephen Andrews
 */
public class Main {

	/**
	 * Launches the application.
	 * @param args The arguments, if any.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				ControlPanel cp = new ControlPanel();
				cp.setVisible(true);
			}
			
		});
		
		try {
			new TextServer().start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
