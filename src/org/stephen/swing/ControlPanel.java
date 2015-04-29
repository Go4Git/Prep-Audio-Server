package org.stephen.swing;

import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

/**
 * A UI for controlling the audio server.
 * @author Stephen Andrews
 */
public class ControlPanel extends JFrame {

	/**
	 * Local instance.
	 */
	private static ControlPanel instance = new ControlPanel();
	
	/**
	 * Gets the instance.
	 * @return The instance.
	 */
	public static ControlPanel getInstance() {
		if (instance != null) {
			return instance; 
		}
		
		instance = new ControlPanel();
		return instance;
	}
	
	/**
	 * The content pane which houses all of the frame's components.
	 */
	private JPanel contentPane;
	private JTextField opusFilePath;
	private DefaultListModel channels = new DefaultListModel();
	private JList connectedChannelsList;

	/**
	 * Creates the frame, initializes its components, and configures
	 * the layout.
	 */
	public ControlPanel() {
		init();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 129, 246);
		contentPane.add(scrollPane);
		
		connectedChannelsList = new JList();
		scrollPane.setViewportView(connectedChannelsList);
		connectedChannelsList.setModel(channels);
		
		opusFilePath = new JTextField();
		opusFilePath.setBounds(149, 11, 265, 20);
		contentPane.add(opusFilePath);
		opusFilePath.setColumns(10);
		
		JButton browseButton = new JButton("Browse");
		browseButton.setBounds(424, 9, 89, 23);
		contentPane.add(browseButton);
		
		JButton btnNewButton = new JButton("Transmit");
		btnNewButton.setBounds(149, 42, 364, 23);
		contentPane.add(btnNewButton);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(149, 76, 364, 20);
		contentPane.add(progressBar);
	}
	
	/**
	 * Set's up the frame's environment.
	 */
	private void init() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(new Dimension(539, 307));
			setLocationRelativeTo(null);
			
			setTitle("Audio Server");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
	
	public JList getConnectedChannelsList() {
		return connectedChannelsList;
	}
	
	public DefaultListModel getChannels() {
		return channels;
	}
}
