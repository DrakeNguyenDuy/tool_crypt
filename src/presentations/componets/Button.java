package presentations.componets;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Button extends JButton {

	public Button(int x, int y, int width, int height, String nameButton) {
		setText(nameButton);
		setBounds(x, y, width, height);
		setBorderPainted(false);
		setFocusable(false);
		setBackground(Color.decode("#4991FE"));
		setForeground(Color.decode("#86ADE9"));
		setHorizontalAlignment(SwingConstants.LEFT);
	}

}
