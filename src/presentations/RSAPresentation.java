package presentations;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import business.RSA;
import business.RSAKey;
import helppers.Constants;
import helppers.ReadFile;
import helppers.WriteFile;

public class RSAPresentation extends JPanel implements IPresentation {
	private JTextArea taText;
	private JTextArea taResult;
	private ButtonGroup buttonGroup;
	private JTextArea taPublicKey;
	private JTextArea taPrivateKey;
	private byte[] byteText = null;
	private byte[] byteKey = null;
	private int indexChose = -1;
	private JTextArea taKey;
	private Object keyExcute;
	private String result = "";

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

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(0, 24, 163, 200);
		panel.add(scrollPane_3);

		taText = new JTextArea();
		scrollPane_3.setViewportView(taText);
		taText.setLineWrap(true);

		JLabel lblNewLabel_1 = new JLabel("Your result");
		lblNewLabel_1.setBounds(0, 235, 100, 14);
		panel.add(lblNewLabel_1);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(0, 250, 163, 200);
		panel.add(scrollPane_4);

		taResult = new JTextArea();
		scrollPane_4.setViewportView(taResult);
		taResult.setLineWrap(true);
		taResult.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(187, 11, 387, 428);
		add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Chose your option");
		lblNewLabel_2.setBounds(0, 0, 150, 14);
		panel_1.add(lblNewLabel_2);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Encrypt");
		rdbtnNewRadioButton.setBounds(0, 21, 76, 23);
		rdbtnNewRadioButton.setActionCommand(Constants.ENCRYPT);
		panel_1.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Decrypt");
		rdbtnNewRadioButton_1.setBounds(74, 21, 76, 23);
		rdbtnNewRadioButton_1.setActionCommand(Constants.DECRYPT);
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
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				Icon pr = new ImageIcon("src/icon/private.png");
//				Icon pu = new ImageIcon("src/icon/public.png");
//				Object iconArray[] = { pr, pu };
//				indexChose = JOptionPane.showOptionDialog(null, "Chose type key", "Select an Option",
//						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, iconArray, iconArray[1]);
				try {
					loadText(Constants.IMPORT_KEY);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JButton btnNewButton_1 = new JButton("Import Text");
		btnNewButton_1.setForeground(SystemColor.text);
		btnNewButton_1.setBackground(SystemColor.textHighlight);
		btnNewButton_1.setBounds(272, 21, 105, 23);
		panel_1.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					loadText(Constants.IMPORT_TEXT);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JLabel lblNewLabel_3 = new JLabel("Chose your key length ");
		lblNewLabel_3.setBounds(0, 60, 150, 14);
		panel_1.add(lblNewLabel_3);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "1024", "2048", "4096" }));
		comboBox.setBounds(0, 85, 135, 22);
		panel_1.add(comboBox);

		JButton btnCreateKey = new JButton("Create Key");
		btnCreateKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RSAKey.getInstance().createKey(Integer.parseInt(comboBox.getSelectedItem().toString()));
				taPublicKey
						.setText(Base64.getEncoder().encodeToString(RSAKey.getInstance().getPublicKey().getEncoded()));
				taPrivateKey.setText(
						(Base64.getEncoder().encodeToString(RSAKey.getInstance().getPrivateKey().getEncoded())));
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
				saveFile(Constants.PUBLIC_KEY);
			}
		});
		btnSavePublicKey.setForeground(Color.WHITE);
		btnSavePublicKey.setBackground(SystemColor.textHighlight);
		btnSavePublicKey.setBounds(206, 159, 135, 23);
		panel_1.add(btnSavePublicKey);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 144, 135, 50);
		panel_1.add(scrollPane);

		taPublicKey = new JTextArea();
		taPublicKey.setLineWrap(true);
		scrollPane.setViewportView(taPublicKey);

		JLabel lblNewLabel_4_1 = new JLabel("Private key");
		lblNewLabel_4_1.setBounds(0, 205, 150, 14);
		panel_1.add(lblNewLabel_4_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 230, 135, 50);
		panel_1.add(scrollPane_1);

		taPrivateKey = new JTextArea();
		taPrivateKey.setLineWrap(true);
		scrollPane_1.setViewportView(taPrivateKey);

		JButton btnCreateKey_1_1 = new JButton("Save Private Key");
		btnCreateKey_1_1.setForeground(Color.WHITE);
		btnCreateKey_1_1.setBackground(SystemColor.textHighlight);
		btnCreateKey_1_1.setBounds(206, 231, 135, 23);
		panel_1.add(btnCreateKey_1_1);
		btnCreateKey_1_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveFile(Constants.PRIVATE_KEY);
			}
		});

		JLabel lblNewLabel_4_1_1 = new JLabel("Enter your public/ private key");
		lblNewLabel_4_1_1.setBounds(0, 291, 150, 14);
		panel_1.add(lblNewLabel_4_1_1);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 308, 135, 50);
		panel_1.add(scrollPane_2);

		taKey = new JTextArea();
		scrollPane_2.setViewportView(taKey);

		JButton btnCreateKey_1_1_1 = new JButton("Save Result");
		btnCreateKey_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFile(Constants.SAVE_TEXT);
			}
		});
		btnCreateKey_1_1_1.setForeground(Color.WHITE);
		btnCreateKey_1_1_1.setBackground(SystemColor.textHighlight);
		btnCreateKey_1_1_1.setBounds(10, 369, 115, 23);
		panel_1.add(btnCreateKey_1_1_1);

		JButton btnNewButton_2 = new JButton("Start");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				if (indexChose == -1) {
				Icon pr = new ImageIcon("src/icon/private.png");
				Icon pu = new ImageIcon("src/icon/public.png");
				Object iconArray[] = { pr, pu };
				indexChose = JOptionPane.showOptionDialog(null, "Chose type key", "Select an Option",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, iconArray, iconArray[1]);
//				}
				getExcuteKey();
				execute();
			}
		});
		btnNewButton_2.setBounds(235, 357, 89, 23);
		panel_1.add(btnNewButton_2);
	}

	private void getExcuteKey() {
		if (indexChose == 0) {
			keyExcute = RSAKey.getInstance().getPrivateKey();
		} else {
			keyExcute = RSAKey.getInstance().getPublicKey();
		}
	}

	@Override
	public void execute() {
		String mode = buttonGroup.getSelection().getActionCommand();
		if (mode.equals(Constants.ENCRYPT)) {
			try {
				result = RSA.getInstance().encrypt(Base64.getDecoder().decode(taText.getText()), keyExcute);
			} catch (IllegalBlockSizeException e) {
				JOptionPane.showMessageDialog(this, e.getMessage());
			} catch (InvalidKeyException e) {
				JOptionPane.showMessageDialog(this, e.getMessage());
			} catch (ClassCastException e) {
				JOptionPane.showMessageDialog(this, "Wrong key");
			} catch (BadPaddingException e) {
				JOptionPane.showMessageDialog(this, e.getMessage());
			}
		} else {
			try {
				result = RSA.getInstance().decrypt(taText.getText(), keyExcute);
			} catch (IllegalBlockSizeException e) {
				JOptionPane.showMessageDialog(this, e.getMessage());
			} catch (InvalidKeyException e) {
				JOptionPane.showMessageDialog(this, e.getMessage());
			} catch (ClassCastException e) {
				JOptionPane.showMessageDialog(this, "Wrong key");
			} catch (BadPaddingException e) {
				JOptionPane.showMessageDialog(this, e.getMessage());
			}
		}
		taResult.setText(result);
	}

	@Override
	public void loadText(String type) throws IOException {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(this);
		String path = fileChooser.getCurrentDirectory() + "\\" + fileChooser.getSelectedFile().getName();
		if (type.equals(Constants.IMPORT_TEXT)) {
			byteText = ReadFile.getInstance().readFile(path, null);
			taText.setText(Base64.getEncoder().encodeToString(byteText));
		} else {
			byteKey = RSAKey.getInstance().loadKey(path);// load key with type a object( a sign for object key in RSA
															// instance )
			taKey.setText(Base64.getEncoder().encodeToString(byteKey));
		}
	}

	@Override
	public void saveFile(String type) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showSaveDialog(this);
		String path = fileChooser.getCurrentDirectory() + "\\" + fileChooser.getSelectedFile().getName();
		if (type.equals(Constants.PRIVATE_KEY) || type.equals(Constants.PUBLIC_KEY)) {
			RSAKey.getInstance().saveKey(path, type);
			JOptionPane.showMessageDialog(this, "Save file success");
		} else {
			try {
				WriteFile.getInstance().writeFile(path, Base64.getDecoder().decode(taResult.getText().getBytes()));
				JOptionPane.showMessageDialog(this, "Save file success");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, e.getMessage());
			}
		}
	}
}
