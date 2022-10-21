package presentations;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class RSAPresentation extends JPanel {
	private JTextArea taText;
	private JTextArea taResult;
	private ButtonGroup buttonGroup;

	public RSAPresentation() {
		setBorder(new EmptyBorder(0, 5, 5, 0));
		setSize(584, 450);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(20, 0, 157, 440);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Enter your text");
		lblNewLabel.setBounds(0, 11, 100, 14);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblNewLabel);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(0, 24, 163, 200);
		textArea.setLineWrap(true);
		panel.add(textArea);

		JLabel lblNewLabel_1 = new JLabel("Your result");
		lblNewLabel_1.setBounds(0, 235, 100, 14);
		panel.add(lblNewLabel_1);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(0, 250, 163, 200);
		textArea_1.setLineWrap(true);
		textArea_1.setColumns(10);
		panel.add(textArea_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(187, 11, 387, 428);
		add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Chose your option");
		lblNewLabel_2.setBounds(0, 0, 150, 14);
		panel_1.add(lblNewLabel_2);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Encrypt");
		rdbtnNewRadioButton.setBounds(0, 21, 76, 23);
		panel_1.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Decrypt");
		rdbtnNewRadioButton_1.setBounds(74, 21, 76, 23);
		panel_1.add(rdbtnNewRadioButton_1);
		buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setSelected(true);
		buttonGroup.add(rdbtnNewRadioButton_1);

		JButton btnNewButton = new JButton("Import Key");
		btnNewButton.setForeground(SystemColor.text);
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setBounds(156, 21, 106, 23);
		panel_1.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Import Text");
		btnNewButton_1.setForeground(SystemColor.text);
		btnNewButton_1.setBackground(SystemColor.textHighlight);
		btnNewButton_1.setBounds(272, 21, 105, 23);
		panel_1.add(btnNewButton_1);

		JLabel lblNewLabel_3 = new JLabel("Chose your key length ");
		lblNewLabel_3.setBounds(0, 60, 150, 14);
		panel_1.add(lblNewLabel_3);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "516", "1024" }));
		comboBox.setBounds(0, 85, 135, 22);
		panel_1.add(comboBox);

		JButton btnCreateKey = new JButton("Create Key");
		btnCreateKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreateKey.setForeground(Color.WHITE);
		btnCreateKey.setBackground(SystemColor.textHighlight);
		btnCreateKey.setBounds(218, 85, 106, 23);
		panel_1.add(btnCreateKey);

		JLabel lblNewLabel_4 = new JLabel("Public key");
		lblNewLabel_4.setBounds(0, 118, 150, 14);
		panel_1.add(lblNewLabel_4);

		JButton btnSavePublicKey = new JButton("Save Public Key");
		btnSavePublicKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSavePublicKey.setForeground(Color.WHITE);
		btnSavePublicKey.setBackground(SystemColor.textHighlight);
		btnSavePublicKey.setBounds(206, 159, 135, 23);
		panel_1.add(btnSavePublicKey);

		JTextArea textArea_2 = new JTextArea();
		textArea_2.setBounds(0, 144, 135, 50);
		panel_1.add(textArea_2);

		JLabel lblNewLabel_4_1 = new JLabel("Private key");
		lblNewLabel_4_1.setBounds(0, 205, 150, 14);
		panel_1.add(lblNewLabel_4_1);

		JTextArea textArea_2_1 = new JTextArea();
		textArea_2_1.setBounds(0, 230, 135, 50);
		panel_1.add(textArea_2_1);

		JButton btnCreateKey_1_1 = new JButton("Save Private Key");
		btnCreateKey_1_1.setForeground(Color.WHITE);
		btnCreateKey_1_1.setBackground(SystemColor.textHighlight);
		btnCreateKey_1_1.setBounds(206, 231, 135, 23);
		panel_1.add(btnCreateKey_1_1);

		JLabel lblNewLabel_4_1_1 = new JLabel("Enter your public/ private key");
		lblNewLabel_4_1_1.setBounds(0, 291, 150, 14);
		panel_1.add(lblNewLabel_4_1_1);

		JTextArea textArea_2_1_1 = new JTextArea();
		textArea_2_1_1.setBounds(0, 308, 135, 50);
		panel_1.add(textArea_2_1_1);

		JButton btnCreateKey_1_1_1 = new JButton("Save Result");
		btnCreateKey_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreateKey_1_1_1.setForeground(Color.WHITE);
		btnCreateKey_1_1_1.setBackground(SystemColor.textHighlight);
		btnCreateKey_1_1_1.setBounds(10, 369, 115, 23);
		panel_1.add(btnCreateKey_1_1_1);

		JButton btnNewButton_2 = new JButton("Start");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(235, 357, 89, 23);
		panel_1.add(btnNewButton_2);

	}
}
