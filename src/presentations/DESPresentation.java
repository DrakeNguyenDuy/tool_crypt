package presentations;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Base64;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import business.DES;
import helppers.Constants;
import helppers.ReadFile;
import helppers.WriteFile;

public class DESPresentation extends JPanel implements IPresentation {
	private JTextField taKey;
	private JTextField taKeyLength;
	private JTextArea taText;
	private JTextArea taResult;
	private ButtonGroup buttonGroup;
	private byte[] byteText = null;
	private byte[] byteResult = null;
	private byte[] byteKey=null;

	public DESPresentation() {
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

		JRadioButton rdbtnEncryt = new JRadioButton("Encrypt");
		rdbtnEncryt.setBounds(0, 21, 111, 23);
		rdbtnEncryt.setActionCommand(Constants.ENCRYPT);
		panel_1.add(rdbtnEncryt);
		rdbtnEncryt.setSelected(true);

		JRadioButton rdbtnDecryt = new JRadioButton("Decrypt");
		rdbtnDecryt.setBounds(115, 21, 111, 23);
		rdbtnDecryt.setActionCommand(Constants.DECRYPT);
		panel_1.add(rdbtnDecryt);

		buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnEncryt);
		buttonGroup.add(rdbtnDecryt);

		JLabel lblNewLabel_3 = new JLabel("Enter your key");
		lblNewLabel_3.setBounds(0, 51, 150, 14);
		panel_1.add(lblNewLabel_3);

		taKey = new JTextField();
		taKey.setBounds(0, 76, 226, 29);
		panel_1.add(taKey);
		taKey.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Enter your key length");
		lblNewLabel_4.setBounds(0, 116, 150, 14);
		panel_1.add(lblNewLabel_4);

		taKeyLength = new JTextField();
		taKeyLength.setBounds(0, 141, 226, 29);
		panel_1.add(taKeyLength);
		taKeyLength.setColumns(10);

		JButton btnNewButton = new JButton("Import Key");
		btnNewButton.setForeground(SystemColor.text);
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					loadText(Constants.IMPORT_KEY);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(0, 186, 105, 23);
		panel_1.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Import Text");
		btnNewButton_1.setBackground(SystemColor.textHighlight);
		btnNewButton_1.setForeground(SystemColor.text);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					loadText(Constants.IMPORT_TEXT);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(115, 186, 111, 23);
		panel_1.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Save Text");
		btnNewButton_2.setBackground(SystemColor.textHighlight);
		btnNewButton_2.setForeground(SystemColor.text);
		btnNewButton_2.setBounds(0, 238, 105, 23);
		panel_1.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveFile(Constants.SAVE_TEXT);
			}
		});

		JButton btnNewButton_3 = new JButton("Save Key");
		btnNewButton_3.setForeground(SystemColor.text);
		btnNewButton_3.setBackground(SystemColor.textHighlight);
		btnNewButton_3.setBounds(117, 238, 109, 23);
		panel_1.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveFile(Constants.SAVE_KEY);
			}
		});

		JButton btnNewButton_4 = new JButton("Create Key");
		btnNewButton_4.setBackground(SystemColor.textHighlight);
		btnNewButton_4.setForeground(SystemColor.text);
		btnNewButton_4.setBounds(64, 293, 111, 23);
		panel_1.add(btnNewButton_4);
		btnNewButton_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				createKey();
			}
		});

		JButton btnNewButton_5 = new JButton("Start");
		btnNewButton_5.setBounds(75, 354, 89, 23);
		panel_1.add(btnNewButton_5);
		btnNewButton_5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				execute();
			}
		});
	}

	private void createKey() {
		byte[] encode = DES.getInstance().createKey().getEncoded();
		String key = "";
		for (byte b : encode) {
			key += Byte.toString(b);
		}
		taKey.setText(key);
		taKeyLength.setText("56");
	}

	@Override
	public void execute() {
		String mode = buttonGroup.getSelection().getActionCommand();
		if (mode.equals(Constants.ENCRYPT)) {
//			DES.getInstance().createKey();
			byte[] byteEncypt = DES.getInstance().encrypt(byteText != null ? byteText : taText.getText().getBytes());
			byteResult = byteEncypt;
			taResult.setText(byteResult.length == 0 ? "" : new String(byteEncypt));
		} else {
			byte[] byteDecypt = DES.getInstance().decypt(byteText != null ? byteText : taText.getText().getBytes());
			byteResult = byteDecypt;
			taResult.setText(byteResult.length == 0 ? "" : new String(byteDecypt));
		}

	}

	@Override
	public void loadText(String type) throws IOException {
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.showOpenDialog(this);
		String path = jFileChooser.getCurrentDirectory() + "\\" + jFileChooser.getSelectedFile().getName();
		if (type.equals(Constants.IMPORT_TEXT)) {
			byteText = ReadFile.getInstance().readFile(path, this);
			taText.setText("Please waiting load file to field");
			String s = new String(byteText);
			taText.setText(s);
		} else {
			byteKey=DES.getInstance().loadKey(path);
			System.out.println(byteKey.length);
			taKey.setText(new String(byteKey));
			taKeyLength.setText("56");
		}
	}

	@Override
	public void saveFile(String type) {
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.showSaveDialog(this);
		String path = jFileChooser.getCurrentDirectory() + "\\" + jFileChooser.getSelectedFile().getName();
		if (type.equals(Constants.SAVE_KEY)) {
			DES.getInstance().saveKey(path);
		} else {
			WriteFile.getInstance().writeFile(path, byteResult);
		}
	}
}
