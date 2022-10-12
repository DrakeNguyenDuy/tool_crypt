package presentations;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import presentations.componets.Button;

public class Main extends JFrame {

	private JPanel contentPane;
	private JButton btnCeasar;
	private JButton btnVigenere;
	private JButton btnHill;
	private JButton btnDES;
	private JButton btnRSA;
	private JButton btnMD5;
	private JButton btnSHA1;
	private JButton btnSHA256;
	private JButton btnSHA512;
	private JPanel panel_1;
	private JPanel panelCurrent;
	private int indexButtonActive;

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
		panelCurrent = null;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.decode("#4991FE"));
		panel.setBounds(0, 0, 104, 471);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Algorithm");
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setBounds(0, 0, 104, 27);
		panel.add(lblNewLabel);

		btnCeasar = new Button(0, 34, 104, 23, "Ceasar");
		panel.add(btnCeasar);

		btnVigenere = new Button(0, 68, 104, 23, "Vigenere");
		panel.add(btnVigenere);

		btnHill = new Button(0, 102, 104, 23, "Hill");
		panel.add(btnHill);

		btnDES = new Button(0, 136, 104, 23, "DES");
		panel.add(btnDES);

		btnRSA = new Button(0, 168, 104, 23, "RSA");
		panel.add(btnRSA);

		btnMD5 = new Button(0, 198, 104, 23, "MD5");
		panel.add(btnMD5);

		btnSHA1 = new Button(0, 228, 104, 23, "SHA1");
		panel.add(btnSHA1);

		btnSHA256 = new Button(0, 262, 104, 23, "SHA256");
		panel.add(btnSHA256);

		btnSHA512 = new Button(0, 296, 104, 23, "SHA512");
		panel.add(btnSHA512);

		panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(0, 100, 0, 0));
		JPanel intro = new WelcomePresentation();
		panel_1.add(intro);
		panelCurrent = intro;
		setTitle("Welcome");
		panel_1.setBounds(102, 0, 584, 418);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		btnCeasar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				moveTab(new CeasarPresentation(), btnCeasar, 0);
			}
		});
		btnVigenere.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				moveTab(new VigenerePresentation(), btnVigenere, 1);
			}
		});
		btnHill.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				moveTab(new HillPresentation(), btnHill, 2);
			}
		});
		btnDES.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				moveTab(new DESPresentation(), btnDES, 3);
			}
		});
		btnRSA.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				moveTab(new RSAPresentation(), btnRSA, 4);
			}
		});
		btnMD5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				moveTab(new MD5Presentation(), btnMD5, 5);
			}
		});
		btnSHA1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				moveTab(new SHA1Presentation(), btnSHA1, 6);
			}
		});
		btnSHA256.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				moveTab(new SHA256Presentation(), btnSHA256, 7);
			}
		});
		btnSHA512.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				moveTab(new SHA512Presentation(), btnSHA512, 8);
			}
		});
	}

	// nếu mà cái này nó là đang click cho chính nó thì sẽ không làm gì
	// còn nếu như nó click vào button khác thì nó sẽ thay đổi màu button hiện tại
	// và set màu active cho button kế tiếp
	private void refreshButtons(int indexActive) {
		if (indexActive != indexButtonActive) {
			switch (indexButtonActive) {
			case 0:
				unactiveStyleButton(btnCeasar);
				break;
			case 1:
				unactiveStyleButton(btnVigenere);
				break;
			case 2:
				unactiveStyleButton(btnHill);
				break;
			case 3:
				unactiveStyleButton(btnDES);
				break;
			case 4:
				unactiveStyleButton(btnRSA);
				break;
			case 5:
				unactiveStyleButton(btnMD5);
				break;
			case 6:
				unactiveStyleButton(btnSHA1);
				break;
			case 7:
				unactiveStyleButton(btnSHA256);
				break;
			default:
				unactiveStyleButton(btnSHA512);
				break;
			}
			indexButtonActive = indexActive;
		}
	}

	private void unactiveStyleButton(JButton button) {
		button.setBackground(Color.decode("#4991FE"));
		button.setForeground(Color.decode("#86ADE9"));
	}

	private void activeStyleButton(JButton button) {
		button.setBackground(Color.decode("#C5E3FF"));
		button.setForeground(Color.decode("#4991FE"));
	}

	private void moveTab(JPanel newPannel, JButton buttonActive, int indexButtonActive) {
		if (panelCurrent != null) {
			panel_1.remove(panelCurrent);
		}
		panelCurrent = newPannel;
		panel_1.add(panelCurrent);
		setTitle(buttonActive.getText());
		refreshButtons(indexButtonActive);
		activeStyleButton(buttonActive);
		repaint();
		revalidate();
	}
}
