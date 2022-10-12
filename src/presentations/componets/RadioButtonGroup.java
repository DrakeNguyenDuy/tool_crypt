package presentations.componets;

import javax.swing.*;

public class RadioButtonGroup {
    private ButtonGroup buttonGroup;

    public RadioButtonGroup() {
        buttonGroup = new ButtonGroup();
    }

    public JPanel createRadioButtonGroup(String[] labels, boolean isX) {
        JPanel jPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(jPanel, isX ? BoxLayout.X_AXIS : BoxLayout.Y_AXIS);
        jPanel.setLayout(boxLayout);
        for (int i = 0; i < labels.length; i++) {
            JRadioButton jRadioButton = new JRadioButton(labels[i]);
            jRadioButton.setActionCommand(labels[i]);
            jRadioButton.setBounds(0, 21, 111, 23);
            this.buttonGroup.add(jRadioButton);
            if (i == 0) {
                jRadioButton.setSelected(true);
            }
            jPanel.add(jRadioButton);
        }
        return jPanel;
    }

    public String getValue() {
        return buttonGroup.getSelection().getActionCommand();
    }
}
