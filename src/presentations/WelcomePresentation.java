package presentations;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;

public class WelcomePresentation extends JPanel {

	/**
	 * Create the panel.
	 */
	public WelcomePresentation() {
		setBorder(new EmptyBorder(0, 5, 5, 0));
		setSize(550, 450);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("WELCOME ");
		lblNewLabel.setFont(new Font("Arial Narrow", Font.BOLD, 30));
		lblNewLabel.setBounds(63, 117, 368, 36);
		add(lblNewLabel);
		
		JLabel lblLetsChoiseYou = new JLabel("LET\u2019S CHOISE YOU ALGORITHMS");
		lblLetsChoiseYou.setFont(new Font("Arial Narrow", Font.BOLD, 30));
		lblLetsChoiseYou.setBounds(63, 149, 427, 36);
		add(lblLetsChoiseYou);
	}
}
