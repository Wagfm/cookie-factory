package br.maciel.graphics.components.forms;

import br.maciel.utilities.constants.Graphic;
import br.maciel.utilities.constants.Palette;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;

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
        this.label.setPreferredSize(new Dimension(75, 30));
        this.label.setForeground(Palette.BLACK);
        this.label.setText(name);
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
