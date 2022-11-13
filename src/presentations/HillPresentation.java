package presentations;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import business.Hill;
import business.RSA;
import helppers.Constants;

import javax.swing.JScrollPane;

public class HillPresentation extends JPanel implements IPresentation{
	private JTextArea taText;
	private JTextArea taResult;
	private ButtonGroup buttonGroup;
	private JTextArea key;
	/**
	 * Create the panel.
	 */
	public HillPresentation() {
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 24, 229, 200);
		panel.add(scrollPane);
		
		taText = new JTextArea();
		scrollPane.setViewportView(taText);
		taText.setLineWrap(true);
		
		JLabel lblNewLabel_1 = new JLabel("Your result");
		lblNewLabel_1.setBounds(0, 235, 100, 14);
		panel.add(lblNewLabel_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 250, 229, 200);
		panel.add(scrollPane_1);
		
		taResult = new JTextArea();
		scrollPane_1.setViewportView(taResult);
		taResult.setLineWrap(true);
		taResult.setColumns(10);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(292, 11, 248, 428);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Chose your option");
		lblNewLabel_2.setBounds(0, 0, 150, 14);
		panel_1.add(lblNewLabel_2);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Encrypt");
		rdbtnNewRadioButton.setBounds(0, 21, 111, 23);
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setActionCommand(Constants.ENCRYPT);
		panel_1.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Decrypt");
		rdbtnNewRadioButton_1.setActionCommand(Constants.DECRYPT);
		rdbtnNewRadioButton_1.setBounds(115, 21, 111, 23);
		panel_1.add(rdbtnNewRadioButton_1);
		buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnNewRadioButton);
		buttonGroup.add(rdbtnNewRadioButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("Enter your matrix 2*2");
		lblNewLabel_3.setBounds(0, 51, 150, 14);
		panel_1.add(lblNewLabel_3);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 80, 226, 71);
		panel_1.add(scrollPane_2);
		
		key = new JTextArea();
		scrollPane_2.setViewportView(key);
		key.setRows(10);
		
		JButton btnNewButton = new JButton("Import Key");
		btnNewButton.setForeground(SystemColor.text);
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(0, 186, 105, 23);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Import Text");
		btnNewButton_1.setBackground(SystemColor.textHighlight);
		btnNewButton_1.setForeground(SystemColor.text);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(115, 186, 111, 23);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Save Text");
		btnNewButton_2.setBackground(SystemColor.textHighlight);
		btnNewButton_2.setForeground(SystemColor.text);
		btnNewButton_2.setBounds(0, 238, 105, 23);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Save Key");
		btnNewButton_3.setForeground(SystemColor.text);
		btnNewButton_3.setBackground(SystemColor.textHighlight);
		btnNewButton_3.setBounds(117, 238, 109, 23);
		panel_1.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Start");
		btnNewButton_4.setBounds(70, 329, 89, 23);
		panel_1.add(btnNewButton_4);
		btnNewButton_4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				execute();
			}
		});
	}
	@Override
	public void execute() {
		String mode = buttonGroup.getSelection().getActionCommand();
		if (mode.equals(Constants.ENCRYPT)) {
			String result = Hill.getInstance().encrypt(taText.getText(), key.getText());
			taResult.setText(result);
		} else {
			String result = Hill.getInstance().decrypt(taText.getText(), key.getText());
			taResult.setText(result);
		}
	}
	@Override
	public void loadText(String type) throws IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void saveFile(String type) {
		// TODO Auto-generated method stub
		
	}

}
