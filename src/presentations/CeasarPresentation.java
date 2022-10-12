package presentations;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import business.CriptCeasar;
import presentations.componets.RadioButtonGroup;

public class CeasarPresentation extends JPanel implements IPresentation {
	private JTextField textField;
	private JTextArea taText;
	private JTextArea taResult;
	private ButtonGroup buttonGroup;

	/**
	 * Create the panel.
	 */
	public CeasarPresentation() {
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
		taResult.setAutoscrolls(true);
		taResult.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(292, 11, 248, 428);
		add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Chose your options");
		lblNewLabel_2.setBounds(0, 0, 100, 14);
		panel_1.add(lblNewLabel_2);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Encrypt");
		rdbtnNewRadioButton.setBounds(0, 21, 111, 23);
		rdbtnNewRadioButton.setActionCommand(rdbtnNewRadioButton.getText());
		rdbtnNewRadioButton.setSelected(true);
		panel_1.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Decrypt");
		rdbtnNewRadioButton_1.setBounds(115, 21, 111, 23);
		rdbtnNewRadioButton_1.setActionCommand(rdbtnNewRadioButton_1.getText());
		panel_1.add(rdbtnNewRadioButton_1);

		buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnNewRadioButton);
		buttonGroup.add(rdbtnNewRadioButton_1);

		JLabel lblNewLabel_3 = new JLabel("Enter your key");
		lblNewLabel_3.setBounds(0, 58, 100, 14);
		lblNewLabel_3.setToolTipText("con cò");
		panel_1.add(lblNewLabel_3);

		textField = new JTextField();
		textField.setBounds(0, 83, 226, 20);
		panel_1.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Import Text");
		btnNewButton.setForeground(SystemColor.text);
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setBounds(0, 130, 111, 23);
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					loadText();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});

		JButton btnNewButton_1 = new JButton("Save Text");
		btnNewButton_1.setForeground(SystemColor.text);
		btnNewButton_1.setBackground(SystemColor.textHighlight);
		btnNewButton_1.setBounds(126, 130, 100, 23);
		panel_1.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveFile();
			}
		});

		JButton btnNewButton_2 = new JButton("Start");
		btnNewButton_2.setBounds(75, 302, 89, 23);
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
		try {
			CriptCeasar criptCeasar = new CriptCeasar();
			String plainText = taText.getText();
			String mode = buttonGroup.getSelection().getActionCommand();
			String result;
			int key = Integer.parseInt(textField.getText());
			if(key<0||key>25) {
				JOptionPane.showMessageDialog(this, "Value not between from 0 to 25", "Warning", 1);
				return;
			}
			if (mode.equals("Encrypt")) {
				result = criptCeasar.encript(plainText, key);
				taResult.setText(result);
			} else {
				result = criptCeasar.decript(plainText, key);
				taResult.setText(result);
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Makesure that you filled a key with value from 0 to 25", "Warning", 1);
		}
	}

	public void loadText() throws IOException {
		String result = "";
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.showOpenDialog(this);
		File file = jFileChooser.getSelectedFile();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String s = "";
		while ((s = bufferedReader.readLine()) != null) {
			result += s;
			taText.setText(result);
		}
	}

	public void saveFile() {
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
}
