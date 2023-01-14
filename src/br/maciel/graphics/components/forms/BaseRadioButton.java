package br.maciel.graphics.components.forms;

import br.maciel.utilities.constants.Graphic;
import br.maciel.utilities.constants.Palette;

import javax.swing.JRadioButton;
import java.awt.Font;

public class BaseRadioButton extends JRadioButton {
    public BaseRadioButton(String name) {
        super(name);
        this.setup();
    }

    private void setup() {
        this.setFont(new Font(Graphic.DEFAULT_FONT, Font.BOLD, 15));
        this.setFocusable(false);
        this.setForeground(Palette.BLACK);
        this.setBackground(Palette.GRAY);
    }
}
