package presentations;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class CeasarPanel extends JPanel {
	private JTextField txtX;

	/**
	 * Create the panel.
	 */
	public CeasarPanel() {

		JLabel lblNewLabel = new JLabel("Enter yout text here");
		lblNewLabel.setBounds(33, 9, 99, 14);

		JTextArea textAreaCeasar = new JTextArea();
		textAreaCeasar.setBounds(137, 5, 5, 22);

		JLabel lblNewLabel_1 = new JLabel("Your result");
		lblNewLabel_1.setBounds(147, 9, 54, 14);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(206, 5, 5, 22);

		JLabel lblNewLabel_2 = new JLabel("Your options");
		lblNewLabel_2.setBounds(216, 9, 62, 14);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Encrypt");
		rdbtnNewRadioButton.setBounds(283, 5, 63, 23);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Decrypt");
		rdbtnNewRadioButton_1.setBounds(351, 5, 65, 23);

		JLabel lblNewLabel_3 = new JLabel("Enter your key");
		lblNewLabel_3.setBounds(50, 37, 71, 14);

		txtX = new JTextField();
		txtX.setBounds(126, 34, 96, 20);
		txtX.setToolTipText("G\u00EDa tr\u1ECB n\u00EAn nh\u1EADp");

		JButton btnNewButton = new JButton("Import text");
		btnNewButton.setBounds(227, 33, 89, 23);
		btnNewButton.setForeground(Color.blue);
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JButton btnNewButton_1 = new JButton("Save text");
		btnNewButton_1.setBounds(321, 33, 79, 23);
		
		JButton btnNewButton_2 = new JButton("Start");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(textAreaCeasar, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
						.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(102))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 147, Short.MAX_VALUE)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(13)
									.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
												.addComponent(rdbtnNewRadioButton))
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
													.addComponent(rdbtnNewRadioButton_1, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
													.addGap(142))
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(26)
													.addComponent(btnNewButton_1))))
										.addComponent(lblNewLabel_3)
										.addComponent(txtX, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))))
							.addGap(88))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(88)
							.addComponent(btnNewButton_2)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_2))
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textAreaCeasar, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(rdbtnNewRadioButton_1)
							.addGap(7)
							.addComponent(lblNewLabel_3)
							.addGap(10)
							.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton)
								.addComponent(btnNewButton_1))
							.addGap(1))
						.addComponent(rdbtnNewRadioButton))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(lblNewLabel_1)
							.addGap(18)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNewButton_2)
							.addGap(61))))
		);
		setLayout(groupLayout);

		
	}
}
