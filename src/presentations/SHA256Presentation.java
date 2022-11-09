package presentations;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import business.Hashing;
import helppers.Constants;
import helppers.ReadFile;
import helppers.WriteFile;

import java.awt.Color;

public class SHA256Presentation extends JPanel implements IPresentation{
	private ButtonGroup buttonGroup;
	private JTextArea taText;
	private JTextArea taResult;
	private Hashing hashing;
	/**
	 * Create the panel.
	 */
	public SHA256Presentation() {
		this.hashing = new Hashing();
		setBorder(new EmptyBorder(0, 5, 5, 0));
		setSize(550, 450);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 0, 229, 440);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter your text");
		lblNewLabel.setBounds(0, 11, 100, 14);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblNewLabel);
		
		taText = new JTextArea();
		taText.setBounds(0, 24, 229, 200);
		taText.setLineWrap(true);
		panel.add(taText);
		
		JLabel lblNewLabel_1 = new JLabel("Your result");
		lblNewLabel_1.setBounds(0, 235, 100, 14);
		panel.add(lblNewLabel_1);
		
		taResult = new JTextArea();
		taResult.setBounds(0, 250, 229, 200);
		taResult.setLineWrap(true);
		taResult.setColumns(10);
		panel.add(taResult);

		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(292, 11, 248, 428);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Chose your option");
		lblNewLabel_2.setBounds(0, 0, 150, 14);
		panel_1.add(lblNewLabel_2);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Encrypt");
		rdbtnNewRadioButton.setBounds(0, 21, 111, 23);
		panel_1.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setActionCommand(Constants.ENCRYPT);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Decrypt");
		rdbtnNewRadioButton_1.setBounds(115, 21, 111, 23);
		panel_1.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setActionCommand(Constants.DECRYPT);
		buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnNewRadioButton);
		buttonGroup.add(rdbtnNewRadioButton_1);
		
		JButton btnNewButton = new JButton("Import File");
		btnNewButton.setForeground(SystemColor.text);
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					loadText(null);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(0, 51, 105, 23);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Save Result");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(SystemColor.textHighlight);
		btnNewButton_1.setBounds(115, 51, 105, 23);
		panel_1.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFile(null);
			}
		});
		
		JButton btnNewButton_2 = new JButton("Start");
		btnNewButton_2.setBounds(69, 305, 105, 23);
		panel_1.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				execute();
			}
		});

	}
	@Override
	public void execute() {
		String mode = buttonGroup.getSelection().getActionCommand();
		String input = taText.getText();
		String result = "";
		if (mode.equals(Constants.ENCRYPT)) {
			result = this.hashing.encrypt(Constants.SHA256, input.getBytes(), true);
		} else {
			result = this.hashing.decrypt(input, Constants.SHA256);
		}
		taResult.setText(result);
	}

	@Override
	public void loadText(String type) throws IOException {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(this);
		String path = fileChooser.getCurrentDirectory() + "\\" + fileChooser.getSelectedFile().getName();
		byte[] hashByte = ReadFile.getInstance().readFile(path, null);
		taText.setText(new String(hashByte));
	}

	@Override
	public void saveFile(String type) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showSaveDialog(this);
		String path = fileChooser.getCurrentDirectory() + "\\" + fileChooser.getSelectedFile().getName();
		WriteFile.getInstance().writeFile(path, taResult.getText().getBytes());
	}
}
