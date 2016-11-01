package systems.crigges.gui.desktop;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.border.LineBorder;

import com.badlogic.gdx.Graphics.Monitor;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import systems.crigges.gui.Ticker;

import java.awt.Color;
import javax.swing.DefaultComboBoxModel;

public class SortingTable {

	private JFrame frame;
	private JTable table;
	private BowTableModel tableModel;
	private JTextField nameField;
	private JSpinner ringSpinner;
	private ArrayList<String> classes;
	private JComboBox<Object> classSelector;
	private JComboBox<String> deviceBox;
	private Monitor[] monitors;

	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		String[] iconOpts = {"OptionPane.errorIcon", 
				  "OptionPane.informationIcon", 
				  "OptionPane.warningIcon", 
				  "OptionPane.questionIcon"};
				for (String key : iconOpts) {
				  ImageIcon icon = (ImageIcon) UIManager.get(key);
				  Image img = icon.getImage();
				  ImageIcon newIcon = new ImageIcon(img.getScaledInstance(img.getWidth(null), img.getHeight(null), 0));
				  UIManager.put(key, newIcon);
				}
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SortingTable window = new SortingTable();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SortingTable() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1357, 722);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(130, 135, 144)));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 927, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		JButton btnEintragHinzufgen = new JButton("Hinzuf\u00FCgen");
		btnEintragHinzufgen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tableModel.addEntry(nameField.getText(), classSelector.getSelectedItem(), (Integer) ringSpinner.getValue());
			}
		});
		
		JButton btnLschen = new JButton("L\u00F6schen");
		
		JLabel lblName = new JLabel("Name");
		
		nameField = new JTextField();
		nameField.setColumns(10);
		
		JLabel lblKlasse = new JLabel("Klasse");
		
		JLabel lblNewLabel = new JLabel("Ringe");
		
		ringSpinner = new JSpinner();
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClassModifierWindow w = new ClassModifierWindow(frame, classes);
				classes = w.openDialog();
				classSelector.setModel(new DefaultComboBoxModel<Object>(classes.toArray()));
			}
		});
		
		classSelector = new JComboBox<Object>();
		
		deviceBox = new JComboBox<String>();
		
		
		
		JLabel lblTargetDevice = new JLabel("Target Device");
		
		JButton btnNewButton_1 = new JButton("Start");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Thread starter = new Thread(new Runnable() {
					
					@Override
					public void run() {
						Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
						config.setFullscreenMode(config.getDisplayMode(monitors[deviceBox.getSelectedIndex()]));
						new Lwjgl3Application(new Ticker(), config);
					}
				});
				starter.start();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(ringSpinner, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
						.addComponent(btnEintragHinzufgen, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 253, Short.MAX_VALUE)
						.addComponent(btnLschen, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(classSelector, 0, 203, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
						.addComponent(nameField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
						.addComponent(lblName)
						.addComponent(lblKlasse)
						.addComponent(lblNewLabel)
						.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
						.addComponent(deviceBox, 0, 253, Short.MAX_VALUE)
						.addComponent(lblTargetDevice))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblName)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addComponent(lblKlasse)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(classSelector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ringSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnEintragHinzufgen)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLschen)
					.addPreferredGap(ComponentPlacement.RELATED, 224, Short.MAX_VALUE)
					.addComponent(lblTargetDevice)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(deviceBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		table = new JTable();
		tableModel = new BowTableModel();
		table.setModel(tableModel);
		scrollPane.setViewportView(table);
		frame.getContentPane().setLayout(groupLayout);
		Thread monitorScaner = new Thread(new MonitorScanTask());
		monitorScaner.start();
	}
	
	public class MonitorScanTask implements Runnable {
		
		private String[] monitorNames = {};

		public void run() {
			while(true){
				monitors = Lwjgl3ApplicationConfiguration.getMonitors();
				if(monitors.length > monitorNames.length){
					monitorNames = new String[monitors.length];
					for (int i = 0; i < monitors.length; i++) {
						monitorNames[i] = monitors[i].name;
					}
					deviceBox.setModel(new DefaultComboBoxModel<String>(monitorNames));
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
