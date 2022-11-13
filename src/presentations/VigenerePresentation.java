package presentations;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import business.CriptCeasar;
import business.Vigenere;
import helppers.Constants;
import helppers.Templates;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.util.Random;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JScrollPane;

public class VigenerePresentation extends JPanel implements IPresentation {
	private JTextArea taText;
	private JTextArea taResult;
	private ButtonGroup buttonGroup;
	private JTextArea key;

	public VigenerePresentation() {
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
		rdbtnNewRadioButton.setActionCommand(rdbtnNewRadioButton.getText());
		panel_1.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Decrypt");
		rdbtnNewRadioButton_1.setBounds(115, 21, 111, 23);
		rdbtnNewRadioButton_1.setActionCommand(rdbtnNewRadioButton_1.getText());
		panel_1.add(rdbtnNewRadioButton_1);

		buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnNewRadioButton);
		buttonGroup.add(rdbtnNewRadioButton_1);

		JLabel lblNewLabel_3 = new JLabel("Enter your key");
		lblNewLabel_3.setBounds(0, 51, 150, 14);
		panel_1.add(lblNewLabel_3);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 80, 226, 71);
		panel_1.add(scrollPane_2);

		key = new JTextArea();
		scrollPane_2.setViewportView(key);
		key.setLineWrap(true);

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
		JButton btnNewButton_2_1 = new JButton("Or Create Key");
		btnNewButton_2_1.setForeground(Color.WHITE);
		btnNewButton_2_1.setBackground(SystemColor.textHighlight);
		btnNewButton_2_1.setBounds(89, 47, 137, 23);
		panel_1.add(btnNewButton_2_1);
		btnNewButton_2_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				createKey();
			}
		});
	}

	@Override
	public void execute() {
		try {
			Vigenere vigenere = Vigenere.instance();
			String plainText = taText.getText();
			String temp= Normalizer.normalize(plainText, Normalizer.Form.NFD);
			Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
			plainText = pattern.matcher(temp).replaceAll("").replaceAll("đ", "d");
			String mode = buttonGroup.getSelection().getActionCommand();
			String result;
			String k = key.getText();

			if (mode.equals("Encrypt")) {
				result = vigenere.encript(plainText, k);
				taResult.setText(result);
			} else {
				result = vigenere.decrypt(plainText, k);
				taResult.setText(result);
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Make sure that you filled a key with value from 0 to 25", "Warning", 1);
		}
	}

	@Override
	public void loadText(String type) throws IOException {
		String result = "";
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.showOpenDialog(this);
		File file = jFileChooser.getSelectedFile();
		if(file.getName().split("\\.")[1].equals("txt")||file.getName().split("\\.")[1].equals("java")) {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			String s = "";
			while ((s = bufferedReader.readLine()) != null) {
				result += s;
				taText.setText(result);
			}
		}else {
			JOptionPane.showMessageDialog(this, "File không lợp lệ");
		}
	}

	@Override
	public void saveFile(String type) {
		try {
			JFileChooser jFileChooser = new JFileChooser();
			jFileChooser.showSaveDialog(this);
			String path = jFileChooser.getCurrentDirectory() + "\\" + jFileChooser.getSelectedFile().getName();
			File file = new File(path);
			BufferedWriter bufferedWriter= new BufferedWriter(new FileWriter(file));
			bufferedWriter.write(taResult.getText());
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createKey() {
		this.key.setText(Vigenere.instance().createKey());
	}
}
