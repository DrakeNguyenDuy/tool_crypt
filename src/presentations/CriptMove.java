package presentations;

import business.CriptCeasar;
import presentations.componets.LableWithTextArea;
import presentations.componets.RadioButtonGroup;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CriptMove extends JPanel implements IPresentation {
    private LableWithTextArea lableWithTextArea1;
    private LableWithTextArea lableWithTextArea2;
    private JComboBox<Integer> jComboBox;
    private RadioButtonGroup radioButtonGroup;

    public CriptMove() {
        setLayout(new GridLayout(1, 2));
        //create 2 panel for app includes left and right
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        add(leftPanel);
        add(rightPanel);
        //end
        BoxLayout boxLayout = new BoxLayout(leftPanel, BoxLayout.Y_AXIS);

        leftPanel.setLayout(boxLayout);
        lableWithTextArea1 = new LableWithTextArea();
        lableWithTextArea2 = new LableWithTextArea();
        leftPanel.add(lableWithTextArea1.createTextArea("Enter your text here"));
        leftPanel.add(lableWithTextArea2.createTextArea("Your result"));

        rightPanel.setLayout(new GridLayout(3, 1));
//        create panel 1 on top pannle right
        JPanel jpane1 = new JPanel();
        jpane1.setLayout(new FlowLayout());
//        create jpane left and right for jpanel 1
        JPanel jPanel1Left = new JPanel();
        JPanel jPanel1Right = new JPanel();
        jpane1.add(jPanel1Left);
        jpane1.add(jPanel1Right);
//        end
//        set layout for jpanel1Left and add components
        BoxLayout boxLayout1 = new BoxLayout(jPanel1Left, BoxLayout.Y_AXIS);
        jPanel1Left.setLayout(boxLayout1);
        jPanel1Left.add(new JLabel("Or load text here"));
        jPanel1Left.add(new JButton("Load text"));
//        end
//        set layout for jpanel1Right and add components
        BoxLayout boxLayout2 = new BoxLayout(jPanel1Right, BoxLayout.Y_AXIS);
        jPanel1Right.setLayout(boxLayout2);
        jPanel1Right.add(new JLabel("Your options", SwingConstants.LEFT));
        radioButtonGroup = new RadioButtonGroup();
        String[] labelOptions = {"Encript", "Decript"};
        jPanel1Right.add(radioButtonGroup.createRadioButtonGroup(labelOptions, true));
//        end
        rightPanel.add(jpane1);

        leftPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        rightPanel.setBorder(new EmptyBorder(10, 10, 10, 10));


//        pannle middle
        JPanel jPanel2 = new JPanel();
        jPanel2.setSize(200, 200);
        Integer[] keys = new Integer[29];
        for (int i = 1; i < keys.length; i++) {
            keys[i - 1] = i;
        }
        jComboBox = new JComboBox<>(keys);
        jPanel2.add(jComboBox);
        jComboBox.setMaximumSize(jComboBox.getPreferredSize());
        rightPanel.add(jPanel2);

        JPanel jPanel3 = new JPanel();
        JButton startBT = new JButton("Start");
        jPanel3.add(startBT);
        rightPanel.add(jPanel3);
        startBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                execute();
            }
        });





    }

    @Override
    public void execute() {
        CriptCeasar criptCeasar = new CriptCeasar();
        String plainText = lableWithTextArea1.getValue();
        String mode =radioButtonGroup.getValue();
        String result;
        int key = (Integer) jComboBox.getSelectedItem();
        if(mode.equals("Encript")){
            result = criptCeasar.encript(plainText, key);
            lableWithTextArea2.setValue(result);
        }else{
            result = criptCeasar.decript(plainText, key);
            lableWithTextArea2.setValue(result);
        }
    }
}
