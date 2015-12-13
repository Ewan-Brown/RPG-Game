package display;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;

import main.Game.Difficulty;
import main.StartValues;

import javax.swing.AbstractListModel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class StartDisplay implements ActionListener{

	public JFrame frame;
	JList list;
	private JTextField txtPlayername;
	boolean pressed = false;
	JButton btnEnterInfo;
	JToggleButton tglBtnFastmode;
	JSpinner spinner;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartDisplay window = new StartDisplay();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public StartValues getValues(){
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StartValues values = new StartValues();
		do{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(pressed == false);

		int a = list.getSelectedIndex();
		if(a == -1){
			values.difficulty = Difficulty.Normal;
		}
		if(a == 0){
			values.difficulty = Difficulty.Easy;
		}
		if(a == 1){
			values.difficulty = Difficulty.Normal;
		}
		if(a == 2){
			values.difficulty = Difficulty.Hard;
		}
		if(a == 3){
			values.difficulty = Difficulty.Uber;
		}
		values.playerName = this.txtPlayername.getText();
		values.fastMode = tglBtnFastmode.isSelected();
		values.sleepMillis = (int)spinner.getValue();
		pressed = false;
		return values;

		
	}

	/**
	 * Create the application.
	 */
	public StartDisplay() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 412, 232);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Easy", "Normal", "Hard", "Uber"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		txtPlayername = new JTextField();
		txtPlayername.setText("PlayerName");
		txtPlayername.setColumns(10);
		
		btnEnterInfo = new JButton("Enter");
		btnEnterInfo.addActionListener(this);
		
		tglBtnFastmode = new JToggleButton("Fastmode");
		
		JLabel lblSleepMillis = new JLabel("Sleep millis");
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(500), null, null, new Integer(1)));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(12)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtPlayername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(list, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(38)
							.addComponent(lblSleepMillis))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(tglBtnFastmode, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
						.addComponent(btnEnterInfo, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(12)
							.addComponent(txtPlayername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(9)
							.addComponent(list)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblSleepMillis)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(tglBtnFastmode, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnEnterInfo, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)))
					.addGap(70))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {

		this.pressed = true;
		
	}
}
