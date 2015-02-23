package org.stephen.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * A UI for controlling the audio server.
 * @author Stephen Andrews
 */
public class ControlPanel extends JFrame {

	/**
	 * The content pane which houses all of the frame's components.
	 */
	private JPanel contentPane;

	/**
	 * Creates the frame, initializes its components, and configures
	 * the layout.
	 */
	public ControlPanel() {
		init();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	/**
	 * Set's up the frame's environment.
	 */
	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(1024, 720));
		setLocationRelativeTo(null);
		
		setTitle("Audio Server");
	}

}
