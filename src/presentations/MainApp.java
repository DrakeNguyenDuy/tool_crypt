package presentations;

import presentations.componets.LableWithTextArea;
import presentations.componets.RadioButtonGroup;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainApp extends JFrame {

    public MainApp(String title) {
        super(title);
        add(new CriptMove());
        //set config for app
        getContentPane();
        setSize(700, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        MainApp grid = new MainApp("Mã hóa dich chuyển");
    }
}
