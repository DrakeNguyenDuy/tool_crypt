package presentations.componets;

import javax.swing.*;
import java.awt.*;

public class LableWithTextArea {
    private JTextArea jTextArea;
    public JPanel createTextArea(String nameLable) {
        JPanel jPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
//        jPanel.setLayout(new GridLayout(2,1));
        jPanel.setLayout(boxLayout);
        jPanel.add(new JLabel(nameLable));
        jTextArea = new JTextArea();
        jTextArea.setRows(2);
        jTextArea.setColumns(2);
        jTextArea.setLineWrap(true);
//        jTextArea.setSize(250, 200);
        jPanel.add(jTextArea);
        return jPanel;
    }
    public String getValue(){
        return jTextArea.getText();
    }
    public void setValue(String value){
        jTextArea.setText(value);
    }
}
