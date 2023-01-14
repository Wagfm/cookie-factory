package br.maciel.graphics.components.forms;

import br.maciel.utilities.constants.Graphic;
import br.maciel.utilities.constants.Palette;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class BaseForm extends JPanel {
    private final JLabel label;
    private final JTextField textField;

    public BaseForm(String name) {
        super();
        this.label = new JLabel();
        this.textField = new JTextField();
        this.setup();
        this.setupLabel(name);
        this.setupTextField();
    }

    public String getFieldText() {
        return this.textField.getText();
    }

    public void setFieldText(String text) {
        this.textField.setText(text);
    }

    private void setup() {
        BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
        this.setLayout(layout);
        this.add(label);
        this.add(textField);
        this.setBackground(Palette.GRAY);
        this.setOpaque(true);
    }

    private void setupLabel(String name) {
        this.label.setFont(new Font(Graphic.DEFAULT_FONT, Font.PLAIN, 20));
        this.label.setPreferredSize(new Dimension(80, 30));
        this.label.setForeground(Palette.BLACK);
        this.label.setText(name + " (g)");
        this.label.setBackground(Palette.GRAY);
        this.label.setOpaque(true);
    }

    private void setupTextField() {
        this.textField.setFont(new Font(Graphic.DEFAULT_FONT, Font.PLAIN, 20));
        this.textField.setBackground(Palette.WHITE);
        this.textField.setForeground(Palette.BLACK);
        this.textField.setBorder(new LineBorder(Palette.BLACK, 2));
        this.textField.setOpaque(true);
    }

}
