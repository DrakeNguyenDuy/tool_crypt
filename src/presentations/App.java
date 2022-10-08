package presentations;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.FlowLayout;

public class App extends JFrame {

	private JPanel contentPane;
	private JPanel panel_1;
	private CeasarPanel ceasarPanel = null;
	private int panelCurrent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
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
	public App() {
		setTitle("Nguy\u1EC5n D\u0169y Long");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(700, 500);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setForeground(SystemColor.textHighlight);
		panel.setBounds(0, 0, 100, 1000);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		panel_1 = new JPanel();
		panel_1.setBounds(110, 11, 566, 441);
		contentPane.add(panel_1);

		JLabel lblNewLabel = new JLabel("Algorithms");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setForeground(SystemColor.textHighlightText);
		panel.add(lblNewLabel);

		JButton btnCeasar = new JButton("Ceasar");
		panel.add(btnCeasar);
		btnCeasar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (ceasarPanel == null) {
					ceasarPanel = new CeasarPanel();
					panelCurrent = 0;
					panel_1.add(ceasarPanel);
					validate();
				}

			}
		});

		JButton btnVigenere = new JButton("Vigenere");
		panel.add(btnVigenere);
		btnVigenere.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel_1.remove(panelCurrent);
				resetComponent();
				validate();
				repaint();
				panelCurrent = 1;
//				if(ceasarPanel==null) {
//					ceasarPanel = new CeasarPanel();
//					panelCurrent=ceasarPanel;
//					panel_1.add(ceasarPanel);
//					validate();	
//				}

			}
		});

		JButton btnHill = new JButton("Hill");
		panel.add(btnHill);

		JButton btnDes = new JButton("DES");
		panel.add(btnDes);

		JButton btnRsa = new JButton("RSA");
		panel.add(btnRsa);

		JButton btnMd5 = new JButton("MD5");
		panel.add(btnMd5);

		JButton btnSHA1 = new JButton("SHA1");
		panel.add(btnSHA1);

		JButton btnSHA256 = new JButton("SHA256");
		panel.add(btnSHA256);

		JButton btnSHA512 = new JButton("SHA512");
		panel.add(btnSHA512);

		JButton btnNewButton = new JButton("New button");
		panel.add(btnNewButton);

	}

	private void resetComponent() {
		switch (panelCurrent) {
		case 0: {
			System.out.println("c");
			ceasarPanel = null;
			System.out.println(ceasarPanel);
			break;
		}
		default:

		}
	}
}
