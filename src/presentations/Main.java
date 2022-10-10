package presentations;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(0, 0, 104, 471);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Al");
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setBounds(0, 0, 104, 27);
		panel.add(lblNewLabel);
		
		JButton btnCeasar = new JButton("Ceasar");
		btnCeasar.setBounds(0, 34, 104, 23);
		panel.add(btnCeasar);
		
		JButton btnVigenere = new JButton("Vigenere");
		btnVigenere.setBounds(0, 68, 104, 23);
		panel.add(btnVigenere);
		
		JButton btnHill = new JButton("Hill");
		btnHill.setBounds(0, 102, 104, 23);
		panel.add(btnHill);
		
		JButton btnDES = new JButton("DES");
		btnDES.setBounds(0, 136, 104, 23);
		panel.add(btnDES);
		
		JButton btnRSA = new JButton("RSA");
		btnRSA.setBounds(0, 168, 104, 23);
		panel.add(btnRSA);
		
		JButton btnMD5 = new JButton("MD5");
		btnMD5.setBounds(0, 198, 104, 23);
		panel.add(btnMD5);
		
		JButton btnSHA1 = new JButton("SHA1");
		btnSHA1.setBounds(0, 228, 104, 23);
		panel.add(btnSHA1);
		
		JButton btnSHA256 = new JButton("SHA256");
		btnSHA256.setBounds(0, 262, 104, 23);
		panel.add(btnSHA256);
		
		JButton btnSHA512 = new JButton("SHA512");
		btnSHA512.setBounds(0, 296, 104, 23);
		panel.add(btnSHA512);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(0, 100, 0, 0));
//		panel_1.setBackground(Color.RED);
		panel_1.setBounds(102, 0, 584, 418);
		panel_1.add(new MD5Presentation());
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		
	}
}
