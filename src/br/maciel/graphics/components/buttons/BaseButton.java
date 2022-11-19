package br.maciel.graphics.components.buttons;

import br.maciel.graphics.components.RefreshableComponent;
import br.maciel.utilities.constants.Graphic;
import br.maciel.utilities.constants.Palette;

import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class BaseButton extends JButton implements RefreshableComponent, ActionListener {
    public BaseButton(String text, Color backgroundColor) {
        super(text);
        this.setupButton(backgroundColor);
    }

    private void setupButton(Color backgroundColor) {
        this.setFont(new Font(Graphic.DEFAULT_FONT, Font.BOLD, 15));
        this.setFocusable(false);
        this.setBackground(backgroundColor);
        this.setForeground(Palette.BLACK);
        this.setBorder(new BevelBorder(BevelBorder.RAISED));
        this.setMaximumSize(new Dimension(250, 75));
    }

    @Override
    public abstract void refresh();

    @Override
    public abstract void actionPerformed(ActionEvent e);
}
